package com.beep.beep.domain.auth.mapper;

import com.beep.beep.domain.auth.presentation.dto.request.SignUpReq;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.enums.UserType;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

    public User toUser(SignUpReq req, UserType authority){
        return User.builder()
                .id(req.getId())
                .password(req.getPassword())
                .email(req.getEmail())
                .name(req.getName())
                .authority(authority).build();
    }

}
