package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_MEAL1_ID = START_SEQ + 2;
    public static final int USER_MEAL2_ID = START_SEQ + 3;
    public static final int ADMIN_MEAL3_ID = START_SEQ + 4;
    public static final int NOBODY_MEAL_ID = 6;
    public static final LocalDateTime MEAL1_DT = LocalDateTime.of(2021, 10, 24, 10, 0);

    public static final Meal user_meal1 = new Meal(USER_MEAL1_ID, MEAL1_DT, "English tea", 100);
    public static final Meal user_meal2 = new Meal(USER_MEAL2_ID, LocalDateTime.of(2021, 10, 25, 11, 0), "English breakfast", 500);
    public static final Meal admin_meal1 = new Meal(ADMIN_MEAL3_ID, LocalDateTime.of(2021, 10, 26, 10, 0), "Русский мерзкий чай с молоком", 150);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2121, 10, 1, 0, 0), "test new meal", 500);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(user_meal1);
        updated.setDateTime(LocalDateTime.of(2030, 1, 1, 0, 0));
        updated.setDescription("Updated meal new description");
        updated.setCalories(330);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}
