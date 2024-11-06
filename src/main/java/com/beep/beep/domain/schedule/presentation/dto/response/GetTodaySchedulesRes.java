package com.beep.beep.domain.schedule.presentation.dto.response;

import com.beep.beep.domain.attendLog.domain.enums.TimeTable;
import com.beep.beep.domain.schedule.domain.Schedule;
import com.beep.beep.domain.student.presentation.dto.response.MemberListRes;
import com.beep.beep.domain.user.domain.User;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

public record GetTodaySchedulesRes(
        Long id,
        String cause,
        String room,
        TimeTable timeTable,
        DayOfWeek dayOfWeek,
        String name,
        Integer grade,
        Integer cls,
        Integer num
) {
    public static List<GetTodaySchedulesRes> of(List<Schedule> schedules) {
        return schedules.parallelStream()
                .map(GetTodaySchedulesRes::of)
                .toList();
    }

    public static GetTodaySchedulesRes of(Schedule schedule) {
        User user = schedule.getUser();
//        System.out.println(user.getName());

        return new GetTodaySchedulesRes(
                schedule.getId(),
                schedule.getCause(),
                schedule.getRoom(),
                schedule.getTimeTable(),
                schedule.getDayOfWeek(),
                user.getName(),
                user.getGrade(),
                user.getCls(),
                user.getNum()
        );
    }

}
