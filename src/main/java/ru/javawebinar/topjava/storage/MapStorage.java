package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.exception.StorageException;
import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage implements AbstractStorage {
    private final Map<Long, Meal> storage;

    public MapStorage() {
        this.storage = new HashMap<>();
    }

    @Override
    public void add(Meal meal) {
        if (meal == null){
            return;
        }
        storage.put(meal.getId(), meal);
    }

    @Override
    public Meal get(long id) {
        checkExistence(id);
        return storage.get(id);
    }

    @Override
    public void update(Meal meal) {
        long id = meal.getId();
        checkExistence(id);
        storage.put(id, meal);
    }

    @Override
    public void delete(long id) {
        storage.remove(id);
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
