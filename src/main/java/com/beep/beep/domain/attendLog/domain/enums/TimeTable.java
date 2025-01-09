package com.beep.beep.domain.attendLog.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;
import java.time.ZoneId;


/**
 * 교시 enum(EIGHT,NINE,TEN,ELEVEN,ETC)
 * */
@Getter
@RequiredArgsConstructor
public enum TimeTable {
    ONE("ONE"),
    TWO("TWO"),
    THREE("THREE"),
    FOUR("FOUR"),
    ETC("ETC");

    private final String value;
    public static TimeTable of(){
        LocalTime now = LocalTime.now(ZoneId.of("Asia/Seoul")); // 현재 시간 가져오기

        if (now.isAfter(LocalTime.of(9, 0)) && now.isBefore(LocalTime.of(13, 5))) {
            return ONE;
        } else if (now.isAfter(LocalTime.of(13, 5)) && now.isBefore(LocalTime.of(15, 55))) {
            return TWO;
        } else if (now.isAfter(LocalTime.of(16, 0)) && now.isBefore(LocalTime.of(20, 43))) {
            return THREE;
        } else if (now.isAfter(LocalTime.of(20, 43)) && now.isBefore(LocalTime.of(22, 5))) {
            return FOUR;
        }else {
            return ETC;
        }
    }
}
