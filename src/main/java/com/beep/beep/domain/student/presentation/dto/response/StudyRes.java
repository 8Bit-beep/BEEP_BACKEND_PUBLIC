package com.beep.beep.domain.student.presentation.dto.response;

import com.beep.beep.domain.attendLog.domain.enums.TimeTable;
import com.beep.beep.domain.attendLog.domain.repo.AttendLogJpaRepo;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.enums.RoomCode;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


/**
 * study로 출석조회 응답 dto
 * */
@Builder
public record StudyRes(
        RoomCode roomName,
        List<TodayLastLogs> todayLastLogs,
        RoomCode currentRoom,
        TimeTable currentPeriod,
        String name,
        Integer grade,
        Integer cls,
        Integer num,
        boolean isExist,
        LocalDateTime modifiedDate) {
    public static StudyRes of(RoomCode requestedRoom, List<TodayLastLogs> todayLastLogs, User user) {
        return StudyRes.builder()
                .roomName(requestedRoom)
                .todayLastLogs(todayLastLogs)
                .currentRoom(user.getCurrentRoom())
                .currentPeriod(TimeTable.of())
                .name(user.getName())
                .grade(user.getGrade())
                .cls(user.getCls())
                .num(user.getNum())
                .isExist(user.getFixedRoom().equals(user.getCurrentRoom()))
                .modifiedDate(user.getLastUpdated()).build();
    }
}
