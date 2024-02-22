package com.beep.beep.domain.user.exception;


import com.beep.beep.domain.user.exception.error.UserErrorProperty;
import com.beep.beep.global.exception.BusinessException;

public class UserNotFoundException extends BusinessException {
    public static final UserNotFoundException EXCEPTION = new UserNotFoundException();

    private UserNotFoundException() {
        super(UserErrorProperty.USER_NOT_FOUND);
    }
}
