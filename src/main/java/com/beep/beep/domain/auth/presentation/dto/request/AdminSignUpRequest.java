package com.beep.beep.domain.auth.presentation.dto.request;

import lombok.Getter;

@Getter
public class AdminSignUpRequest {
    private String id;
    private String password;
    private String email;
    private String name;
}
