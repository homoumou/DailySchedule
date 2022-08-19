package com.matthew.schedule.config;

import com.matthew.schedule.constant.DayOfWeek;
import com.matthew.schedule.entities.Activity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class WeeklyCalendar {
    @Bean
    public Map<DayOfWeek, Activity> weeklyCalender() {
        Map<DayOfWeek, Activity> weeklyCalender = new HashMap<>();
        weeklyCalender.put(DayOfWeek.Monday, null);
        weeklyCalender.put(DayOfWeek.Tuesday, null);
        weeklyCalender.put(DayOfWeek.Wednesday, null);
        weeklyCalender.put(DayOfWeek.Thursday, null);
        weeklyCalender.put(DayOfWeek.Friday, null);
        weeklyCalender.put(DayOfWeek.Saturday, null);
        weeklyCalender.put(DayOfWeek.Sunday, null);
        return weeklyCalender;
    }
}
