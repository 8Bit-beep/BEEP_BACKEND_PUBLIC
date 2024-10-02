package com.beep.beep.domain.attendLog.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;

@Getter
@RequiredArgsConstructor
public enum TimeTable {
    EIGHT("EIGHT"),
    NINE("NINE"),
    TEN("TEN"),
    ELEVEN("ELEVEN"),
    ETC("ETC");

    private final String value;
    public static TimeTable of(){
        LocalTime now = LocalTime.now(); // 현재 시간 가져오기

        if (now.isAfter(LocalTime.of(16, 30)) && now.isBefore(LocalTime.of(17, 29))) {
            return EIGHT;
        } else if (now.isAfter(LocalTime.of(17, 30)) && now.isBefore(LocalTime.of(18, 30))) {
            return NINE;
        } else if (now.isAfter(LocalTime.of(19, 10)) && now.isBefore(LocalTime.of(20, 9))) {
            return TEN;
        } else if (now.isAfter(LocalTime.of(20, 10)) && now.isBefore(LocalTime.of(21, 40))) {
            return ELEVEN;
        } else {
            return ETC;
        }
    }
}
