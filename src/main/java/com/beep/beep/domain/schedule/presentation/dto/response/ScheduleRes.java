package com.beep.beep.domain.schedule.presentation.dto.response;

import com.beep.beep.domain.attendLog.domain.enums.TimeTable;
import com.beep.beep.domain.schedule.domain.Schedule;

public record ScheduleRes(
    String cause,
    String room
) {

}
