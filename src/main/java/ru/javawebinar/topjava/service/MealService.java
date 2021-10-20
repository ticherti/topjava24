package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.List;
import java.util.function.Predicate;

@Service
public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal save(int authId, Meal meal) {
        return repository.save(authId, meal);
    }

    public boolean delete(int authId, int id) {
        return repository.delete(authId, id);
    }

    public Meal get(int authId, int mealId) {
        return repository.get(authId, mealId);
    }

    public List<Meal> getAll(int authId) {
        return repository.getAll(authId);
    }
    public List<Meal> getAllFiltered(int authId, Predicate<Meal> predicate) {
        return repository.getAllFiltered(authId, predicate);
    }
}