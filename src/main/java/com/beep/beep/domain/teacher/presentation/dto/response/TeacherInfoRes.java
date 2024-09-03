package com.beep.beep.domain.teacher.presentation.dto.response;

import com.beep.beep.domain.user.domain.User;
import lombok.Builder;

@Builder
public record TeacherInfoRes(String email, String name) {
    public static TeacherInfoRes of(User user) {
        return TeacherInfoRes.builder()
                .email(user.getEmail())
                .name(user.getName()).build();
    }
}
