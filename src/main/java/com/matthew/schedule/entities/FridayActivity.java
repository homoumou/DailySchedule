package com.matthew.schedule.entities;

import com.matthew.schedule.constant.DayOfWeek;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FridayActivity extends Activity{
    private DayOfWeek dayOfWeek;
    private String event;

    public FridayActivity() {
        this.dayOfWeek = DayOfWeek.Friday;
        this.event = "Fishing";
    }

    public FridayActivity(DayOfWeek dayOfWeek, String event) {
        this.dayOfWeek = dayOfWeek;
        this.event = event;
    }

    @Override
    public String printEvent() {
        return dayOfWeek + ":" + event;
    }
}
