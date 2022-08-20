package com.matthew.schedule.services;

import com.matthew.schedule.Factory.ActivityFactory;
import com.matthew.schedule.constant.DayOfWeek;
import com.matthew.schedule.dto.ActivitiesPostDto;
import com.matthew.schedule.dto.ActivityPostDto;
import com.matthew.schedule.entities.Activity;
import com.matthew.schedule.exceptions.ActivityNotFoundException;
import com.matthew.schedule.exceptions.DayOfWeekNotFoundException;
import com.matthew.schedule.mappers.ActivityMapper;
import com.matthew.schedule.utils.CalendarPrinter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActivityService {
    @Resource(name = "weeklyCalender")
    private Map<DayOfWeek, Activity> weeklyCalendar;
    private final ActivityMapper activityMapper;
    private final CalendarPrinter calendarPrinter;
    private final ActivityFactory activityFactory;

    public Map<DayOfWeek, Activity> addWeeklyCalendar(DayOfWeek dayOfWeek, String event) {
        log.info("weeklyCalender updated");
        Activity activity = activityFactory.createActivity(dayOfWeek);
        activity.setEvent(event);
        weeklyCalendar.put(dayOfWeek, activity);
        calendarPrinter.printCalendar(weeklyCalendar);
        return weeklyCalendar;
    }

    public Map<DayOfWeek, Activity> createWeeklyCalendar(ActivitiesPostDto activitiesPostDto) {
        log.info("weeklyCalender created");
        List<ActivityPostDto> activityArray = activitiesPostDto.getActivities();
        for (ActivityPostDto activityPostDto : activityArray) {
            Activity activity = activityMapper.toEntity(activityPostDto);
            weeklyCalendar.put(activity.getDayOfWeek(), activity);
        }
        calendarPrinter.printCalendar(weeklyCalendar);
        return weeklyCalendar;
    }

    public Activity queryCalendar(String dayOfWeek) {
        log.info("query Calender");
        Activity activity;
        try{
            DayOfWeek.valueOf(dayOfWeek);
        }
        catch (Exception e) {
            throw new DayOfWeekNotFoundException("Cant find day "+ dayOfWeek);
        }
        activity = weeklyCalendar.get(DayOfWeek.valueOf(dayOfWeek));
        if(activity == null){
            throw new ActivityNotFoundException("Cant find activity in " + dayOfWeek);
        }
        return activity;
    }
}
