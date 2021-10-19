package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    //todo дополнительно: попробуйте сделать реализацию атомарной
// (те учесть коллизии при одновременном изменении еды одного пользователя)

    //    now all the list is for one mock user
    {
        MealsUtil.meals.forEach(meal -> save(SecurityUtil.authUserId(), meal, meal.getId()));
    }

    @Override
    public Meal save(int authId, Meal meal, int id) {
        log.info("save {}, {}", authId, meal);
        if (repository.get(authId) == null) {
            repository.put(authId, new ConcurrentHashMap<>());
        }
        Map<Integer, Meal> userMeals = getUserMeals(authId);

        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            userMeals.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return userMeals.computeIfPresent(id, (mealId, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int authId, int id) {
        log.info("delete {}, {}", authId, id);
        return getUserMeals(authId).remove(id) != null;
    }

    @Override
    public Meal get(int authId, int id) {
        log.info("get {}, {}", authId, id);
        return getUserMeals(authId).get(id);
    }

    @Override
    public Collection<Meal> getAll(int authId) {
        log.info("getAll {}", authId);
        return getUserMeals(authId).values().stream()
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    private Map<Integer, Meal> getUserMeals(int authId) {
        return Optional.ofNullable(repository.get(authId)).orElse(Collections.emptyMap());
    }
}

