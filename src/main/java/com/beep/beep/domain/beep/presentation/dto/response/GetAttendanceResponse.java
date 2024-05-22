package com.beep.beep.domain.beep.presentation.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder @AllArgsConstructor
public class GetAttendanceResponse {

    private String userName;
    private int grade;
    private int cls;
    private int num;

}
