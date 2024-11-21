package com.beep.beep.domain.user.domain.enums;

import com.beep.beep.domain.student.exception.RoomNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum RoomCode {
    NOTFOUND("0",0),
    PROJECT1("1101",1),PROJECT2("1102",1),
    LAB1("2201",2), LAB2("2202",2),
    PROJECT3("2203",2),
    SERVER("2204",2),
    PROJECT4("2205",2),
    LAB3_4("2206",2), LAB5("2208",2),
    LAB6_7("2209",2), LAB8("2211",2),
    LAB9_10("2212",2), LAB11("2214",2),
    LAB12("3301",3),LAB13("3302",3),
    PROJECT5("3303",3),
    PRINTER_MAKER("3304",3),PROJECT6("3306",3),
    LAB14_15("3307",3),LAB16("3309",3),
    LAB17_18("3310",3),LAB19_20("3312",3),
    LAB21("3314",3),LAB22("3315",3);

    private final String code;
    private final Integer floor;

    // Find RoomCode by code
    public static RoomCode of(String code) {
        return Arrays.stream(values())
                .filter(value -> value.code.equals(code))
                .findAny()
                .orElseThrow(() -> RoomNotFoundException.EXCEPTION);
    }

    // Find RoomCodes by floor
    public static List<RoomCode> findByFloor(Integer floor) {
        return Arrays.stream(values())
                .filter(value -> value.floor.equals(floor))
                .collect(Collectors.toList());
    }

}
