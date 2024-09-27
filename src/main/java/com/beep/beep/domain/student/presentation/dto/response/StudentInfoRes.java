package com.beep.beep.domain.student.presentation.dto.response;

import com.beep.beep.domain.user.presentation.dto.UserVO;
import lombok.Builder;

@Builder
public record StudentInfoRes(String email,String name,Integer grade,Integer cls,Integer num) {
    public static StudentInfoRes of(UserVO user) {
        return StudentInfoRes.builder()
                .email(user.email())
                .name(user.name())
                .grade(user.grade())
                .cls(user.cls())
                .num(user.num())
                .grade(user.grade())
                .cls(user.cls())
                .num(user.num()).build();
    }
}
