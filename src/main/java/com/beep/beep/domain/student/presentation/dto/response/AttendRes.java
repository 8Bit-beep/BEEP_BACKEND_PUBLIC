package com.beep.beep.domain.student.presentation.dto.response;

import com.beep.beep.domain.student.domain.enums.RoomCode;
import lombok.Builder;

@Builder
public record AttendRes(String code) {
    public static AttendRes of(String code) {
        return AttendRes.builder().code(code).build();
    }
}
