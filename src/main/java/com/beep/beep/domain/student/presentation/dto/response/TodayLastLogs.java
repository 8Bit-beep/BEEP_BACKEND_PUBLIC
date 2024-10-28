package com.beep.beep.domain.student.presentation.dto.response;

import com.beep.beep.domain.attendLog.domain.enums.TimeTable;
import com.beep.beep.domain.user.domain.enums.RoomCode;

import java.time.LocalDateTime;

/**
 * 오늘 저장된 로그들 dto
 * */
public record TodayLastLogs(
        TimeTable timeTable,
        LocalDateTime lastUpdated,
        RoomCode roomName
) {

}
