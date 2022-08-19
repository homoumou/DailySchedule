package com.matthew.schedule.dto;

import com.matthew.schedule.constant.DayOfWeek;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivityPutDto {

    @NonNull
    private DayOfWeek dayOfWeek;
    @NonNull
    private String event;
}
