package com.beep.beep.domain.user.presentation.dto.request;


import lombok.Getter;

@Getter
public class ChangePwReq {
    private String email;
    private String password;
}
