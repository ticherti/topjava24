package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.storage.MapStorage;
import ru.javawebinar.topjava.storage.MealStorage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MealsUtil {
    public static final int LIMIT = 2000;
    private static final MealStorage storage = createStorage();

    public static void main(String[] args) {
        get().forEach(System.out::println);
    }

    private static MealStorage createStorage() {
        List<Meal> list = Arrays.asList(
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Пикник на обочине", 100),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );
        MealStorage storage = new MapStorage();
        list.forEach(storage::add);
        return storage;
    }
    public static List<MealTo> get() {
        return filteredByStreams(storage.getAll(), everyMeal -> true, LIMIT);
    }

    public static MealStorage getStorage() {
        return storage;
    }
    public static List<MealTo> filteredByStreams(List<Meal> meals, Predicate<MealTo> predicate, int dayLimit) {
        Map<LocalDate, Integer> caloriesSumByDate = meals.stream()
                .collect(Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories)));

        return meals.stream()
                .map(meal -> createMealTo(meal, caloriesSumByDate.get(meal.getDate()) > dayLimit))
                .filter(predicate)
                .collect(Collectors.toList());
    }

    private static MealTo createMealTo(Meal meal, boolean excess) {
        return new MealTo(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), excess);
    }

}
