package com.matthew.schedule.services;

import com.matthew.schedule.dto.ActivitiesPostDto;
import com.matthew.schedule.dto.ActivityPostDto;
import com.matthew.schedule.dto.ActivityPutDto;
import com.matthew.schedule.entities.Activity;
import com.matthew.schedule.exceptions.ActivityNotFoundException;
import com.matthew.schedule.mappers.ActivityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {
    Map<String, Activity> weeklyCalender = new HashMap<>();
    private final ActivityMapper activityMapper;

    public Map<String, Activity> addWeeklyCalender(String dayOfWeek, String event) {
        weeklyCalender.put(dayOfWeek, new Activity(dayOfWeek, event));
        return weeklyCalender;
    }

    public Map<String, Activity> createWeeklyCalender(ActivitiesPostDto activitiesPostDto) {
        List<ActivityPostDto> activityArray = activitiesPostDto.getActivities();
        weeklyCalender = activityArray.stream().collect(Collectors.toMap(e -> e.getDayOfWeek(), e -> activityMapper.toEntity(e)));
        return weeklyCalender;
    }

    public Activity queryCalender(String dayOfWeek) {
        Activity activity = weeklyCalender.get(dayOfWeek);
        if(activity == null){
            throw new ActivityNotFoundException("Cant find activity in " + dayOfWeek);
        }
        return weeklyCalender.get(dayOfWeek);
    }

    public Map<String, Activity> getWeeklyCalender(){
        return weeklyCalender;
    }
}
