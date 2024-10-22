package com.beep.beep.domain.student.presentation.dto.response;

import com.beep.beep.domain.attendLog.domain.enums.TimeTable;
import com.beep.beep.domain.room.domain.Club;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.enums.RoomCode;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public record StudyResByFloor(
        String name,
        Integer grade,
        Integer cls,
        Integer num,
        List<TodayLastLogs> todayLastLogs,
        RoomCode currentRoom,
        LocalDateTime lastUpdated,
        TimeTable currentPeriod,
        RoomCode fixedRoom,
        boolean isExist,
        Club club) {

    public static StudyResByFloor of(List<TodayLastLogs> todayLastLogs,User user) {
        return new StudyResByFloor(
                user.getName(),
                user.getGrade(),
                user.getCls(),
                user.getNum(),
                todayLastLogs,
                user.getCurrentRoom(),
                user.getLastUpdated(),
                TimeTable.of(),
                user.getFixedRoom(),
                user.getFixedRoom().equals(user.getCurrentRoom()),
                Club.of(user.getFixedRoom().getCode())
        );
    }
}
