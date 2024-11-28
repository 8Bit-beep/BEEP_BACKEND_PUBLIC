package com.beep.beep.domain.student.presentation.dto.response;

import com.beep.beep.domain.attendLog.domain.enums.TimeTable;
import com.beep.beep.domain.schedule.presentation.dto.response.ScheduleRes;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.enums.RoomCode;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;


/**
 * study로 출석조회 응답 dto
 * */
@Builder
public record StudyRes(
        RoomCode roomName,
        List<TodayLastLogs> todayLastLogs,
        String name,
        Integer grade,
        Integer cls,
        Integer num,
        ScheduleRes schedule) {
    public static StudyRes of(RoomCode requestedRoom, List<TodayLastLogs> todayLastLogs, User user, ScheduleRes schedule) {
        return StudyRes.builder()
                .roomName(requestedRoom)
                .todayLastLogs(todayLastLogs)
                .name(user.getName())
                .grade(user.getGrade())
                .cls(user.getCls())
                .num(user.getNum())
                .schedule(schedule).build();
    }
}
