package com.beep.beep.domain.user.presentation.dto.request;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ChangePwRequest {
    private String id;
    private String password;
}
