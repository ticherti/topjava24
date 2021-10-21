package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class MealRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public Meal get(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get {}, {}", userId, id);
        return service.get(userId, id);
    }

    public Meal save(Meal meal) {
        int userId = SecurityUtil.authUserId();
        log.info("create {}, {}", userId, meal);
        return service.save(userId, meal);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete {}, {}", userId, id);
        service.delete(userId, id);
    }

    public Meal update(Meal meal, int id) {
        int userId = SecurityUtil.authUserId();
        log.info("update {} with id={}", meal, userId);
//        todo UNDERSTAND SOMETHING
//        assureIdConsistent(meal, id);
        return service.update(userId, meal, id);
    }

    public List<MealTo> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll {}", userId);
        return MealsUtil.getTos(service.getAll(userId), SecurityUtil.authUserCaloriesPerDay());
    }

    public List<MealTo> getAllFiltered(LocalDate sd, LocalDate ed, LocalTime st, LocalTime et) {
        int userId = SecurityUtil.authUserId();
        LocalDate startDate = getDate(sd, true);
        LocalDate endDate = getDate(ed, false);
        LocalTime startTime = getTime(st, true);
        LocalTime endTime = getTime(et, false);
        log.info("getAll filtered {}, date {} - {}, time {} - {}", userId, startDate, endDate, startTime, endTime);
        return MealsUtil.getFilteredTos(service.getAllFiltered(userId, startDate, endDate),
                SecurityUtil.authUserCaloriesPerDay(), startTime, endTime);
    }

    private LocalDate getDate(LocalDate date, Boolean isStart) {
        if (date == null) {
            return isStart ? LocalDate.MIN : LocalDate.MAX;
        }
        return date;
    }

    private LocalTime getTime(LocalTime time, Boolean isStart) {
        if (time == null){
            return isStart ? LocalTime.MIN : LocalTime.MAX;
        }
        return time;
    }
}