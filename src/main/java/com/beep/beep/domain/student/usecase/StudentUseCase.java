package com.beep.beep.domain.student.usecase;

import com.beep.beep.domain.room.domain.Club;
import com.beep.beep.domain.room.domain.Room;
import com.beep.beep.domain.room.service.RoomService;
import com.beep.beep.domain.student.domain.Student;
import com.beep.beep.domain.student.exception.NotAllowedExitException;
import com.beep.beep.domain.student.presentation.dto.request.AttendReq;
import com.beep.beep.domain.student.presentation.dto.request.StudentSignUpReq;
import com.beep.beep.domain.student.presentation.dto.response.AttendListRes;
import com.beep.beep.domain.student.presentation.dto.response.AttendRes;
import com.beep.beep.domain.student.presentation.dto.response.MemberListRes;
import com.beep.beep.domain.student.presentation.dto.response.StudentCodeRes;
import com.beep.beep.domain.student.presentation.dto.response.StudentInfoRes;
import com.beep.beep.domain.student.presentation.dto.response.StudyRes;
import com.beep.beep.domain.student.service.StudentService;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.service.UserService;
import com.beep.beep.global.common.dto.response.Response;
import com.beep.beep.global.common.dto.response.ResponseData;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentUseCase {

    private final StudentService studentService;
    private final RoomService roomService;
    private final UserService userService;

    public Response signUp(StudentSignUpReq req) {
        User user = userService.findByEmail(req.email());
        studentService.isExists(user);

        studentService.save(req.toStudentEntity(user));
        return Response.created("학생 기본정보 저장 성공");
    }

    public ResponseData<StudentInfoRes> studentInfo() {
        User user = userService.getUser();
        StudentInfoRes result = StudentInfoRes.of(studentService.getStudent(user));
        return ResponseData.ok("학생 프로필 조회 성공",result);
    }

    public ResponseData<StudentCodeRes> studentCode() {
        User user = userService.getUser();
        StudentCodeRes result = StudentCodeRes.of(studentService.getStudent(user));
        return ResponseData.ok("학생 실 코드 조회 완료",result);
    }

    @Transactional
    public ResponseData<AttendRes> attend(AttendReq req) {
        User user = userService.getUser();
        Student student = studentService.getStudent(user);
        Room lastRoom = student.getRoom();
        AttendRes res;

        Room room = roomService.findByCode(req.code()); // 이 코드는 존재하는가?

        if(lastRoom == null){ // 빈문자열 -> 입실처리
            student.updateRoom(room);
            student.updateDate();
            res = AttendRes.of(req.code());
        } else if(lastRoom.getCode().equals(req.code())){ // 빈문자열 아님, 저장된 코드 = 요청한 코드 -> 퇴실처리
            student.updateRoom(null);
            student.updateDate();
            res = AttendRes.of("");
        } else { // 빈문자열 아님, 저장된 코드 != 요청한 코드 -> 퇴실 불가 처리
            throw NotAllowedExitException.EXCEPTION;
        }

        return ResponseData.ok("출석 성공", res);
    }

    public ResponseData<List<AttendListRes>> attendList(String code) {
        Room room = roomService.findByCode(code);
        List<AttendListRes> result = AttendListRes.of(studentService.getRoomAttendList(room));
        return ResponseData.ok("출석부 조회 성공",result);
    }

    public ResponseData<List<MemberListRes>> memberList(Integer grade,Integer cls) {
        List<MemberListRes> result = MemberListRes.of(studentService.getClassMemberList(grade,cls));
        return ResponseData.ok("반 구성원 조회 성공",result);
    }

    public ResponseData<List<StudyRes>> studyList(Club club) {
        Room room = roomService.findByClub(club);
        List<StudyRes> result = StudyRes.of(studentService.getStudyMemberList(room));
        return ResponseData.ok("스터디 구성원 조회 성공",result);
    }
}
