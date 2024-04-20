package com.beep.beep.domain.auth.presentation.dto.request;


import com.beep.beep.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import static com.beep.beep.domain.user.domain.enums.UserType.ADMIN;

@Getter
@Builder
@AllArgsConstructor
public class AdminSignUpRequest {
    private String id;
    private String password;
    private String email;
    private String name;

    public User toEntity(String encryptedPassword){
        return User.builder()
                .id(this.id)
                .password(encryptedPassword)
                .email(this.email)
                .name(this.name)
                .authority(ADMIN).build();
    }

}
