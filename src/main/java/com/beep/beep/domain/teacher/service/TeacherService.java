package com.beep.beep.domain.teacher.service;

import com.beep.beep.domain.teacher.domain.repository.JobRepository;
import com.beep.beep.domain.teacher.mapper.TeacherMapper;
import com.beep.beep.domain.teacher.presentation.dto.Job;
import com.beep.beep.domain.teacher.presentation.dto.response.AdminTeacherResponse;
import com.beep.beep.domain.teacher.presentation.dto.response.TeacherInfoResponse;
import com.beep.beep.domain.user.domain.enums.UserType;
import com.beep.beep.domain.user.domain.repository.UserRepository;
import com.beep.beep.domain.user.facade.UserFacade;
import com.beep.beep.domain.user.presentation.dto.User;
import com.beep.beep.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final JwtProvider jwtProvider;
    private final UserFacade userFacade;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public List<AdminTeacherResponse> teacherList() {
        List<User> teacherList = userRepository.findAllByAuthority(UserType.TEACHER);

        return teacherList.stream()
                .map(teacher ->
                        TeacherMapper.toAdminTeacherDto(teacher,jobRepository.findByUserIdx(teacher.getIdx())))
                .toList();
    }

    public TeacherInfoResponse getTeacherInfo(String token){
        User user = userFacade.findUserByEmail(jwtProvider.getTokenSubject(jwtProvider.parseToken(token)));
        Job job = jobRepository.findByUserIdx(user.getIdx());

        return TeacherMapper.toTeacherInfoDto(user, job);
    }


}
