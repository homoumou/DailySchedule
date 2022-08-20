package com.matthew.schedule.entities;

import com.matthew.schedule.constant.DayOfWeek;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WednesdayActivity extends Activity{
    private DayOfWeek dayOfWeek;
    private String event;

    public WednesdayActivity() {
        this.dayOfWeek = DayOfWeek.Wednesday;
        this.event = "Swimming";
    }

    public WednesdayActivity(DayOfWeek dayOfWeek, String event) {
        this.dayOfWeek = dayOfWeek;
        this.event = event;
    }
    @Override
    public String printEvent() {
        return dayOfWeek + ":" + event;
    }
}
