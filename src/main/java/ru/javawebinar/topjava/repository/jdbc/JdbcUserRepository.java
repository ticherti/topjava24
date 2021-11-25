package ru.javawebinar.topjava.repository.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.List;
import java.util.Set;

@Repository
@Transactional(readOnly = true)
public class JdbcUserRepository implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(JdbcUserRepository.class);

    private static final BeanPropertyRowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertUser;

    @Autowired
    public JdbcUserRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertUser = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Transactional
    @Override
    public User save(User user) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
        if (user.isNew()) {
            Number newKey = insertUser.executeAndReturnKey(parameterSource);
            user.setId(newKey.intValue());
        } else {
            deleteRoles(user);
        }
        if (namedParameterJdbcTemplate.update("""
                   UPDATE users SET name=:name, email=:email, password=:password,
                   registered=:registered, enabled=:enabled, calories_per_day=:caloriesPerDay WHERE id=:id
                """, parameterSource) == 0) {
            return null;
        }
        insertRoles(user);
        return user;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id=?", id) != 0;
    }

    @Override
    public User get(int id) {
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE id=?", ROW_MAPPER, id);
        return setRolesForFirstOf(users);
    }

    @Override
    public User getByEmail(String email) {
//        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE email=?", ROW_MAPPER, email);
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE email=?", ROW_MAPPER, email);
        return setRolesForFirstOf(users);
    }

    @Override
    public List<User> getAll() {
//// Something's not working here today:
//        List<User> users = jdbcTemplate.query("SELECT * FROM users ORDER BY name, email", ROW_MAPPER);
//        Map<String, Set<Role>> roles =  jdbcTemplate.query("Select * From user_roles", rs -> {
//            Map<String, Set<Role>> data = new HashMap<>();
//            while (rs.next()) {
//                String id = rs.getString("user_id");
//                data.putIfAbsent(id, new HashSet<>());
//                data.get(id).add(Role.valueOf(rs.getString("role")));
//            }
//            return data;
//        });
//        return users.stream().map(u -> {u.setRoles(roles.get(u.id())); return u;}).collect(Collectors.toList());
        return jdbcTemplate.query("""
                SELECT users.*, string_agg(role, ', ') AS roles FROM users
                LEFT JOIN user_roles ur ON id = ur.user_id GROUP BY id ORDER BY max(name), max(email)""", ROW_MAPPER);
    }

    private void deleteRoles(User user) {
        jdbcTemplate.update("""
                DELETE From user_roles Where user_id=?
                """, user.id());
    }

    private User setRolesForFirstOf(List<User> users) {
        User user = DataAccessUtils.singleResult(users);
        if (user == null) {
            return null;
        }
        List<Role> roles = jdbcTemplate.queryForList("SELECT role FROM user_roles where user_id=?", Role.class, user.id());
        user.setRoles(roles);
        return user;
    }

    private void insertRoles(User user) {
        Set<Role> roles = user.getRoles();
        if (roles != null) {
            jdbcTemplate.batchUpdate("""
                               INSERT INTO user_roles (user_id, role) VALUES (?, ?)
                            """,
                    roles,
                    roles.size(), (ps, role) -> {
                        ps.setInt(1, user.id());
                        ps.setString(2, role.name());
                    });
        }
    }
}
