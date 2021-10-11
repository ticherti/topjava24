package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Meal {
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private long id;

    private static volatile long idCount = 0;

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(dateTime, description, calories, true);
    }

    public Meal(LocalDateTime dateTime, String description, int calories, long id) {
        this(dateTime, description, calories, false);
        this.id = id;
    }

    private Meal(LocalDateTime dateTime, String description, int calories, boolean isNew) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        if (isNew){
            synchronized (this) {
                idCount++;
                this.id = idCount;
            }
        }
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }
}
