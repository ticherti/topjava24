package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.MealList;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.AbstractMealStorage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class MealController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String INSERT_OR_EDIT = "/editMeal.jsp";
    private static final String LIST_MEAL = "/meals.jsp";
    private final AbstractMealStorage storage;

    public MealController() {
        super();
        storage = MealList.getStorage();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward;
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("delete")) {
            storage.delete(parseId(request));
            forward = LIST_MEAL;
            request.setAttribute("list", MealList.get());
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            Meal meal = storage.get(parseId(request));
            request.setAttribute("meal", meal);
        } else {
//            TODO probably better to put plain else if case for insert action
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String idString = request.getParameter("id").trim();
        String description = request.getParameter("description");
        //     TODO check out for parsing exception
        int calories = Integer.parseInt(request.getParameter("calories").trim());
        String dateTime = request.getParameter("dateTime");
        LocalDateTime ldt = LocalDateTime.parse(dateTime);
        if (idString.isEmpty()) {
            Meal meal = new Meal(ldt, description, calories);
            storage.add(meal);
        } else {
            long id = Long.parseLong(idString);
            Meal meal = new Meal(ldt, description, calories);
            meal.setId(id);
            storage.update(meal);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_MEAL);
        request.setAttribute("list", MealList.get());
        view.forward(request, response);
    }

    private long parseId(HttpServletRequest request) {
        return Long.parseLong(request.getParameter("id"));
    }
}
