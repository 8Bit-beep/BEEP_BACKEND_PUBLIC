package com.beep.beep.domain.auth.presentation.dto.request;

import lombok.Getter;

@Getter
public class SignInRequest {
    private String id;
    private String password;
}
