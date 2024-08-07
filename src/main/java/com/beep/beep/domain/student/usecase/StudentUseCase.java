package com.beep.beep.domain.student.usecase;

import com.beep.beep.domain.student.domain.Student;
import com.beep.beep.domain.student.domain.enums.RoomCode;
import com.beep.beep.domain.student.exception.NotAllowedAttendException;
import com.beep.beep.domain.student.presentation.dto.request.AttendReq;
import com.beep.beep.domain.student.presentation.dto.request.MemberListReq;
import com.beep.beep.domain.student.presentation.dto.response.AttendListRes;
import com.beep.beep.domain.student.presentation.dto.response.AttendRes;
import com.beep.beep.domain.student.presentation.dto.response.MemberListRes;
import com.beep.beep.domain.student.presentation.dto.response.StudentInfoRes;
import com.beep.beep.domain.student.service.StudentService;
import com.beep.beep.global.common.repository.UserSessionHolder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentUseCase {

    private final UserSessionHolder userSessionHolder;
    private final StudentService studentService;

    public StudentInfoRes studentInfo() {
        return StudentInfoRes.of(studentService.findByEmail(userSessionHolder.getUser().email()));
    }

    @Transactional
    public AttendRes attend(AttendReq req) {
        Student student = studentService.findByEmail(userSessionHolder.getUser().email());
        RoomCode code = student.getCode();

        if(code == null && req.code() != null){ // 입실해야하면?
            student.updateCode(req.code());
            return AttendRes.of(req.code());
        } else if(code != null && req.code() == null){ // 퇴실해야하면?
            student.updateCode(null);
            return AttendRes.of(null);
        } else {
            throw NotAllowedAttendException.EXCEPTION;
        }
    }

    public List<AttendListRes> attendList(RoomCode code) {
        return studentService.attendList(code);
    }

    public List<MemberListRes> memberList(MemberListReq req) {
        return studentService.memberList(req);
    }


}
