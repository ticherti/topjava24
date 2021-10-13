package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryMealStorage implements MealStorage {
    private final Map<Integer, Meal> storage;

    private AtomicInteger idCount = new AtomicInteger(0);

    public InMemoryMealStorage() {
        this.storage = new ConcurrentHashMap<>();
    }

    @Override
    public Meal add(Meal meal) {
        meal.setId(idCount.incrementAndGet());
        storage.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public Meal get(int id) {
        return storage.get(id);
    }

    @Override
    public Meal update(Meal meal) {
        return storage.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public void delete(int id) {
        storage.remove(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(storage.values());
    }
}
