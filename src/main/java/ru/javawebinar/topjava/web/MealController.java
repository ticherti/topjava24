package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.MealList;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.MealStorage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealController extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    private static final String INSERT_OR_EDIT = "/editMeal.jsp";
    private static final String LIST_MEAL = "/meals.jsp";
    private MealStorage storage;


    @Override
    public void init() {
        storage = MealList.getStorage();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward;
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("delete")) {
            log("Controlling servlet. Method doGet, action delete");
            storage.delete(parseId(request));
            forward = LIST_MEAL;
            request.setAttribute("list", MealList.get());
        } else if (action.equalsIgnoreCase("edit")) {
            log("Controlling servlet. Method doGet, action edit");
            forward = INSERT_OR_EDIT;
            Meal meal = storage.get(parseId(request));
            request.setAttribute("meal", meal);
        } else {
            log("Controlling servlet. Method doGet, action any other than delete and edit");
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String idString = request.getParameter("id").trim();
        if (idString.isEmpty()) {
            log("Controlling servlet. Method doPost, id string is empty");
            storage.add(createMeal(request));
        } else {
            log(String.format("Controlling servlet. Method doPost, id string is %s", idString));
            long id = parseId(request);
            Meal meal = createMeal(request);
            meal.setId(id);
            storage.update(meal);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_MEAL);
        request.setAttribute("list", MealList.get());
        view.forward(request, response);
    }

    private Meal createMeal(HttpServletRequest request) {
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories").trim());
        String dateTime = request.getParameter("dateTime");
        LocalDateTime ldt = LocalDateTime.parse(dateTime);
        return new Meal(ldt, description, calories);
    }

    private long parseId(HttpServletRequest request) {
        return Long.parseLong(request.getParameter("id"));
    }
}
