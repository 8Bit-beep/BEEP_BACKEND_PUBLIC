package com.beep.beep.domain.student.usecase;

import com.beep.beep.domain.attendLog.domain.AttendLog;
import com.beep.beep.domain.attendLog.domain.enums.TimeTable;
import com.beep.beep.domain.attendLog.domain.repo.AttendLogJpaRepo;
import com.beep.beep.domain.attendLog.service.AttendLogService;
import com.beep.beep.domain.room.domain.Club;
import com.beep.beep.domain.student.mapper.StudentMapper;
import com.beep.beep.domain.student.presentation.dto.response.StudyResByFloor;
import com.beep.beep.domain.student.presentation.dto.response.TodayLastLogs;
import com.beep.beep.domain.user.domain.enums.RoomCode;
import com.beep.beep.domain.user.presentation.dto.UserVO;
import com.beep.beep.global.common.repository.UserSessionHolder;
import com.beep.beep.domain.student.exception.NotAllowedExitException;
import com.beep.beep.domain.student.presentation.dto.request.AttendReq;
import com.beep.beep.domain.student.presentation.dto.request.StudentSignUpReq;
import com.beep.beep.domain.student.presentation.dto.response.AttendListRes;
import com.beep.beep.domain.student.presentation.dto.response.AttendRes;
import com.beep.beep.domain.student.presentation.dto.response.MemberListRes;
import com.beep.beep.domain.student.presentation.dto.response.StudentCodeRes;
import com.beep.beep.domain.student.presentation.dto.response.StudentInfoRes;
import com.beep.beep.domain.student.presentation.dto.response.StudyRes;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.service.UserService;
import com.beep.beep.global.common.dto.response.Response;
import com.beep.beep.global.common.dto.response.ResponseData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

import static com.beep.beep.domain.user.domain.enums.RoomCode.NOTFOUND;

@Slf4j
@Component
@RequiredArgsConstructor
public class StudentUseCase {

    private final UserSessionHolder userSessionHolder;
    private final UserService userService;
    private final AttendLogService attendLogService;
    private final StudentMapper studentMapper;

    @Transactional
    public Response signUp(StudentSignUpReq req) {
        User user = userService.findByEmail(req.email());
        user.saveStudentInfo(req);
        userService.save(user);
        return Response.created("학생 기본정보 저장 성공");
    }

    public ResponseData<StudentInfoRes> studentInfo() {
        UserVO user = userSessionHolder.getUser();
        StudentInfoRes result = StudentInfoRes.of(user);
        return ResponseData.ok("학생 프로필 조회 성공",result);
    }

    public ResponseData<StudentCodeRes> studentCode() {
        UserVO user = userSessionHolder.getUser();
        StudentCodeRes result = StudentCodeRes.of(user);
        return ResponseData.ok("학생 실 코드 조회 완료",result);
    }

    @Transactional
    public ResponseData<AttendRes> attend(AttendReq req) {
        User user = userSessionHolder.getUser().toUserEntity();
        RoomCode currentRoomCode = user.getCurrentRoom();
        RoomCode roomCodeToUpdate = RoomCode.of(req.code());

        if (currentRoomCode.equals(NOTFOUND)) { // 입실 처리
            user.setCurrentRoom(roomCodeToUpdate);
        } else if (currentRoomCode.equals(roomCodeToUpdate)) { // 퇴실 처리
            user.setCurrentRoom(NOTFOUND);
        } else { // 퇴실 불가
            throw NotAllowedExitException.EXCEPTION;
        }

        User updatedUser = userService.updateCurrentRoom(user);
        AttendRes result = AttendRes.of(updatedUser.getCurrentRoom());

        return ResponseData.ok("출석 성공", result);
    }

    public ResponseData<List<AttendListRes>> attendList(String code) {
        RoomCode requestedRoom = RoomCode.of(code);
        List<AttendListRes> result = AttendListRes.of(userService.getRoomAttendList(requestedRoom));
        return ResponseData.ok("출석부 조회 성공",result);
    }

    public ResponseData<List<MemberListRes>> memberList(Integer grade,Integer cls) {
        List<MemberListRes> result = MemberListRes.of(userService.getClassMemberList(grade,cls));
        return ResponseData.ok("반 구성원 조회 성공",result);
    }

    public ResponseData<List<StudyRes>> studyList(Club club) {
        RoomCode requestedRoom = RoomCode.of(club.getCode()); // 요청된 club의 실 코드 구하기
        List<User> users = userService.getRoomStudyList(requestedRoom);
        List<StudyRes> result = studentMapper.of(requestedRoom,users);
        return ResponseData.ok("스터디 구성원 조회 성공",result);
    }

    public ResponseData<List<StudyResByFloor>> studyListByFloor(Integer floor) {
        List<RoomCode> roomsOnFloor = RoomCode.findByFloor(floor); // n층에 있는 모든 방을 조회
        List<StudyResByFloor> result = StudyResByFloor.of(userService.getStudyListByFloor(roomsOnFloor));
        return ResponseData.ok("층별 스터디 리스트 조회 성공",result);
    }
}
