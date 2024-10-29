package com.beep.beep.domain.student.usecase;

import com.beep.beep.domain.attendLog.service.AttendLogService;
import com.beep.beep.domain.room.domain.Club;
import com.beep.beep.domain.room.presentation.dto.RoomRes;
import com.beep.beep.domain.room.service.RoomService;
import com.beep.beep.domain.student.mapper.StudentMapper;
import com.beep.beep.domain.student.presentation.dto.response.GetStudentOrRoomRes;
import com.beep.beep.domain.student.presentation.dto.response.StudyResByFloor;
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

import java.util.List;

import static com.beep.beep.domain.user.domain.enums.RoomCode.NOTFOUND;

@Slf4j
@Component
@RequiredArgsConstructor
public class StudentUseCase {

    private final UserSessionHolder userSessionHolder;
    private final UserService userService;
    private final StudentMapper studentMapper;
    private final RoomService roomService;

//    @Transactional
//    public Response signUp(StudentSignUpReq req) {
//        User user = userService.findByEmail(req.email()); // 유저 찾기
//        user.saveStudentInfo(req); // entity 정보 수정
//        userService.save(user); // 저장
//        return Response.created("학생 기본정보 저장 성공");
//    }

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
        User user = userSessionHolder.getUser().toUserEntity(); // 유저 받기
        RoomCode currentRoomCode = user.getCurrentRoom(); // 현재 출석한 실
        RoomCode roomCodeToUpdate = RoomCode.of(req.code()); // 저장할 코드

        if (currentRoomCode.equals(NOTFOUND)) { // 입실 처리
            user.setCurrentRoom(roomCodeToUpdate);
        } else if (currentRoomCode.equals(roomCodeToUpdate)) { // 퇴실 처리
            user.setCurrentRoom(NOTFOUND);
        } else { // 퇴실 불가
            throw NotAllowedExitException.EXCEPTION;
        }

        User updatedUser = userService.updateCurrentRoom(user); // 실 업데이트
        AttendRes result = AttendRes.of(updatedUser.getCurrentRoom()); // 저장된 코드 반환

        return ResponseData.ok("출석 성공", result);
    }

    // 실별 조회
    public ResponseData<List<AttendListRes>> attendList(String code) {
        RoomCode requestedRoom = RoomCode.of(code);
        List<AttendListRes> result = AttendListRes.of(userService.getRoomAttendList(requestedRoom));
        return ResponseData.ok("출석부 조회 성공",result);
    }

    // 반별 조회
    public ResponseData<List<MemberListRes>> memberList(Integer grade,Integer cls) {
        List<MemberListRes> result = MemberListRes.of(userService.getClassMemberList(grade,cls));
        return ResponseData.ok("반 구성원 조회 성공",result);
    }

    // 스터디별 조회
    public ResponseData<List<StudyRes>> studyList(Club club) {
        RoomCode requestedRoom = RoomCode.of(club.getCode()); // 요청된 club의 실 코드 구하기
        List<User> users = userService.getRoomStudyList(requestedRoom);
        List<StudyRes> result = studentMapper.ofStudyRes(requestedRoom,users);
        return ResponseData.ok("스터디 구성원 조회 성공",result);
    }

    // 층별 조회
    public ResponseData<List<StudyResByFloor>> studyListByFloor(Integer floor) {
        List<RoomCode> roomsOnFloor = RoomCode.findByFloor(floor); // n층에 있는 모든 방을 조회
        List<User> users = userService.getStudyListByFloor(roomsOnFloor);
        List<StudyResByFloor> result = studentMapper.ofStudyResByFloor(users);
        return ResponseData.ok("층별 스터디 리스트 조회 성공",result);
    }

    // 학생이름/실이름 조회
    public ResponseData<GetStudentOrRoomRes> getStudentOrRoom(String keyword) {
        List<MemberListRes> students = MemberListRes.of(userService.getStudentByName(keyword));
        List<RoomRes> rooms = RoomRes.of(roomService.getRoomsByName(keyword));
        GetStudentOrRoomRes result = new GetStudentOrRoomRes(students,rooms);
        return ResponseData.ok("검색어로 학생/실 조회 성공",result);
    }
}
