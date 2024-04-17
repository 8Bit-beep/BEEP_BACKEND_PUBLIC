package com.beep.beep.domain.student.presentation.dto.response;


import com.beep.beep.domain.student.domain.StudentId;
import com.beep.beep.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder @AllArgsConstructor
public class GetAttendanceResponse {

    private String name;

    private int grade;
    private int cls;
    private int num;

    public static GetAttendanceResponse of(User user,StudentId studentId) {
        return GetAttendanceResponse.builder()
                .name(user.getName())
                .grade(studentId.getGrade())
                .cls(studentId.getCls())
                .num(studentId.getNum()).build();
    }

}
