package com.beep.beep.domain.student.presentation.dto.request;

import com.beep.beep.domain.room.domain.Room;
import com.beep.beep.domain.student.domain.Student;
import com.beep.beep.domain.user.domain.User;

public record StudentSignUpReq(Integer grade, Integer cls, Integer num, String email) {
    public Student toStudentEntity(User user){
        return Student.builder()
                .grade(this.grade)
                .cls(this.cls)
                .num(this.num)
                .room(Room.builder()
                        .code("0")
                        .name("미출석")
                        .floor(0).build())
                .studyRoom(null)
                .user(user).build();
    }
}
