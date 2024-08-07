package com.beep.beep.domain.student.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoomCode {
    LAB1("2201"), LAB2("2202"),
    PROJECT3("2203"),
    SERVER("2204"),
    PROJECT4("2205"),
    LAB3("2206"), LAB4("2207"), LAB5("2208"),
    LAB6("2209"), LAB7("2210"), LAB8("2211"),
    LAB9("2212"), LAB10("2213"), LAB11("2214");

    private final String code;
}
