package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface AbstractStorage {

    void add(Meal meal);
    Meal get(long id);
    void update(Meal meal);
    void delete(long id);
    List<Meal> getAll();
}
