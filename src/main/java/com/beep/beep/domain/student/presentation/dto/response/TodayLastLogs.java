package com.beep.beep.domain.student.presentation.dto.response;

import com.beep.beep.domain.attendLog.domain.enums.TimeTable;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.enums.RoomCode;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

import static com.beep.beep.domain.user.domain.enums.RoomCode.NOTFOUND;

/**
 * 오늘 저장된 로그들 dto
 * */
public record TodayLastLogs(
        TimeTable timeTable,
        LocalDateTime lastUpdated,
        RoomCode roomName,
        boolean isExist,
        boolean isCurrent
) {

    public static TodayLastLogs of(User user,RoomCode fixedRoom) {
        TimeTable timeTable= TimeTable.of();
        return new TodayLastLogs(
                timeTable,
                (user.getCurrentRoom() == NOTFOUND) ? null:user.getLastUpdated(),
                user.getCurrentRoom(),
                user.getCurrentRoom().equals(fixedRoom),
                true
        );
    }
}
