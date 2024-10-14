package com.beep.beep.domain.room.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Club {
    AND("2206"),
    DGSW42("2208"),
    COMMAND("2209"),
    DION("2211"),
    DUCAMI("2212"),
    D3("3304"),
    ALT("3307"),
    MODI("3309"),
    CNS("3310"),
    BIND("3312");

    private final String code;
}
