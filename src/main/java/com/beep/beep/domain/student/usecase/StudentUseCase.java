package com.beep.beep.domain.student.usecase;

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
import com.beep.beep.domain.student.service.StudentService;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.repo.UserJpaRepo;
import com.beep.beep.domain.user.exception.UserNotFoundException;
import com.beep.beep.domain.user.service.UserService;
import com.beep.beep.global.common.dto.response.Response;
import com.beep.beep.global.common.dto.response.ResponseData;
import com.beep.beep.global.common.repository.UserSessionHolder;
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
        userService.notFoundEmail(req.email());

        studentService.save(req.toStudentEntity());
        return Response.created("학생 기본정보 저장 성공");
    }

    public ResponseData<StudentInfoRes> studentInfo() {
        User user = userService.findByEmail(userService.getCurrentEmail());

        Student student = studentService.findByEmail(user.getEmail());
        return ResponseData.ok("학생 프로필 조회 성공",StudentInfoRes.of(user,student));
    }

    public ResponseData<StudentCodeRes> studentCode() {
        return ResponseData.ok("학생 실 코드 조회 완료",StudentCodeRes.of(studentService.findByEmail(userService.getCurrentEmail()).getCode()));
    }

    @Transactional
    public ResponseData<AttendRes> attend(AttendReq req) {
        Student student = studentService.findByEmail(userService.getCurrentEmail());
        String code = student.getCode();
        AttendRes res;

        roomService.existsByCode(req.code()); // 이 코드는 존재하는가?

        if(code.isEmpty()){ // 빈문자열 -> 입실처리
            student.updateCode(req.code());
            student.updateDate();
            res = AttendRes.of(req.code());
        } else if(code.equals(req.code())){ // 빈문자열 아님, 저장된 코드 = 요청한 코드 -> 퇴실처리
            student.updateCode("");
            student.updateDate();
            res = AttendRes.of("");
        } else { // 빈문자열 아님, 저장된 코드 != 요청한 코드 -> 퇴실 불가 처리
            throw NotAllowedExitException.EXCEPTION;
        }

        return ResponseData.ok("출석 성공", res);
    }

    public ResponseData<List<AttendListRes>> attendList(String code) {
        roomService.existsByCode(code);
        return ResponseData.ok("출석부 조회 성공",studentService.attendList(code));
    }

    public ResponseData<List<MemberListRes>> memberList(Integer grade,Integer cls) {
        return ResponseData.ok("반 구성원 조회 성공",studentService.memberList(grade,cls));
    }

}
