package com.matthew.schedule.Controller;

import com.matthew.schedule.constant.DayOfWeek;
import com.matthew.schedule.dto.ActivitiesPostDto;
import com.matthew.schedule.dto.ActivityPutDto;
import com.matthew.schedule.entities.Activity;
import com.matthew.schedule.services.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ActivityController {
    private final ActivityService activityService;

    @PostMapping("/activity")
    public ResponseEntity<Map<DayOfWeek, Activity>> createActivity(@RequestBody ActivitiesPostDto activitiesPostDto){
        return ResponseEntity.ok(activityService.createWeeklyCalendar(activitiesPostDto));
    }

    @PutMapping("/activity")
    public ResponseEntity<Map<DayOfWeek, Activity>> addActivity(@RequestParam DayOfWeek dayOfWeek, @RequestParam String event) {
        return ResponseEntity.ok(activityService.addWeeklyCalendar(dayOfWeek, event));
    }

    @GetMapping("/activity/{dayOfWeek}")
    public ResponseEntity<String> getActivityByDayOfWeek(@PathVariable String dayOfWeek){
        Activity activity = activityService.queryCalendar(dayOfWeek);
        return ResponseEntity.ok(activity.getDayOfWeek() + " : " + activity.getEvent());
    } 
}
