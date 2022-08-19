package com.matthew.schedule.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matthew.schedule.Controller.ActivityController;
import com.matthew.schedule.constant.DayOfWeek;
import com.matthew.schedule.dto.ActivitiesPostDto;
import com.matthew.schedule.dto.ActivityPostDto;
import com.matthew.schedule.entities.Activity;
import com.matthew.schedule.exceptions.ActivityNotFoundException;
import com.matthew.schedule.services.ActivityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class ActivityControllerTest {
    @Mock
    private ActivityService activityService;

    ActivitiesPostDto activitiesPostDto;

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
    }

    @Test
    public void testCreateActivity() {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new ActivityController(activityService)).build();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String str = objectMapper.writeValueAsString(activitiesPostDto);
            mockMvc.perform(MockMvcRequestBuilders.post("/activity")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(activitiesPostDto)))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateActivityThrowHttpMessageNotReadableException() {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new ActivityController(activityService)).build();
        try {
            String str = "{\"activities\":[{\"dayOfWeek\":\"123\",\"event\":\"Fishing\"}]}";

            mockMvc.perform(MockMvcRequestBuilders.post("/activity")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(str))
                    .andExpect(status().is4xxClientError());
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals(HttpMessageNotReadableException.class, e);
        }
    }

    @Test
    public void testAddActivity() {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new ActivityController(activityService)).build();
        try {
            mockMvc.perform(MockMvcRequestBuilders.put("/activity?dayOfWeek=Thursday&event=Fishing"))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetActivity() {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new ActivityController(activityService)).build();
        //given
        Activity activity = new Activity(DayOfWeek.Monday, "Fishing");
        given(activityService.queryCalendar("Monday")).willReturn(activity);
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/activity/Monday")
                            .param("dayOfWeek", "Monday"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", is("Fishing")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
