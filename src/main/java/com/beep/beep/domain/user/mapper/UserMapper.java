package com.beep.beep.domain.user.mapper;


import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.presentation.dto.UserVO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEdit(UserVO userVO){
        return User.builder()
                .password(userVO.getPassword())
                .build();
    }

    public UserVO toUserDto(User user){
        return UserVO.builder()
                .idx(user.getIdx())
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .authority(user.getAuthority()).build();
    }
}
