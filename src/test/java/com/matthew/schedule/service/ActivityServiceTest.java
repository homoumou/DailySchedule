package com.matthew.schedule.service;

import com.matthew.schedule.constant.DayOfWeek;
import com.matthew.schedule.dto.ActivitiesPostDto;
import com.matthew.schedule.dto.ActivityPostDto;
import com.matthew.schedule.entities.Activity;
import com.matthew.schedule.exceptions.ActivityNotFoundException;
import com.matthew.schedule.exceptions.DayOfWeekNotFoundException;
import com.matthew.schedule.services.ActivityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ActivityServiceTest {

    @Autowired
    private ActivityService activityService;
    ActivitiesPostDto activitiesPostDto;
    Map<DayOfWeek, Activity> expectedCalender;

    @BeforeEach
    public void setUp(){
        List<ActivityPostDto> activities = new ArrayList<>();
        activities.add(new ActivityPostDto(DayOfWeek.Monday, "Fishing"));
        activities.add(new ActivityPostDto(DayOfWeek.Tuesday, "Hiking"));
        activities.add(new ActivityPostDto(DayOfWeek.Wednesday, "Swimming"));
        activities.add(new ActivityPostDto(DayOfWeek.Friday, "Fishing"));
        activities.add(new ActivityPostDto(DayOfWeek.Saturday, "Resting"));
        activities.add(new ActivityPostDto(DayOfWeek.Sunday, "Resting"));
        activitiesPostDto = ActivitiesPostDto.builder().activities(activities).build();

        expectedCalender = new HashMap<>();
        expectedCalender.put(DayOfWeek.Monday, new Activity(DayOfWeek.Monday, "Fishing"));
        expectedCalender.put(DayOfWeek.Tuesday, new Activity(DayOfWeek.Tuesday, "Hiking"));
        expectedCalender.put(DayOfWeek.Wednesday, new Activity(DayOfWeek.Wednesday, "Swimming"));
        expectedCalender.put(DayOfWeek.Thursday, null);
        expectedCalender.put(DayOfWeek.Friday, new Activity(DayOfWeek.Friday, "Fishing"));
        expectedCalender.put(DayOfWeek.Saturday, new Activity(DayOfWeek.Saturday, "Resting"));
        expectedCalender.put(DayOfWeek.Sunday, new Activity(DayOfWeek.Sunday, "Resting"));

        List<ActivityPostDto> invalidActivities = new ArrayList<>();
        activities.add(new ActivityPostDto(DayOfWeek.Monday, "Fishing"));
    }

    @Test
    @DisplayName("addWeeklyCalender should return updatedCalender")
    void addWeeklyCalenderShouldReturnUpdatedCalender(){
        Map<DayOfWeek, Activity> updatedCalender = activityService.addWeeklyCalendar(DayOfWeek.Monday, "Fishing");
        Activity expectedActivity = new Activity(DayOfWeek.Monday, "Fishing");
        assertEquals(expectedActivity, updatedCalender.get(DayOfWeek.Monday));
    }

    @Test
    @DisplayName("addWeeklyCalender should return updatedCalender")
    void createWeeklyCalenderShouldReturnUpdatedCalender(){
        Map<DayOfWeek, Activity> updatedCalender = activityService.createWeeklyCalendar(activitiesPostDto);
        assertEquals(expectedCalender, updatedCalender);
    }

    @Test
    @DisplayName("queryCalender should return ExpectedActivity")
    void queryCalenderShouldReturnExpectedActivity(){
        activityService.addWeeklyCalendar(DayOfWeek.Monday, "Fishing");
        Activity returnActivity = activityService.queryCalendar("Monday");
        Activity expectedActivity = new Activity(DayOfWeek.Monday, "Fishing");
        assertEquals(expectedActivity, returnActivity);
    }

    @Test
    @DisplayName("queryCalender should throw DayOfWeekNotFoundException given invalid day of week")
    void queryCalenderThrowDayOfWeekNotFoundExceptionGivenInvalidDayOfWeek(){
       assertThrows(DayOfWeekNotFoundException.class, ()->activityService.queryCalendar("111"));
    }

    @Test
    @DisplayName("queryCalender should throw ActivityNotFoundException when activity is null")
    void queryCalenderThrowActivityNotFoundExceptionWhenActivityIsNull(){
        assertThrows(ActivityNotFoundException.class, ()->activityService.queryCalendar("Monday"));
    }

}
