package com.beep.beep.domain.user.exception;


import com.beep.beep.domain.user.exception.error.UserErrorProperty;
import com.beep.beep.global.exception.BusinessException;

public class PasswordWrongException extends BusinessException {
    public static final PasswordWrongException EXCEPTION = new PasswordWrongException();

    private PasswordWrongException(){
        super(UserErrorProperty.PASSWORD_WRONG);
    }
}
