package com.beep.beep.domain.auth.presentation.dto.request;


import com.beep.beep.domain.beep.domain.Attendance;
import com.beep.beep.domain.student.domain.StudentId;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.enums.UserType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentSignUpRequest {
    private String id;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    private UserType authority;

    private int grade;
    private int cls;
    private int num;

    public User toUserEntity(String encodedPassword){
        return User.builder()
                .id(this.id)
                .password(encodedPassword)
                .email(this.email)
                .firstname(this.firstname)
                .lastname(this.lastname)
                .authority(UserType.ROLE_STUDENT).build();
    }

    public StudentId toStudentIdEntity(User user) {
        return StudentId.builder()
                .userIdx(user.getIdx())
                .grade(this.grade)
                .cls(this.cls)
                .num(this.num).build();
    }

    public Attendance toAttendanceEntity(User user) {
        return Attendance.builder()
                .userIdx(user.getIdx())
                .code("404").build();
    }
}
