package com.matthew.schedule.config;

import com.matthew.schedule.constant.DayOfWeek;
import com.matthew.schedule.entities.Activity;
import com.matthew.schedule.entities.FridayActivity;
import com.matthew.schedule.entities.MondayActivity;
import com.matthew.schedule.entities.SaturdayActivity;
import com.matthew.schedule.entities.SundayActivity;
import com.matthew.schedule.entities.ThursdayActivity;
import com.matthew.schedule.entities.TuesdayActivity;
import com.matthew.schedule.entities.WednesdayActivity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class WeeklyCalendar {
    @Bean
    public Map<DayOfWeek, Activity> weeklyCalender() {
        Map<DayOfWeek, Activity> weeklyCalender = new HashMap<>();
        weeklyCalender.put(DayOfWeek.Monday, new MondayActivity());
        weeklyCalender.put(DayOfWeek.Tuesday, new TuesdayActivity());
        weeklyCalender.put(DayOfWeek.Wednesday, new WednesdayActivity());
        weeklyCalender.put(DayOfWeek.Thursday, new ThursdayActivity());
        weeklyCalender.put(DayOfWeek.Friday, new FridayActivity());
        weeklyCalender.put(DayOfWeek.Saturday, new SaturdayActivity());
        weeklyCalender.put(DayOfWeek.Sunday, new SundayActivity());
        return weeklyCalender;
    }
}
