package com.matthew.schedule.mappers;

import com.matthew.schedule.Factory.ActivityFactory;
import com.matthew.schedule.dto.ActivityPostDto;
import com.matthew.schedule.dto.ActivityPutDto;
import com.matthew.schedule.entities.Activity;
import org.mapstruct.Mapper;

import javax.annotation.Resource;

@Mapper(componentModel = "spring")
public class ActivityMapper {
    @Resource
    private ActivityFactory activityFactory;
    public Activity toEntity (ActivityPutDto activityPutDto) {
        Activity activity = activityFactory.createActivity(activityPutDto.getDayOfWeek());
        activity.setEvent(activityPutDto.getEvent());
        return activity;
    };

    public Activity toEntity(ActivityPostDto activityPostDto) {
        Activity activity = activityFactory.createActivity(activityPostDto.getDayOfWeek());
        activity.setEvent(activityPostDto.getEvent());
        return activity;
    };
}
