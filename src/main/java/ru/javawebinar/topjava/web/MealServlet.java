package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.storage.InMemoryMealStorage;
import ru.javawebinar.topjava.storage.MealStorage;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.filteredByStreams;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    private static final int LIMIT = 2000;
    private static final String INSERT_OR_EDIT = "/editMeal.jsp";
    private static final String LIST_MEAL = "/meals.jsp";
    private MealStorage storage;

    @Override
    public void init() {
        storage = new InMemoryMealStorage();
        MealsUtil.getTestMealList().forEach(storage::add);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meal");

        String action = request.getParameter("action");
        if (action == null) {
            action = "default";
        }
        switch (action) {
            case "delete":
                log.debug("Controlling servlet. Method doGet, action delete");

                storage.delete(parseId(request));
                response.sendRedirect("meals");
                break;
            case "edit":
                log.debug("Controlling servlet. Method doGet, action edit");

                Meal meal = storage.get(parseId(request));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher(INSERT_OR_EDIT).forward(request, response);
                break;
            case "insert":
                log.debug("Controlling servlet. Method doGet, action insert");

                request.getRequestDispatcher(INSERT_OR_EDIT).forward(request, response);
                break;
            default:
                log.debug("Controlling servlet. Method doGet, action is unknown or empty");

                request.setAttribute("list", getList());
                request.getRequestDispatcher(LIST_MEAL).forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String idString = request.getParameter("id");
        Meal meal = createMeal(request);
        if (idString.isEmpty()) {
            log.debug("Controlling servlet. Method doPost, id string is empty");
            storage.add(meal);
        } else {
            log.debug(String.format("Controlling servlet. Method doPost, id string is %s", idString));
            meal.setId(parseId(request));
            storage.update(meal);
        }
        response.sendRedirect("meals");
    }

    private Meal createMeal(HttpServletRequest request) {
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories").trim());
        String dateTime = request.getParameter("dateTime");
        LocalDateTime ldt = LocalDateTime.parse(dateTime);
        return new Meal(ldt, description, calories);
    }

    private int parseId(HttpServletRequest request) {
        return Integer.parseInt(request.getParameter("id"));
    }

    private List<MealTo> getList() {
        return filteredByStreams(storage.getAll(), everyMeal -> true, LIMIT);
    }
}
