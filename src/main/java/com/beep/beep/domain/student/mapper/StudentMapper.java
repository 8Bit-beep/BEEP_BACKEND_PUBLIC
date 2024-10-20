package com.beep.beep.domain.student.mapper;

import com.beep.beep.domain.attendLog.domain.enums.TimeTable;
import com.beep.beep.domain.attendLog.service.AttendLogService;
import com.beep.beep.domain.student.presentation.dto.response.StudyRes;
import com.beep.beep.domain.student.presentation.dto.response.TodayLastLogs;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.enums.RoomCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentMapper {

    private final AttendLogService attendLogService;

    public List<StudyRes> of(RoomCode requestedRoom,List<User> users) {
        return users.parallelStream()
                .map(user -> {
                    List<TodayLastLogs> todayLastLogs = attendLogService.getTodayLog(user);
                    return StudyRes.of(requestedRoom,todayLastLogs,user);
                })
                .toList();
    }

}
