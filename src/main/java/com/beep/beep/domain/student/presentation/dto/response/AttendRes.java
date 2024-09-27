package com.beep.beep.domain.student.presentation.dto.response;

import com.beep.beep.domain.user.domain.enums.RoomCode;

public record AttendRes(String code) {
    public static AttendRes of(RoomCode code) {
        return new AttendRes(code.getCode());
    }
}
