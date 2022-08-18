package com.matthew.schedule.Controller;

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

@Controller
@RequiredArgsConstructor
public class ActivityController {
    private final ActivityService activityService;

    @PostMapping("/activity")
    public ResponseEntity<Void> createActivity(@RequestBody ActivitiesPostDto activitiesPostDto){
        activityService.createWeeklyCalender(activitiesPostDto);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/activity")
    public ResponseEntity<Void> addActivity(@RequestParam String dayOfWeek, @RequestParam String event) {
        activityService.addWeeklyCalender(dayOfWeek, event);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/activity/{dayOfWeek}")
    public ResponseEntity<String> getActivityByDayOfWeek(@PathVariable String dayOfWeek){
        Activity activity = activityService.queryCalender(dayOfWeek);
        return ResponseEntity.ok(activityService.queryCalender(dayOfWeek).getEvent());
    } 
}
