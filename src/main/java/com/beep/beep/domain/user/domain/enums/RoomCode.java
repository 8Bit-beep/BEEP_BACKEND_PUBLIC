package com.beep.beep.domain.user.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum RoomCode {
    NOTFOUND("0"),
    PROJECT1("1101"),PROJECT2("1102"),
    LAB1("2201"), LAB2("2202"),
    PROJECT3("2203"),
    SERVER("2204"),
    PROJECT4("2205"),
    LAB3_4("2206"), LAB5("2208"),
    LAB6_7("2209"), LAB8("2211"),
    LAB9_10("2212"), LAB11("2214"),
    LAB12("3301"),LAB13("3302"),
    PROJECT5("3303"),
    PRINTER_MAKER("3304"),PROJECT6("3306"),
    LAB14_15("3307"),LAB16("3309"),
    LAB17_18("3310"),LAB19_20("3312"),
    LAB21("3314"),LAB22("3315");

    private final String code;

    public static RoomCode of(String code) {
        return Arrays.stream(values())
                .filter(value -> value.code.equals(code))
                .findAny()
                .orElse(NOTFOUND);
    }
}
