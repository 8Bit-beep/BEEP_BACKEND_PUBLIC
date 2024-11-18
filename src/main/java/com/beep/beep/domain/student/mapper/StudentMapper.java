package com.beep.beep.domain.student.mapper;

import com.beep.beep.domain.attendLog.domain.enums.TimeTable;
import com.beep.beep.domain.attendLog.service.AttendLogService;
import com.beep.beep.domain.room.domain.Club;
import com.beep.beep.domain.schedule.presentation.dto.response.ScheduleRes;
import com.beep.beep.domain.schedule.service.ScheduleService;
import com.beep.beep.domain.student.presentation.dto.response.StudyRes;
import com.beep.beep.domain.student.presentation.dto.response.StudyResByFloor;
import com.beep.beep.domain.student.presentation.dto.response.SummarizeStudiesRes;
import com.beep.beep.domain.student.presentation.dto.response.TodayLastLogs;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.enums.RoomCode;
import com.beep.beep.domain.user.domain.repo.UserJpaRepo;
import com.beep.beep.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.beep.beep.domain.user.domain.enums.RoomCode.NOTFOUND;

@Component
@RequiredArgsConstructor
public class StudentMapper {

    private final AttendLogService attendLogService;
    private final ScheduleService scheduleService;
    private final UserService userService;

    // record로 저거 구현할 생각에 아찔해진 본인은 일부 mapper 를 도입하게됨.

    /**
     * @parameter List<User> entity 리스트를 받아서
     * @return List<StudyResByFloor> 응답 dto로
     * */
    public List<StudyResByFloor> ofStudyResByFloor(List<User> users) {
        return users.parallelStream()
                .map(user -> {
                    List<TodayLastLogs> todayLastLogs = attendLogService.getTodayLog(user); // 오늘 저장된 user의 출석로그 조회
                    ScheduleRes schedule = null;
                    if(user.getCurrentRoom() == NOTFOUND) {
                        schedule = scheduleService.getCurrentSchedule(user, TimeTable.of());
                    }
                    StudyResByFloor res = StudyResByFloor.of(todayLastLogs,user,schedule);  // res로 변환
                    return res;
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
                    ScheduleRes schedule = null;
                    if(user.getCurrentRoom() == NOTFOUND) {
                        schedule = scheduleService.getCurrentSchedule(user, TimeTable.of());
                    }
                    StudyResByFloor res = StudyResByFloor.of(todayLastLogs,user,schedule);  // res로 변환
                    return StudyRes.of(requestedRoom,todayLastLogs,user,schedule); // res로 변환
                })
                .toList();
    }

    public List<SummarizeStudiesRes> ofSummarizeStudiesRes(List<Club> clubs) {
        return clubs.parallelStream()
                .map(club -> {
                    RoomCode room = RoomCode.of(club.getCode());
                    Integer attendedStudent = userService.countCurrentRoom(room);
                    Integer totalStudent = userService.countFixedRoom(room);
                    SummarizeStudiesRes res = new SummarizeStudiesRes(club,attendedStudent,totalStudent);
                    return res;
                })
                .toList();
    }
}
