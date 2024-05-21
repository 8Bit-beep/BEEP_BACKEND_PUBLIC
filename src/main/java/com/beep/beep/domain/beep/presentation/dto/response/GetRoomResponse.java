package com.beep.beep.domain.beep.presentation.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder @AllArgsConstructor
public class GetRoomResponse {
    private String code;
    private String floor;
    private String name;
}
