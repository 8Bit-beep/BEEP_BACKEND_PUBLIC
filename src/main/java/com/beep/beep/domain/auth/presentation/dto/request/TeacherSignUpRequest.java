package com.beep.beep.domain.auth.presentation.dto.request;

import com.beep.beep.domain.teacher.domain.Job;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.enums.UserType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;



@Getter
public class TeacherSignUpRequest {
    private String id;
    private String password;
    private String email;
    private String name;

    private String department;
    private String job;

    public User toUserEntity(String encodedPassword){
        return User.builder()
                .id(this.id)
                .password(encodedPassword)
                .email(this.email)
                .name(this.name)
                .authority(UserType.TEACHER).build();
    }

    public Job toJobEntity(User user) {
        return Job.builder()
                .userIdx(user.getIdx())
                .department(this.department)
                .job(this.job).build();
    }
}
