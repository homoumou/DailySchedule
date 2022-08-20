package com.matthew.schedule.entities;

import com.matthew.schedule.constant.DayOfWeek;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaturdayActivity extends Activity{
    private DayOfWeek dayOfWeek;
    private String event;

    public SaturdayActivity() {
        this.dayOfWeek = DayOfWeek.Saturday;
        this.event = "Resting";
    }

    public SaturdayActivity(DayOfWeek dayOfWeek, String event) {
        this.dayOfWeek = dayOfWeek;
        this.event = event;
    }


    @Override
    public String printEvent() {
        return dayOfWeek + ":" + event;
    }
}
