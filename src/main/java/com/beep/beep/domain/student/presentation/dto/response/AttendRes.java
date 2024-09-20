package com.beep.beep.domain.student.presentation.dto.response;


public record AttendRes(String code) {
    public static AttendRes of(String code) {
        return new AttendRes(code);
    }
}
