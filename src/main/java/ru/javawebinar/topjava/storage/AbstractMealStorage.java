package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface AbstractMealStorage {

    void add(Meal meal);
    Meal get(long id);
    void update(Meal meal);
    void delete(long id);
    List<Meal> getAll();
}
