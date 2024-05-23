package com.beep.beep.domain.auth.presentation.dto.request;

import lombok.Getter;

@Getter
public class SignInReq {
    private String id;
    private String password;
}
