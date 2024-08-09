package com.beep.beep.domain.student.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoomCode {
    PROJECT1("1101"),PROJECT2("1102"),
    LAB1("2201"), LAB2("2202"),
    PROJECT3("2203"),
    SERVER("2204"),
    PROJECT4("2205"),
    LAB3("2206"), LAB4("2207"), LAB5("2208"),
    LAB6("2209"), LAB7("2210"), LAB8("2211"),
    LAB9("2212"), LAB10("2213"), LAB11("2214"),
    LAB12("3301"),LAB13("3302"),
    PROJECT5("3303"),PRINTER("3304"),
    MAKER("3305"),PROJECT6("3306"),
    LAB14("3307"),LAB15("3308"),LAB16("3309"),
    LAB17("3310"),LAB18("3311"),LAB19("3312"),
    LAB20("3313"),LAB21("3314"),LAB22("3315");

    private final String code;
}
