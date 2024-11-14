package com.beep.beep.domain.student.presentation.dto.response;

import com.beep.beep.domain.attendLog.domain.enums.TimeTable;
import com.beep.beep.domain.room.domain.Club;
import com.beep.beep.domain.schedule.domain.Schedule;
import com.beep.beep.domain.schedule.presentation.dto.response.ScheduleRes;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.enums.RoomCode;
import lombok.Builder;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 층별 모든 출석리스트 응답 dto
 * */
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
        Club club,
        ScheduleRes schedule) {

    public static StudyResByFloor of(List<TodayLastLogs> todayLastLogs,User user,ScheduleRes schedule) {
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
                Club.of(user.getFixedRoom().getCode()),
                schedule
        );
    }

}
