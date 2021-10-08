package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.MealList;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MealsUtil {

    public static void main(String[] args) {
        MealList.get().forEach(System.out::println);
    }

    public static List<MealTo> filteredByStreams(List<Meal> meals, LocalTime startTime, LocalTime endTime, int dayLimit) {
        Map<LocalDate, Integer> caloriesSumByDate = groupCaloriesByDay(meals);

        return meals.stream()
                .filter(meal -> TimeUtil.isBetweenHalfOpen(meal.getTime(), startTime, endTime))
                .map(meal -> createTo(meal, caloriesSumByDate.get(meal.getDate()) > dayLimit))
                .collect(Collectors.toList());
    }

    public static List<MealTo> map(List<Meal> meals, int dayLimit) {
        Map<LocalDate, Integer> caloriesSumByDate = groupCaloriesByDay(meals);

        return meals.stream()
                .map(meal -> createTo(meal, caloriesSumByDate.get(meal.getDate()) > dayLimit))
                .collect(Collectors.toList());
    }

    private static Map<LocalDate, Integer> groupCaloriesByDay(List<Meal> meals) {
        return meals.stream()
                .collect(Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories)));
    }

    private static MealTo createTo(Meal meal, boolean excess) {
        return new MealTo(meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess);
    }
}
