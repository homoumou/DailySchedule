package com.matthew.schedule.entities;

import com.matthew.schedule.constant.DayOfWeek;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MondayActivity extends Activity{
    private DayOfWeek dayOfWeek;
    private String event;

    public MondayActivity() {
        this.dayOfWeek = DayOfWeek.Monday;
        this.event = "Fishing";
    }

    public MondayActivity(DayOfWeek dayOfWeek, String event) {
        this.dayOfWeek = dayOfWeek;
        this.event = event;
    }

    @Override
    public String printEvent() {
        return dayOfWeek + ":" + event;
    }
}
