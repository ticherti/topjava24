package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepository.class);
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    //todo дополнительно: попробуйте сделать реализацию атомарной (те учесть коллизии при одновременном изменении еды одного пользователя)

    //    now all the list is for one mock user
//    {
//        MealsUtil.meals.forEach(meal -> save(SecurityUtil.authUserId(), meal, meal.getId()));
//    }

    @Override
    public Meal save(int userId, Meal meal) {
        log.info("save {}, {}", userId, meal);
        repository.putIfAbsent(userId, new ConcurrentHashMap<>() );
        Map<Integer, Meal> userMeals = getUserMeals(userId);

        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            userMeals.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return userMeals.computeIfPresent(meal.getId(), (mealId, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int userId, int id) {
        log.info("delete {}, {}", userId, id);
        return getUserMeals(userId).remove(id) != null;
    }

    @Override
    public Meal get(int userId, int id) {
        log.info("get {}, {}", userId, id);
        return getUserMeals(userId).get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        log.info("getAll {}", userId);
        return getAllFiltered(userId, meal -> true);
    }

    public List<Meal> getAllFiltered(int userId, Predicate<Meal> predicate) {
        log.info("getAll filtered {}", userId);
        return getUserMeals(userId).values().stream()
                .filter(predicate)
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    private Map<Integer, Meal> getUserMeals(int userId) {
        return Optional.ofNullable(repository.get(userId)).orElse(Collections.emptyMap());
    }
}

