package ru.javawebinar.topjava.service;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDate;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {
    private static final Logger log = getLogger(MealService.class);

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal save(int userId, Meal meal) {
        return repository.save(userId, meal);
    }

    public Meal update(int userId, Meal meal, int id) {
        return checkNotFoundWithId(repository.save(userId, meal), id);
    }

    public void delete(int userId, int id) {
        checkNotFoundWithId(repository.delete(userId, id), id);
    }

    public Meal get(int userId, int id) {
        return checkNotFoundWithId(repository.get(userId, id), id);
    }

    public List<Meal> getAll(int userId) {
        return repository.getAll(userId);
    }

    public List<Meal> getAllFiltered(int userId, LocalDate startDate, LocalDate endDate) {
        return repository.getAllFiltered(userId, startDate, endDate);
    }
}