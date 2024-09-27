package com.beep.beep.domain.teacher.presentation.dto.response;

import com.beep.beep.domain.user.presentation.dto.UserVO;
import lombok.Builder;

@Builder
public record TeacherInfoRes(String email, String name) {
    public static TeacherInfoRes of(UserVO user) {
        return TeacherInfoRes.builder()
                .email(user.email())
                .name(user.name()).build();
    }
}
