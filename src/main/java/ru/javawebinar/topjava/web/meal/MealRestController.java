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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public Meal get(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get {}, {}", userId, id);
        Meal meal = service.get(userId, id);
        check(meal);
        return meal;
    }

    public Meal save(Meal meal) {
        int userId = SecurityUtil.authUserId();
        log.info("create {}, {}", userId, meal);
        meal = service.save(userId, meal);
//      todo check it out. Because it really doesn't work for the reason of:
//       userMeals.computeIfPresent(id, (mealId, oldMeal) -> meal) in save() in repo
        check(meal);
        return meal;
    }

    public boolean delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete {}, {}", userId, id);
        Meal meal = service.get(userId, id);
        check(meal);
        return service.delete(userId, id);
    }

    public List<MealTo> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll {}", userId);

        return MealsUtil.getTos(service.getAll(userId), SecurityUtil.authUserCaloriesPerDay());
    }

    public List<MealTo> getAllFiltered(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        int userId = SecurityUtil.authUserId();
        log.info("getAll filtered {}, date {} - {}, time {} - {}", userId, startDate, endDate, startTime, endTime);

        return MealsUtil.getFilteredTos(service.getAllFiltered(userId, startDate, endDate),
                SecurityUtil.authUserCaloriesPerDay(), startTime, endTime);
    }

    private Meal check(Meal meal) {
        if (meal == null) {
            throw new NotFoundException("Meal not found.");
        }
        return meal;
    }
}