package com.beep.beep.domain.student.presentation.dto.request;

import com.beep.beep.domain.student.domain.Student;

public record StudentSignUpReq(Integer grade, Integer cls, Integer num, String email) {
    public Student toStudentEntity(){
        return Student.builder()
                .grade(this.grade)
                .cls(this.cls)
                .num(this.num)
                .code("")
                .studyCode("")
                .username(this.email).build();
    }
}
