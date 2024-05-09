package com.beep.beep.domain.teacher.mapper;

import com.beep.beep.domain.teacher.presentation.dto.Job;
import com.beep.beep.domain.teacher.presentation.dto.response.AdminTeacherResponse;
import com.beep.beep.domain.teacher.presentation.dto.response.TeacherInfoResponse;
import com.beep.beep.domain.user.presentation.dto.User;
import org.springframework.stereotype.Component;


@Component
public class TeacherMapper {

    public static AdminTeacherResponse toAdminTeacherDto(User user, Job job) {
        return AdminTeacherResponse.builder()
                .idx(user.getIdx())
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .department(job.getDepartment())
                .job(job.getJob()).build();
    }

    public static TeacherInfoResponse toTeacherInfoDto(User user, Job job) {
        return TeacherInfoResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .department(job.getDepartment())
                .job(job.getJob()).build();
    }
}
