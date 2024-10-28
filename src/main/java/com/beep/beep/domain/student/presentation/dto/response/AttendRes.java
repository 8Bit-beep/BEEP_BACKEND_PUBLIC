package com.beep.beep.domain.student.presentation.dto.response;

import com.beep.beep.domain.user.domain.enums.RoomCode;

/**
 * 현재 저장된 코드로 응답 dto
 * */
public record AttendRes(String code) {
    public static AttendRes of(RoomCode code) {
        return new AttendRes(code.getCode());
    }
}
