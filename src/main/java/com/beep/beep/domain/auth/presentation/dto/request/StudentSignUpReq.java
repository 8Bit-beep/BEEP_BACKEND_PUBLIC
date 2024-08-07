package com.beep.beep.domain.auth.presentation.dto.request;

import com.beep.beep.domain.student.domain.Student;

public record StudentSignUpReq(String email, String password, String name, Integer grade, Integer cls, Integer num) {
    public Student toStudentEntity(String encodedPassword) {
        return Student.builder()
                .email(this.email)
                .password(encodedPassword)
                .name(this.name)
                .grade(this.grade)
                .cls(this.cls)
                .num(this.num).build();
    }
}
