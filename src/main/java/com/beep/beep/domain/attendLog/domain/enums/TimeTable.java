package com.beep.beep.domain.attendLog.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;

@Getter
@RequiredArgsConstructor
public enum TimeTable {
    EIGHT("EIGHT",17,25,0),
    NINE("NINE",18,20,0),
    TEN("TEN",20,0,0),
    ELEVEN("ELEVEN",20,45,0),
    ELEVEN2("ELEVEN2",21,30,0),
    ETC("ETC",0,0,0);

    private final String value;
    private final Integer hour;
    private final Integer minute;
    private final Integer second;

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
