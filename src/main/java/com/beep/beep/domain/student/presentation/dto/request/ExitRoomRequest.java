package com.beep.beep.domain.beep.presentation.dto.request;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExitRoomRequest {

    private String code;

}
