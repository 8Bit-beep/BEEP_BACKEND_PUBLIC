package com.beep.beep.domain.admin.presentation.dto.response;


import com.beep.beep.domain.student.domain.StudentId;
import com.beep.beep.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder @AllArgsConstructor
public class AdminStudentResponse {
    private Long idx;
    private String id;
    private String firstname;
    private String lastname;
    private String email;

    private int grade;
    private int cls;
    private int num;

    public static AdminStudentResponse of(User user, StudentId studentId) {
        return AdminStudentResponse.builder()
                .idx(user.getIdx())
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .grade(studentId.getGrade())
                .cls(studentId.getCls())
                .num(studentId.getNum()).build();
    }



}
