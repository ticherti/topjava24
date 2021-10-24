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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
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
        assertThat(actual).isEqualTo(expected);
        assertThat(service.get(newId, USER_ID)).isEqualTo(expected);
    }

    @Test
    public void duplicateDateTimeCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Meal(MEAL1_LD, "meal 1 test duplicate time", 100), USER_ID));
    }

    @Test
    public void delete() {
        service.delete(MEAL1_ID, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(MEAL1.getId(), USER_ID));
    }

    @Test
    public void deleteNotUsers() {
        assertThrows(NotFoundException.class, () -> service.delete(MEAL3_ID, USER_ID));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(6, USER_ID));
    }

    @Test
    public void get() {
        Meal actual = service.get(MEAL1.getId(), USER_ID);
        assertThat(actual).isEqualTo(MEAL1);
    }

    @Test
    public void getNotUsers() {
        assertThrows(NotFoundException.class, () -> service.get(MEAL3_ID, USER_ID));
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(6, USER_ID));
    }

    @Test
    public void update() {
        Meal updated = getUpdated();
        service.update(updated, USER_ID);
        assertThat(service.get(updated.getId(), USER_ID)).isEqualTo(updated);
    }

    @Test
    public void updateNotUsers() {
        assertThrows(NotFoundException.class, () -> service.update(MEAL3, USER_ID));
    }

    @Test
    public void getAll() {
        List<Meal> actual = service.getAll(USER_ID);
        List<Meal> expected = new ArrayList<>();
        expected.add(MEAL2);
        expected.add(MEAL1);
        assertThat(actual).isEqualTo(expected);
    }

    public void testGetBetweenInclusive() {
        List<Meal> actual = service.getBetweenInclusive(LocalDate.of(2020, 10, 24), LocalDate.of(2020, 10, 24), USER_ID);
        List<Meal> expected = new ArrayList<>();
        expected.add(MEAL1);
        assertThat(actual).isEqualTo(expected);
    }
}