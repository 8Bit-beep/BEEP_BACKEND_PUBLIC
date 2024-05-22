package com.beep.beep.domain.beep.presentation.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder @AllArgsConstructor
public class Room {
    private String code;
    private String floor;
    private String name;
}
