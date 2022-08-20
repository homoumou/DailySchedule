package com.matthew.schedule.entities;

import com.matthew.schedule.constant.DayOfWeek;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ThursdayActivity extends Activity{
    private DayOfWeek dayOfWeek;
    private String event;

    public ThursdayActivity() {
        this.dayOfWeek = DayOfWeek.Thursday;
        this.event = null;
    }

    public ThursdayActivity(DayOfWeek dayOfWeek, String event) {
        this.dayOfWeek = dayOfWeek;
        this.event = event;
    }
    @Override
    public String printEvent() {
        return dayOfWeek + ":" + event;
    }
}
