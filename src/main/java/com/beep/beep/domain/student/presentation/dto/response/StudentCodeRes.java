package com.beep.beep.domain.student.presentation.dto.response;

import lombok.Builder;

@Builder
public record StudentCodeRes(String code) {
    public static StudentCodeRes of(String code) {
        return StudentCodeRes.builder()
                .code(code).build();
    }
}
