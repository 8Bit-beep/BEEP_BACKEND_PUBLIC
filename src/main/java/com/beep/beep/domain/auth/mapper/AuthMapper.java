package com.beep.beep.domain.auth.mapper;

import com.beep.beep.domain.auth.presentation.dto.request.SignUpRequest;
import com.beep.beep.domain.user.domain.UserEntity;
import com.beep.beep.domain.user.domain.enums.UserType;
import org.springframework.stereotype.Component;

import static com.beep.beep.domain.user.domain.enums.UserType.ADMIN;
import static com.beep.beep.domain.user.domain.enums.UserType.TEACHER;

@Component
public class AuthMapper {

    public UserEntity toUser(SignUpRequest request, UserType authority){
        return UserEntity.builder()
                .id(request.getId())
                .password(request.getPassword())
                .email(request.getEmail())
                .name(request.getName())
                .authority(authority).build();
    }

}
