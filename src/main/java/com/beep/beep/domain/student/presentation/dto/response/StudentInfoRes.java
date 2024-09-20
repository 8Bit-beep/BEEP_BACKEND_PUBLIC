package com.beep.beep.domain.student.presentation.dto.response;

import com.beep.beep.domain.student.domain.Student;
import com.beep.beep.domain.user.domain.User;
import lombok.Builder;

@Builder
public record StudentInfoRes(String email,String name,Integer grade,Integer cls,Integer num) {
    public static StudentInfoRes of(Student student) {
        return StudentInfoRes.builder()
                .email(student.getUser().getEmail())
                .name(student.getUser().getName())
                .grade(student.getGrade())
                .cls(student.getCls())
                .num(student.getNum()).build();
    }
}
