package com.beep.beep.domain.auth.presentation.dto.request;

import lombok.Getter;

@Getter
public class StudentSignUpRequest {
    private String id;
    private String password;
    private String email;
    private String name;

    private int grade;
    private int cls;
    private int num;
}
