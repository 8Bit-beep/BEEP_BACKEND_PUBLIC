package com.beep.beep.domain.student.presentation.dto.request;

import com.beep.beep.domain.room.domain.Room;
import com.beep.beep.domain.student.domain.Student;
import com.beep.beep.domain.user.domain.User;

public record StudentSignUpByExcel(String email,Integer grade, Integer cls, Integer num,String studyCode) {
    public Student toStudentEntity(User user, Room room){
        return Student.builder()
                .grade(this.grade)
                .cls(this.cls)
                .num(this.num)
                .room(null)
                .studyRoom(room)
                .user(user).build();
    }
}
