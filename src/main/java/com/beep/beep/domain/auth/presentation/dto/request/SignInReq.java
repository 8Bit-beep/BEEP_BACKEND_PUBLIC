package com.beep.beep.domain.auth.presentation.dto.request;

import com.beep.beep.domain.user.domain.enums.UserType;

public record SignInReq(String email, String password, UserType authority) {
}
