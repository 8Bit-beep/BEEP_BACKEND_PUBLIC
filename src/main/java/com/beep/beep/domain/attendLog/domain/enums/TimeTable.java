package com.beep.beep.domain.attendLog.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;

@Getter
@RequiredArgsConstructor
public enum TimeTable {
    EIGHT,NINE,TEN,ELEVEN,ETC;

    public static TimeTable of(){
        LocalTime now = LocalTime.now(); // 현재 시간 가져오기

        if (now.isAfter(LocalTime.of(4, 30)) && now.isBefore(LocalTime.of(5, 20))) {
            return EIGHT;
        } else if (now.isAfter(LocalTime.of(5, 30)) && now.isBefore(LocalTime.of(6, 20))) {
            return NINE;
        } else if (now.isAfter(LocalTime.of(7, 10)) && now.isBefore(LocalTime.of(8, 0))) {
            return TEN;
        } else if (now.isAfter(LocalTime.of(8, 10)) && now.isBefore(LocalTime.of(9, 0))) {
            return ELEVEN;
        } else {
            return ETC;
        }
    }
}
