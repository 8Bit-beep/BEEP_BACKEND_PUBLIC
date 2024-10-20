package com.beep.beep.domain.student.presentation.dto.response;

import com.beep.beep.domain.attendLog.domain.enums.TimeTable;
import com.beep.beep.domain.user.domain.enums.RoomCode;

public record TodayLastLogs(
        TimeTable timeTable,
        RoomCode roomName
) {

}
