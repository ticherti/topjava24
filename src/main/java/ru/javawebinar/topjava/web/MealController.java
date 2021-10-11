package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.MealList;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.AbstractStorage;
import ru.javawebinar.topjava.storage.MapStorage;

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
    private final AbstractStorage storage;

    public MealController() {
        super();
        storage = MealList.getStorage();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//            TODO check for repeated long id =
//        TODO dry request.setAttribute("list", storage.getAll());

        String forward = "";
        String action = request.getParameter("action");
        if (action.equalsIgnoreCase("delete")) {
            long id = Long.parseLong(request.getParameter("id"));
            storage.delete(id);
            forward = LIST_MEAL;
            request.setAttribute("list", MealList.get());
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            long id = Long.parseLong(request.getParameter("id"));
            Meal meal = storage.get(id);
            request.setAttribute("meal", meal);
        } else if (action.equalsIgnoreCase("listMeal")) { // do I even need it??
            forward = LIST_MEAL;
            request.setAttribute("list", MealList.get());
//            TODO why? or why this else is here?! why not plain insert case?
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        TODO check out formatter and ! shorten new Meal line
//        TODO probably all parsing should happen in try-catch
        request.setCharacterEncoding("UTF-8");
        String idString = request.getParameter("mealId").trim();

        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories").trim());
        String dateTime = request.getParameter("dateTime");
        LocalDateTime ldt = LocalDateTime.parse(dateTime);

//        try {
//            Date dob = new SimpleDateFormat("MM/dd/yyyy").parse(request.getParameter("dob"));
//            user.setDob(dob);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//     TODO refactor parsing integer?
        if (idString == null || idString.isEmpty()) {
            Meal meal = new Meal(ldt, description, calories);
            storage.add(meal);
        } else {
            int id = Integer.parseInt(idString);
            Meal meal = new Meal(ldt, description, calories, id);
            storage.update(meal);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_MEAL);
        request.setAttribute("list", MealList.get());
        view.forward(request, response);
    }
}
