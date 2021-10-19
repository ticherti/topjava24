package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface MealRepository {
    // null if updated meal do not belong to userId
    Meal save(int authId, Meal meal, int id);

    // false if meal do not belong to userId
    boolean delete(int authId, int id);

    // null if meal do not belong to userId
    Meal get(int authId, int id);

    // ORDERED dateTime desc
//    todo maybe it can be List? if so change it for all the layers
    Collection<Meal> getAll(int authId);
}
