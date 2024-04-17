package com.beep.beep.domain.student.presentation.dto.response;


import com.beep.beep.domain.beep.domain.Room;
import com.beep.beep.domain.student.domain.StudentId;
import com.beep.beep.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder @AllArgsConstructor
public class SearchStudentResponse {

    private String name;

    private int grade;
    private int cls;
    private int num;

    private String room;

    public static SearchStudentResponse of(User user,StudentId studentId, Room room) {
        return SearchStudentResponse.builder()
                .name(user.getName())
                .grade(studentId.getGrade())
                .cls(studentId.getCls())
                .num(studentId.getNum())
                .room(room.getName()).build();
    }

}
