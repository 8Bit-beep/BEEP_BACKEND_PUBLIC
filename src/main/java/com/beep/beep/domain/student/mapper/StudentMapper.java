package com.beep.beep.domain.student.mapper;

import com.beep.beep.domain.attendLog.service.AttendLogService;
import com.beep.beep.domain.student.presentation.dto.response.StudyRes;
import com.beep.beep.domain.student.presentation.dto.response.StudyResByFloor;
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

    // record로 저거 구현할 생각에 아찔해진 본인은 일부 mapper 를 도입하게됨.

    /**
     * @parameter List<User> entity 리스트를 받아서
     * @return List<StudyResByFloor> 응답 dto로
     * */
    public List<StudyResByFloor> ofStudyResByFloor(List<User> users) {
        return users.parallelStream()
                .map(user -> {
                    List<TodayLastLogs> todayLastLogs = attendLogService.getTodayLog(user); // 오늘 저장된 user의 출석로그 조회
                    return StudyResByFloor.of(todayLastLogs,user);  // res로 변환
                })
                .toList();
    }

    /**
     * @parameter List<User> entity 리스트를 받아서
     * @return List<StudyRes> 응답 dto로
     * */
    public List<StudyRes> ofStudyRes(RoomCode requestedRoom, List<User> users) {
        return users.parallelStream()
                .map(user -> {
                    List<TodayLastLogs> todayLastLogs = attendLogService.getTodayLog(user); // 오늘 저장된 user의 출석로그 조회
                    return StudyRes.of(requestedRoom,todayLastLogs,user); // res로 변환
                })
                .toList();
    }

}
