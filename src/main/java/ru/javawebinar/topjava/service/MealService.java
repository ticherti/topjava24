package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.List;

@Service
public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal create(int authId, Meal meal, int id) {
        return repository.save(authId, meal, id);
    }

    public void delete(int authId, int id) {
        repository.delete(authId, id);
    }

    public Meal get(int authId, int mealId) {
        return repository.get(authId, mealId);
    }

    public List<Meal> getAll(int authId) {
        return (List<Meal>) repository.getAll(authId);
    }
}