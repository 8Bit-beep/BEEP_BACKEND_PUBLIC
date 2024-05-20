package com.beep.beep.domain.user.mapper;


import com.beep.beep.domain.user.domain.UserEntity;
import com.beep.beep.domain.user.presentation.dto.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEdit(User user){
        return UserEntity.builder()
                .password(user.getPassword())
                .build();
    }
}
