package com.beep.beep.domain.auth.presentation.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class SignInRes {
    private String accessToken;
    private String refreshToken;
}
