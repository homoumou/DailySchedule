package com.matthew.schedule.services;

import com.matthew.schedule.constant.DayOfWeek;
import com.matthew.schedule.dto.ActivitiesPostDto;
import com.matthew.schedule.dto.ActivityPostDto;
import com.matthew.schedule.entities.Activity;
import com.matthew.schedule.exceptions.ActivityNotFoundException;
import com.matthew.schedule.exceptions.DayOfWeekNotFoundException;
import com.matthew.schedule.mappers.ActivityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ActivityService {
    @Resource(name = "weeklyCalender")
    private Map<DayOfWeek, Activity> weeklyCalender;
    private final ActivityMapper activityMapper;

    public Map<DayOfWeek, Activity> addWeeklyCalender(DayOfWeek dayOfWeek, String event) {
        weeklyCalender.put(dayOfWeek, new Activity(dayOfWeek, event));
        return weeklyCalender;
    }

    public Map<DayOfWeek, Activity> createWeeklyCalender(ActivitiesPostDto activitiesPostDto) {
        List<ActivityPostDto> activityArray = activitiesPostDto.getActivities();
        for (ActivityPostDto Activity : activityArray) {
            weeklyCalender.put(Activity.getDayOfWeek(), new Activity(Activity.getDayOfWeek(), Activity.getEvent()));
        }
        return weeklyCalender;
    }

    public Activity queryCalender(String dayOfWeek) {
        Activity activity;
        try{
            DayOfWeek.valueOf(dayOfWeek);
        }
        catch (Exception e) {
            throw new DayOfWeekNotFoundException("Cant find day "+ dayOfWeek);
        }
        activity = weeklyCalender.get(DayOfWeek.valueOf(dayOfWeek));
        if(activity == null){
            throw new ActivityNotFoundException("Cant find activity in " + dayOfWeek);
        }
        return activity;
    }
}
