package com.beep.beep.domain.room.domain;

import com.beep.beep.domain.user.domain.enums.RoomCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum Club {
    AND("2206",2),
    DGSW42("2208",2),
    COMMAND("2209",2),
    DION("2211",2),
    DUCAMI("2212",2),
    D3("3304",3),
    ALT("3307",3),
    MODI("3309",3),
    CNS("3310",3),
    BIND("3312",3);

    private final String code;
    private final Integer floor;

    // Find Club by code
    public static Club of(String code) {
        return Arrays.stream(values())
                .filter(value -> value.code.equals(code))
                .findAny()
                .orElse(null);
    }

    // Find Clubs by floor
    public static List<Club> findByFloor(Integer floor) {
        return Arrays.stream(values())
                .filter(value -> value.floor.equals(floor))
                .collect(Collectors.toList());
    }

}
