package com.beep.beep.domain.user.presentation.dto;

import com.beep.beep.domain.student.domain.Student;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.enums.UserType;
import lombok.Builder;

import static com.beep.beep.domain.user.domain.enums.UserType.STUDENT;
import static com.beep.beep.domain.user.domain.enums.UserType.TEACHER;


@Builder
public record UserVO(String email, String password, String name, UserType authority) {
    public static UserVO fromUser(User user) {
        return UserVO.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .authority(user.getAuthority())
                .build();
    }

    public User toUserEntity() {
        return User.builder()
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .authority(this.authority).build();
    }
}
