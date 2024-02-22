package com.beep.beep.domain.student.service;

import com.beep.beep.domain.beep.domain.Room;
import com.beep.beep.domain.beep.facade.BeepFacade;
import com.beep.beep.domain.student.domain.StudentId;
import com.beep.beep.domain.student.facade.StudentFacade;
import com.beep.beep.domain.student.presentation.dto.response.StudentInfoResponse;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.facade.UserFacade;
import com.beep.beep.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final UserFacade userFacade;
    private final StudentFacade studentFacade;
    private final BeepFacade beepFacade;
    private final JwtProvider jwtProvider;

    public StudentInfoResponse getStudentInfo(String token) {
        User user = userFacade.findUserById(jwtProvider.getTokenSubject(jwtProvider.parseToken(token)));
        Long userIdx = user.getIdx();

        StudentId studentId = studentFacade.findByUserIdx(userIdx);
        Room room = beepFacade.findByCode(beepFacade.findAttendanceByIdx(userIdx).getCode());

        return StudentInfoResponse.of(user,studentId,room);
    }
}
