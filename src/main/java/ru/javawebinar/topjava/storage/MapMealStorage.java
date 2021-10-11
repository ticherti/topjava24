package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.exception.StorageException;
import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapMealStorage implements AbstractMealStorage {
    private final Map<Long, Meal> storage;

    private static volatile long idCount = 0;

    public MapMealStorage() {
        this.storage = new ConcurrentHashMap<>();
    }

    @Override
    public void add(Meal meal) {
        if (meal == null) {
            return;
        }
        synchronized (this) {
            long id = ++idCount;
            meal.setId(id);
            storage.put(id, meal);
        }
    }

    @Override
    public Meal get(long id) {
        checkExistence(id);
        return storage.get(id);
    }

    @Override
    public void update(Meal meal) {
        long id = meal.getId();
//        TODO check if better change it for a specific concurrent map method
        checkExistence(id);
        synchronized (this) {
            storage.put(id, meal);
        }
    }

    @Override
    public void delete(long id) {
        synchronized (this) {
            storage.remove(id);
        }
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(storage.values());
    }

    private void checkExistence(long id) {
        if (!storage.containsKey(id)) {
            throw new StorageException("No such meal");
        }
    }
}
