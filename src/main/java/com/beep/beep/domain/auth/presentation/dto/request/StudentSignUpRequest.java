package com.beep.beep.domain.auth.presentation.dto.request;


import com.beep.beep.domain.beep.domain.Attendance;
import com.beep.beep.domain.student.domain.StudentId;
import com.beep.beep.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import static com.beep.beep.domain.user.domain.enums.UserType.STUDENT;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentSignUpRequest {
    private String id;
    private String password;
    private String email;
    private String name;

    private int grade;
    private int cls;
    private int num;

    public User toUserEntity(String encodedPassword){
        return User.builder()
                .id(this.id)
                .password(encodedPassword)
                .email(this.email)
                .name(this.name)
                .authority(STUDENT).build();
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
