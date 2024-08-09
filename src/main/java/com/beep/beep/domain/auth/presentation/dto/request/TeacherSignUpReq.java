package com.beep.beep.domain.auth.presentation.dto.request;

import com.beep.beep.domain.teacher.domain.Teacher;

public record TeacherSignUpReq(String email, String password, String name, String department) {
    public Teacher toTeacherEntity(String encodedPassword) {
        return Teacher.builder()
                .email(this.email)
                .password(encodedPassword)
                .name(this.name)
                .department(this.department).build();
    }
}
