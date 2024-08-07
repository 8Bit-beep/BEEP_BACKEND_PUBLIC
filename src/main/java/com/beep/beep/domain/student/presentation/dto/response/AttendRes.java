package com.beep.beep.domain.student.presentation.dto.response;

import com.beep.beep.domain.student.domain.enums.RoomCode;
import lombok.Builder;

@Builder
public record AttendRes(RoomCode code) {
    public static AttendRes of(RoomCode code) {
        return AttendRes.builder().code(code).build();
    }
}
