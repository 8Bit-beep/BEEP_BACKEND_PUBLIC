package com.beep.beep.domain.teacher.presentation.dto.response;

import com.beep.beep.domain.teacher.domain.Teacher;
import lombok.Builder;

@Builder
public record TeacherInfoRes(String email, String name, String department, String job) {
    public static TeacherInfoRes of(Teacher teacher) {
        return TeacherInfoRes.builder()
                .email(teacher.getEmail())
                .name(teacher.getName())
                .department(teacher.getDepartment())
                .job(teacher.getJob()).build();
    }
}
