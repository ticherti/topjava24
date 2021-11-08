package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaMealRepository implements MealRepository {

    private final CrudMealRepository crudMealRepository;
    private final CrudUserRepository crudUserRepository;

    public DataJpaMealRepository(CrudMealRepository crudMealRepository, CrudUserRepository crudUserRepository) {
        this.crudMealRepository = crudMealRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Transactional
    @Override
    public Meal save(Meal meal, int userId) {
        User user = crudUserRepository.getById(userId);
        meal.setUser(user);
        if (meal.isNew()) {
            return crudMealRepository.save(meal);
        } else if (get(meal.id(), userId) == null) {
            return null;
        }
        return crudMealRepository.save(meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudMealRepository.delete(id, userId) != 0;
    }

    @Override
    public Meal get(int id, int userId) {
//        todo Ask: why crudMealRepository.getById(id) doesn't work in next comment line, but findBy does?
//        return crudMealRepository.findById(id).orElse(null);
        return crudMealRepository.findByIdAndUserId(id, userId);
    }
    @Override
    public List<Meal> getAll(int userId) {
        return crudMealRepository.getAllByUserIdOrderByDateTimeDesc(userId);
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return crudMealRepository.getBetween(startDateTime, endDateTime, userId);
    }
}
