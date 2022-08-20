package com.matthew.schedule.entities;

import com.matthew.schedule.constant.DayOfWeek;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Activity {
    private DayOfWeek dayOfWeek;
    private String event;

    public abstract  String printEvent();
}
