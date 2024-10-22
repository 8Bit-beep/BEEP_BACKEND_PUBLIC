package com.beep.beep.domain.attendLog.presentation.dto.response;

import com.beep.beep.domain.attendLog.domain.AttendLog;
import com.beep.beep.domain.attendLog.domain.enums.TimeTable;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.enums.RoomCode;

import java.time.LocalDateTime;
import java.util.List;

public record MonthlyAttendLogsRes(
        Long id,
        Integer grade,
        Integer cls,
        Integer num,
        String studentName,
        RoomCode roomName,
        boolean isExist,
        LocalDateTime lastUpdated, // 그냥 회원가입하면 LocalDateTime.MIN
        LocalDateTime recordedDt,
        TimeTable timeTable) {
    public static List<MonthlyAttendLogsRes> of(List<AttendLog> attendLogs) {
        return attendLogs.parallelStream()
                .map(MonthlyAttendLogsRes::of)
                .toList();
    }

    public static MonthlyAttendLogsRes of(AttendLog attendLog) {
        User user = attendLog.getUser();

        return new MonthlyAttendLogsRes(
                attendLog.getId(),
                user.getGrade(),
                user.getCls(),
                user.getNum(),
                user.getName(),
                attendLog.getCurrentRoom(),
                user.getFixedRoom().equals(attendLog.getCurrentRoom()),
                user.getLastUpdated() == null ? LocalDateTime.MIN : user.getLastUpdated(),
                attendLog.getCurrentDt(),
                attendLog.getTimeTable()
        );
    }
}
