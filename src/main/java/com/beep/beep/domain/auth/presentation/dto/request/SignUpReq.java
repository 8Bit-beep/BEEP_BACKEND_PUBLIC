package com.beep.beep.domain.auth.presentation.dto.request;

import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.enums.UserType;

public record SignUpReq(String email, String password, String name, UserType authority) {
    public User toUserEntity(String encodedPassword) {
        return User.builder()
                .email(this.email)
                .password(encodedPassword)
                .name(this.name)
                .authority(this.authority).build();
    }
}
