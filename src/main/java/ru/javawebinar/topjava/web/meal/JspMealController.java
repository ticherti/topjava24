package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
@RequestMapping("/meals")
public class JspMealController extends AbstractMealController {

    public JspMealController(MealService service) {
        super(service);
    }

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
            model.addAttribute("meals", super.getBetween(startDate, startTime, endDate, endTime));
        } else {
            model.addAttribute("meals", super.getAll());
        }
        return "meals";
    }

    @PostMapping()
    public String saveMeal(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));
        if (StringUtils.hasLength(request.getParameter("id"))) {
            super.update(meal, getId(request));
        } else {
            super.create(meal);
        }
        return "redirect:/meals";
    }

    @GetMapping("/new")
    public String newMeal(@ModelAttribute("meal") Meal meal) {
        return "mealForm";
    }

    @GetMapping("/update")
    public String edit(HttpServletRequest request, Model model) {
        model.addAttribute("meal", super.get(getId(request)));
        model.addAttribute("action", "create");
        return "mealForm";
    }

    @GetMapping("/delete")
    public String deleteMeal(HttpServletRequest request) {
        super.delete(getId(request));
        return "redirect:/meals";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}