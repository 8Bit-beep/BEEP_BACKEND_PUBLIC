package com.beep.beep.domain.user.presentation.dto;

import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.enums.RoomCode;
import com.beep.beep.domain.user.domain.enums.UserType;
import lombok.Builder;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
public record UserVO(String email, String password, String name, UserType authority,
                     Integer grade, Integer cls, Integer num,RoomCode currentRoom,RoomCode fixedRoom) {
    public static UserVO fromUser(User user) {
        if(user.getAuthority() == UserType.TEACHER) {
            return UserVO.builder()
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .name(user.getName())
                    .authority(user.getAuthority())
                    .build();
        } else {
            return UserVO.builder()
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .name(user.getName())
                    .authority(user.getAuthority())
                    .grade(user.getGrade())
                    .cls(user.getCls())
                    .num(user.getNum())
                    .currentRoom(user.getCurrentRoom())
                    .fixedRoom(user.getFixedRoom())
                    .build();
        }
    }

    public User toUserEntity() {
        return User.builder()
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .authority(this.authority)
                .grade(this.grade)
                .cls(this.cls)
                .num(this.num)
                .currentRoom(this.currentRoom)
                .fixedRoom(this.fixedRoom).build();
    }
}
