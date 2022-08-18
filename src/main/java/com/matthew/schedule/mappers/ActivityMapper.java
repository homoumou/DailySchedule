package com.matthew.schedule.mappers;

import com.matthew.schedule.dto.ActivityPostDto;
import com.matthew.schedule.dto.ActivityPutDto;
import com.matthew.schedule.entities.Activity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActivityMapper {
    Activity toEntity (ActivityPutDto activityPutDto);
    Activity toEntity (ActivityPostDto activityPostDto);
}
