package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.exception.StorageException;
import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapStorage implements MealStorage {
    private final Map<Long, Meal> storage;

    private static volatile long idCount = 0;

    public MapStorage() {
        this.storage = new ConcurrentHashMap<>();
    }

    @Override
    public Meal add(Meal meal) {
//            TODO ASK should I check for existence first, and what should I return if it exists like in the if() here
//         Если meal == null, это ошибка, не надо ее игнорировать. Пусть лучше вылетит исключение.
//         - which one exactly. Should I put some special exception?
        if (isExist(meal.getId())){
            return null;
        }
        synchronized (this) {
            long id = ++idCount;
            meal.setId(id);
            storage.put(id, meal);
        }
        return meal;
    }

    @Override
    public Meal get(long id) {
        if (!isExist(id)) {
            return null;
        }
        return storage.get(id);
    }

    @Override
    public Meal update(Meal meal) {
        long id = meal.getId();
        if (!isExist(id)) {
            return null;
        }
//        TODO check if better change it for a specific concurrent map method
        synchronized (this) {
            storage.put(id, meal);
        }
        return meal;
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

    private boolean isExist(long id) {
        if(storage.containsKey(id)){
            return true;
        }
        else return false;
    }
}
