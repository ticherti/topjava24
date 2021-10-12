package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealStorage {

    Meal add(Meal meal);
    Meal get(long id);
    Meal update(Meal meal);
    void delete(long id);
    List<Meal> getAll();
}
