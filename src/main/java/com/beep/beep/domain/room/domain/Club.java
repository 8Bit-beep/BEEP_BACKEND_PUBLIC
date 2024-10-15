package com.beep.beep.domain.room.domain;

import com.beep.beep.domain.user.domain.enums.RoomCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

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

    // Find Club by code
    public static Club of(String code) {
        return Arrays.stream(values())
                .filter(value -> value.code.equals(code))
                .findAny()
                .orElse(null);
    }
}
