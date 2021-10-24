package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL1_ID = START_SEQ + 2;
    public static final int MEAL2_ID = START_SEQ + 3;
    public static final int MEAL3_ID = START_SEQ + 4;
    public static final int MEAL4_ID = START_SEQ + 3;
    public static final LocalDateTime MEAL1_LD = LocalDateTime.parse("2021-10-24T10:00:00");

    public static final Meal MEAL1 = new Meal(MEAL1_ID, MEAL1_LD, "English tea", 100);
    public static final Meal MEAL2 = new Meal(MEAL2_ID, LocalDateTime.parse("2021-10-25T11:00:00"), "English breakfast", 500);
    public static final Meal MEAL3 = new Meal(MEAL3_ID, LocalDateTime.parse("2021-10-26T10:00:00"), "Русский мерзкий чай с молоком", 150);
    public static final Meal MEAL4 = new Meal(4, LocalDateTime.parse("2021-10-27T11:00:00"), "Русские блины", 550);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.now(), "test new meal", 500);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(MEAL1);
        updated.setDateTime(LocalDateTime.of(2030, 1, 1, 0, 0));
        updated.setDescription("Updated meal new description");
        updated.setCalories(330);
        return updated;
    }
}
