package com.beep.beep.domain.teacher.service;

import com.beep.beep.domain.teacher.domain.JobEntity;
import com.beep.beep.domain.teacher.domain.repository.JobRepository;
import com.beep.beep.domain.teacher.mapper.TeacherMapper;
import com.beep.beep.domain.teacher.presentation.dto.request.SaveJobRequest;
import com.beep.beep.domain.teacher.presentation.dto.response.AdminTeacherResponse;
import com.beep.beep.domain.teacher.presentation.dto.response.TeacherInfoResponse;
import com.beep.beep.domain.user.domain.UserEntity;
import com.beep.beep.domain.user.domain.enums.UserType;
import com.beep.beep.domain.user.domain.repository.UserRepository;
import com.beep.beep.domain.user.facade.UserFacade;
import com.beep.beep.domain.user.presentation.dto.User;
import com.beep.beep.global.common.repository.UserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final UserFacade userFacade;
    private final TeacherMapper teacherMapper;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;
    private final UserSecurity userSecurity;

    public void saveJob(SaveJobRequest request){
        User user = userFacade.findUserByEmail(userSecurity.getUser().getEmail());

        jobRepository.save(teacherMapper.toJob(user,request));
    }

    public List<AdminTeacherResponse> teacherList() {
        List<UserEntity> teacherList = userRepository.findAllByAuthority(UserType.TEACHER);

        return teacherList.stream()
                .map(teacher ->
                        TeacherMapper.toAdminTeacherDto(teacher,jobRepository.findByUserIdx(teacher.getIdx())))
                .toList();
    }

    public TeacherInfoResponse getTeacherInfo(){
        User user = userFacade.findUserByEmail(userSecurity.getUser().getEmail());
        JobEntity job = jobRepository.findByUserIdx(user.getIdx());

        return TeacherMapper.toTeacherInfoDto(user, job);
    }


}
