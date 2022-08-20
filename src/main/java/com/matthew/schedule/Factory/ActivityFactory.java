package com.matthew.schedule.Factory;

import com.matthew.schedule.constant.DayOfWeek;
import com.matthew.schedule.entities.Activity;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class ActivityFactory {
    @Resource(name = "weeklyCalender")
    private Map<DayOfWeek, Activity> weeklyCalendar;
    public Activity createActivity(DayOfWeek dayOfWeek) {
        return weeklyCalendar.get(dayOfWeek);
    }
}
