package com.beep.beep.domain.teacher.service;

import com.beep.beep.domain.teacher.presentation.dto.response.AdminTeacherResponse;
import com.beep.beep.domain.beep.facade.BeepFacade;
import com.beep.beep.domain.student.facade.StudentFacade;
import com.beep.beep.domain.teacher.domain.Job;
import com.beep.beep.domain.teacher.domain.facade.TeacherFacade;
import com.beep.beep.domain.teacher.presentation.dto.response.TeacherInfoResponse;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.facade.UserFacade;
import com.beep.beep.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final JwtProvider jwtProvider;
    private final UserFacade userFacade;
    private final TeacherFacade teacherFacade;

    public List<AdminTeacherResponse> teacherList() {
        List<User> teacherList = userFacade.findAllTeachers();

        return teacherList.stream()
                .map(teacher ->
                        AdminTeacherResponse.of(teacher,teacherFacade.findByUserIdx(teacher.getIdx())))
                .toList();
    }

    public TeacherInfoResponse getTeacherInfo(String token){
        User user = userFacade.findUserByEmail(jwtProvider.getTokenSubject(jwtProvider.parseToken(token)));
        Job job = teacherFacade.findByUserIdx(user.getIdx());

        return TeacherInfoResponse.of(user,job);
    }


}
