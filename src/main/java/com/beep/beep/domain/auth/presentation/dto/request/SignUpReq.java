package com.beep.beep.domain.auth.presentation.dto.request;

import com.beep.beep.domain.room.domain.Club;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.enums.RoomCode;
import com.beep.beep.domain.user.domain.enums.UserType;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static com.beep.beep.domain.user.domain.enums.UserType.STUDENT;
import static com.beep.beep.domain.user.domain.enums.UserType.TEACHER;

public record SignUpReq(
        String email,
        String password,
        String name,
        UserType authority,
        Integer grade,
        Integer cls,
        Integer num,
        Club club) {

    public User toTeacher(String encodedPassword) {
        return User.builder()
                .email(this.email)
                .password(encodedPassword)
                .name(this.name)
                .authority(TEACHER).build();
    }

    public User toStudent(String encodedPassword) {
        return User.builder()
                .email(this.email)
                .password(encodedPassword)
                .name(this.name)
                .authority(STUDENT)
                .grade(this.grade)
                .cls(this.cls)
                .num(this.num)
                .currentRoom(RoomCode.of("0"))
                .fixedRoom(RoomCode.of(this.club.getCode()))
                .lastUpdated(LocalDateTime.now(ZoneId.of("Asia/Seoul"))).build();
    }

}
