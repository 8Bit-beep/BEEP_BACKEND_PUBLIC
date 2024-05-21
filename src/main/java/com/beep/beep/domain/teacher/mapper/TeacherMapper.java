package com.beep.beep.domain.teacher.mapper;

import com.beep.beep.domain.teacher.domain.JobEntity;
import com.beep.beep.domain.teacher.presentation.dto.request.SaveJobRequest;
import com.beep.beep.domain.teacher.presentation.dto.response.AdminTeacherResponse;
import com.beep.beep.domain.teacher.presentation.dto.response.TeacherInfoResponse;
import com.beep.beep.domain.user.domain.UserEntity;
import com.beep.beep.domain.user.presentation.dto.User;
import org.springframework.stereotype.Component;


@Component
public class TeacherMapper {

    public JobEntity toJob(User user, SaveJobRequest request) {
        return JobEntity.builder()
                .job(request.getJob())
                .department(request.getDepartment())
                .userIdx(user.getIdx()).build();
    }

    public static AdminTeacherResponse toAdminTeacherDto(UserEntity user, JobEntity job) {
        return AdminTeacherResponse.builder()
                .idx(user.getIdx())
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .department(job.getDepartment())
                .job(job.getJob()).build();
    }

    public static TeacherInfoResponse toTeacherInfoDto(User user, JobEntity job) {
        return TeacherInfoResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .department(job.getDepartment())
                .job(job.getJob()).build();
    }
}
