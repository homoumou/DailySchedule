package com.matthew.schedule.utils;

import com.matthew.schedule.constant.DayOfWeek;
import com.matthew.schedule.entities.Activity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
@Slf4j
@Component
public class CalendarPrinter {
    public void printCalendar(Map<DayOfWeek, Activity> weeklyCalendar) {
       log.info(weeklyCalendar.toString());
    }
}
