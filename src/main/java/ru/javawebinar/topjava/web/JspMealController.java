package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
@RequestMapping("/meals")
public class JspMealController {
    @Autowired
    private MealRestController mealController;

    @GetMapping()
    public String getMeals(
            @RequestParam(value = "action", required = false) String action,
            @RequestParam(value = "startDate", required = false) String startDateInput,
            @RequestParam(value = "endDate", required = false) String endDateInput,
            @RequestParam(value = "startTime", required = false) String startTimeInput,
            @RequestParam(value = "endTime", required = false) String endTimeInput,
            Model model) {
        if ("filter".equals(action)) {
            LocalDate startDate = parseLocalDate(startDateInput);
            LocalDate endDate = parseLocalDate(endDateInput);
            LocalTime startTime = parseLocalTime(startTimeInput);
            LocalTime endTime = parseLocalTime(endTimeInput);
            model.addAttribute("meals", mealController.getBetween(startDate, startTime, endDate, endTime));
        } else {
            model.addAttribute("meals", mealController.getAll());
        }
        return "meals";
    }

    @PostMapping()
    public String saveMeal(HttpServletRequest request) {

        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));
        if (StringUtils.hasLength(request.getParameter("id"))) {
            mealController.update(meal, getId(request));
        } else {
            mealController.create(meal);
        }
        return "redirect:/meals";
    }

    @GetMapping("/new")
    public String newMeal(@ModelAttribute("meal") Meal meal) {
        return "mealForm";
    }

    @GetMapping("/update")
    public String edit(HttpServletRequest request, Model model) {
        model.addAttribute("meal", mealController.get(getId(request)));
        return "mealForm";
    }

    @GetMapping(params = "action=delete")
    public String deleteMeal(HttpServletRequest request) {
        mealController.delete(getId(request));
        return "redirect:/meals";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}