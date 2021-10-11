package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.MealList;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MealsUtil {

    public static void main(String[] args) {
        MealList.get().forEach(System.out::println);
    }

    public static List<MealTo> filteredByStreams(List<Meal> meals, LocalTime startTime, LocalTime endTime, int dayLimit) {
        return mapMeals(meals.stream(), meals, dayLimit)
                .filter(meal -> TimeUtil.isBetweenHalfOpen(meal.getTime(), startTime, endTime))
                .collect(Collectors.toList());
    }

    public static List<MealTo> getMappedMealList(List<Meal> meals, int dayLimit) {
        return mapMeals(meals.stream(), meals, dayLimit)
                .collect(Collectors.toList());
    }

    private static MealTo createMealTo(Meal meal, boolean excess) {
        return new MealTo(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess);
    }

    private static Stream<MealTo> mapMeals(Stream<Meal> stream, List<Meal> meals, int dayLimit) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories)));
        return stream.map(meal -> createMealTo(meal, caloriesSumByDate.get(meal.getDate()) > dayLimit));
    }

}
