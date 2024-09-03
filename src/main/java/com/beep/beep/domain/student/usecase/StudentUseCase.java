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
import com.beep.beep.global.common.repository.UserSessionHolder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentUseCase {

    private final UserSessionHolder userSessionHolder;
    private final UserJpaRepo userJpaRepo;
    private final StudentService studentService;
    private final RoomService roomService;

    public void signUp(StudentSignUpReq req) {
        if(!userJpaRepo.existsById(req.email()))
            throw UserNotFoundException.EXCEPTION;

        studentService.save(req.toStudentEntity());
    }

    public StudentInfoRes studentInfo() {
        User user = userJpaRepo.findById(userSessionHolder.getUser().email())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        Student student = studentService.findByEmail(user.getEmail());
        return StudentInfoRes.of(user,student);
    }

    public StudentCodeRes studentCode() {
        return StudentCodeRes.of(studentService.findByEmail(userSessionHolder.getUser().email()).getCode());
    }

    @Transactional
    public AttendRes attend(AttendReq req) {
        Student student = studentService.findByEmail(userSessionHolder.getUser().email());
        String code = student.getCode();

        roomService.existsByCode(req.code()); // 이 코드는 존재하는가?

        if(code.isEmpty()){ // 빈문자열 -> 입실처리
            student.updateCode(req.code());
            return AttendRes.of(req.code());
        } else if(code.equals(req.code())){ // 빈문자열 아님, 저장된 코드 = 요청한 코드 -> 퇴실처리
            student.updateCode("");
            return AttendRes.of("");
        } else { // 빈문자열 아님, 저장된 코드 != 요청한 코드 -> 퇴실 불가 처리
            throw NotAllowedExitException.EXCEPTION;
        }
    }

    public List<AttendListRes> attendList(String code) {
        roomService.existsByCode(code);
        return studentService.attendList(code);
    }

    public List<MemberListRes> memberList(Integer grade,Integer cls) {
        return studentService.memberList(grade,cls);
    }

}
