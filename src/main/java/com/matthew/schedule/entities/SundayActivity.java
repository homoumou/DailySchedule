package com.matthew.schedule.entities;

import com.matthew.schedule.constant.DayOfWeek;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SundayActivity extends Activity{
    private DayOfWeek dayOfWeek;
    private String event;

    public SundayActivity() {
        this.dayOfWeek = DayOfWeek.Sunday;
        this.event = "Resting";
    }

    public SundayActivity(DayOfWeek dayOfWeek, String event) {
        this.dayOfWeek = dayOfWeek;
        this.event = event;
    }

    @Override
    public String printEvent() {
        return dayOfWeek + ":" + event;
    }
}
