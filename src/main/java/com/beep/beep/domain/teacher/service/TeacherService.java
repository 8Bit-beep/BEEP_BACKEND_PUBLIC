package com.beep.beep.domain.teacher.service;

import com.beep.beep.domain.teacher.domain.Job;
import com.beep.beep.domain.teacher.domain.facade.TeacherFacade;
import com.beep.beep.domain.teacher.presentation.dto.response.TeacherInfoResponse;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.facade.UserFacade;
import com.beep.beep.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final JwtProvider jwtProvider;
    private final UserFacade userFacade;
    private final TeacherFacade teacherFacade;

    public TeacherInfoResponse getTeacherInfo(String token){
        User user = userFacade.findUserById(jwtProvider.getTokenSubject(token));
        Job job = teacherFacade.findByUserIdx(user.getIdx());

        return TeacherInfoResponse.of(user,job);
    }
}
