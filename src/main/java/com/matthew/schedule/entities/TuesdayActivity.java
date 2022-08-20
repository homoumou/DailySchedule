package com.matthew.schedule.entities;

import com.matthew.schedule.constant.DayOfWeek;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TuesdayActivity extends Activity{
    private DayOfWeek dayOfWeek;
    private String event;

    public TuesdayActivity() {
        this.dayOfWeek = DayOfWeek.Tuesday;
        this.event = "Hiking";
    }

    public TuesdayActivity(DayOfWeek dayOfWeek, String event) {
        this.dayOfWeek = dayOfWeek;
        this.event = event;
    }
    @Override
    public String printEvent() {
        return dayOfWeek + ":" + event;
    }
}
