package com.beep.beep.domain.attendLog.presentation.dto.response;

import com.beep.beep.domain.attendLog.domain.AttendLog;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.enums.RoomCode;

import java.time.LocalDateTime;
import java.util.List;

public record AttendLogRes(
        Long id,
        Integer grade,
        Integer cls,
        Integer num,
        String studentName,
        RoomCode roomName,
        boolean isExist,
        LocalDateTime lastUpdated, // 그냥 회원가입하면 LocalDateTime.MIN
        LocalDateTime recordedDt) {
    public static List<AttendLogRes> of(List<AttendLog> attendLogs) {
        return attendLogs.parallelStream()
                .map(AttendLogRes::of)
                .toList();
    }

    public static AttendLogRes of(AttendLog attendLog) {
        User user = attendLog.getUser();
        System.out.println(attendLog.getCurrentRoom());

        return new AttendLogRes(
                attendLog.getId(),
                user.getGrade(),
                user.getCls(),
                user.getNum(),
                user.getName(),
                attendLog.getCurrentRoom(),
                user.getFixedRoom().equals(attendLog.getCurrentRoom()),
                user.getLastUpdated() == null ? LocalDateTime.MIN : user.getLastUpdated(),
                attendLog.getCurrentDt()
        );
    }
}
