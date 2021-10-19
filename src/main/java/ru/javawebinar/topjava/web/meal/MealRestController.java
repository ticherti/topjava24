package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.List;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public List<MealTo> getAll(int userId) {
        log.info("getAll {}", userId);
        assureIdConsistent(userId);

        return MealsUtil.getTos(service.getAll(userId), SecurityUtil.authUserCaloriesPerDay());
    }

    public Meal get(int userId, int mealId) {
        log.info("get {}, {}", userId, mealId);
        assureIdConsistent(userId);
        return service.get(userId, mealId);
    }

    public Meal create(int userId, Meal meal, int id) {
        log.info("create {}, {}", userId, meal);
        assureIdConsistent(userId);
        return service.create(userId, meal, id);
    }

    public void delete(int userId, int id) {
        log.info("delete {}, {}", userId, id);
        assureIdConsistent(userId);
        service.delete(userId, id);
    }

    //    todo should it be in some mealValidationUtil ?
    private static void assureIdConsistent(int userId) {
        if (userId != SecurityUtil.authUserId()) {
            throw new NotFoundException("Not right user " + userId);
        }
    }
}