package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.storage.AbstractMealStorage;
import ru.javawebinar.topjava.storage.MapMealStorage;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static ru.javawebinar.topjava.util.MealsUtil.getMappedMealList;

public class MealList {
    public static final int LIMIT = 2000;
    private static final AbstractMealStorage storage = createStorage();

    public static List<MealTo> get() {
        return getMappedMealList(storage.getAll(), LIMIT);
    }

    public static AbstractMealStorage getStorage() {
        return storage;
    }

    private static AbstractMealStorage createStorage() {
        List<Meal> list = Arrays.asList(
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Пикник на обочине", 100),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );
        AbstractMealStorage storage = new MapMealStorage();
        list.forEach(storage::add);
        return storage;
    }
}
