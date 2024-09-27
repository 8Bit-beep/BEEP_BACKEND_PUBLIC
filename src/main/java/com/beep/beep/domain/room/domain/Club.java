package com.beep.beep.domain.room.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Club {
    BIND("2201"),
    DUCAMI("2202"),
    CNS("2203"),
    ALT("2204"),
    D3("2205"),
    MODI("2206"),
    DION("2207"),
    AND("2208"),
    COMMAND("2209"),
    DGSW42("2210");

    private final String code;
}
