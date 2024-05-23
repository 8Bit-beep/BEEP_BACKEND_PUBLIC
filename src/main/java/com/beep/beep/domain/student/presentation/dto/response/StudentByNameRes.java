package com.beep.beep.domain.student.presentation.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder @AllArgsConstructor
public class StudentByNameRes {

    private String name;

    private int grade;
    private int cls;
    private int num;

    private String roomName;

}
