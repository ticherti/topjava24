package ru.javawebinar.topjava.util;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalTime;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

public class TimeConverter implements Converter<String, LocalTime> {

    @Override
    public LocalTime convert(String from) {
        return parseLocalTime(from);
    }
}
