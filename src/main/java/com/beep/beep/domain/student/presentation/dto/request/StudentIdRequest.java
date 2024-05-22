package com.beep.beep.domain.student.presentation.dto.request;

import lombok.Getter;

@Getter
public class StudentIdRequest {
    private String email;

    private int grade;
    private int cls;
    private int num;
}
