package com.beep.beep.domain.student.presentation.dto.request;

import com.beep.beep.domain.student.domain.Student;
import com.beep.beep.domain.user.domain.User;

public record StudentSignUpReq(Integer grade, Integer cls, Integer num, String email) {
    public Student toStudentEntity(User user){
        return Student.builder()
                .grade(this.grade)
                .cls(this.cls)
                .num(this.num)
                .room(null)
                .studyRoom(null)
                .user(user).build();
    }
}
