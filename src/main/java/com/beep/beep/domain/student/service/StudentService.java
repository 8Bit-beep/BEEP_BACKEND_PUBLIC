package com.beep.beep.domain.student.service;

import com.beep.beep.domain.beep.domain.Attendance;
import com.beep.beep.domain.beep.domain.Room;
import com.beep.beep.domain.beep.exception.NonExitException;
import com.beep.beep.domain.beep.exception.NotCurrentRoomException;
import com.beep.beep.domain.beep.facade.BeepFacade;
import com.beep.beep.domain.student.presentation.dto.request.EnterRoomRequest;
import com.beep.beep.domain.student.presentation.dto.request.ExitRoomRequest;
import com.beep.beep.domain.student.domain.StudentId;
import com.beep.beep.domain.student.facade.StudentFacade;
import com.beep.beep.domain.student.presentation.dto.response.StudentInfoResponse;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.facade.UserFacade;
import com.beep.beep.global.security.jwt.JwtProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final UserFacade userFacade;
    private final StudentFacade studentFacade;
    private final BeepFacade beepFacade;
    private final JwtProvider jwtProvider;

    public StudentInfoResponse getStudentInfo(String token) {
        User user = userFacade.findUserByEmail(jwtProvider.getTokenSubject(jwtProvider.parseToken(token)));
        System.out.println("유저 찾음.");
        Long userIdx = user.getIdx();

        StudentId studentId = studentFacade.findByUserIdx(userIdx);
        Room room = beepFacade.findByCode(beepFacade.findAttendanceByIdx(userIdx).getCode());

        return StudentInfoResponse.of(user,studentId,room);
    }

    @Transactional
    public void enter(String token, EnterRoomRequest request){
        String code = request.getCode();
        beepFacade.existsRoomByCode(code);

        User user = userFacade.findUserByEmail(jwtProvider.getTokenSubject(jwtProvider.parseToken(token)));

        Attendance attendance = beepFacade.findAttendanceByIdx(user.getIdx());
        if (!Objects.equals(attendance.getCode(), "404"))
            throw NonExitException.EXCEPTION;

        attendance.updateAttendance(code);
    }

    @Transactional
    public void exit(String token, ExitRoomRequest request){
        String code = request.getCode();
        beepFacade.existsRoomByCode(code);

        User user = userFacade.findUserByEmail(jwtProvider.getTokenSubject(jwtProvider.parseToken(token)));

        Attendance attendance = beepFacade.findAttendanceByIdx(user.getIdx());
        if (!Objects.equals(code, attendance.getCode()))
            throw NotCurrentRoomException.EXCEPTION;

        attendance.updateAttendance("404");
    }

}
