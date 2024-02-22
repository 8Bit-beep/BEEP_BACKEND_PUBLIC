package com.beep.beep.domain.user.exception;


import com.beep.beep.domain.user.exception.error.UserErrorProperty;
import com.beep.beep.global.exception.BusinessException;

public class UserAlreadyExistsException extends BusinessException {
    public static final UserAlreadyExistsException EXCEPTION = new UserAlreadyExistsException();

    private UserAlreadyExistsException(){
        super(UserErrorProperty.USER_CONFLICT);
    }

}
