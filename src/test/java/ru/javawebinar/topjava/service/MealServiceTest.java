package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-repo.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void create() {
        Meal actual = service.create(getNew(), USER_ID);
        Integer newId = actual.getId();
        Meal expected = getNew();
        expected.setId(newId);
        assertMatch(actual, expected);
        assertMatch(service.get(newId, USER_ID), expected);
    }

    @Test
    public void duplicateDateTimeCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Meal(MEAL1_DT, "meal 1 test duplicate time", 100), USER_ID));
    }

    @Test
    public void delete() {
        service.delete(USER_MEAL1_ID, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(USER_MEAL1.getId(), USER_ID));
    }

    @Test
    public void deleteNotUsers() {
        assertThrows(NotFoundException.class, () -> service.delete(ADMIN_MEAL3_ID, USER_ID));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOBODY_MEAL_ID, USER_ID));
    }

    @Test
    public void get() {
        Meal actual = service.get(USER_MEAL1.getId(), USER_ID);
        assertMatch(actual, USER_MEAL1);
    }

    @Test
    public void getNotUsers() {
        assertThrows(NotFoundException.class, () -> service.get(ADMIN_MEAL3_ID, USER_ID));
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOBODY_MEAL_ID, USER_ID));
    }

    @Test
    public void update() {
        Meal updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(updated.getId(), USER_ID), getUpdated());
    }

    @Test
    public void updateNotUsers() {
        assertThrows(NotFoundException.class, () -> service.update(ADMIN_MEAL3, USER_ID));
    }

    @Test
    public void updateNotFound() {
        Meal updated = getNew();
        service.update(updated, USER_ID);
        assertMatch(service.get(updated.getId(), USER_ID), updated);
    }

    @Test
    public void getAll() {
        List<Meal> actual = service.getAll(USER_ID);
        assertMatch(actual, USER_MEAL2, USER_MEAL1);
    }

    public void testGetBetweenInclusive() {
        List<Meal> actual = service.getBetweenInclusive(LocalDate.of(2020, 10, 24), LocalDate.of(2020, 10, 24), USER_ID);
        List<Meal> expected = new ArrayList<>();
        expected.add(USER_MEAL1);
        assertMatch(actual, expected);
    }

    public void testGetBetweenInclusiveNull() {
        List<Meal> actual = service.getBetweenInclusive(null, null, USER_ID);
        assertMatch(actual, USER_MEAL2, USER_MEAL1);
    }
}