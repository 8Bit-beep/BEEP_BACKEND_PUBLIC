package com.beep.beep.global.security.jwt.exception;


import com.beep.beep.global.exception.BusinessException;
import com.beep.beep.global.security.jwt.exception.error.JwtErrorProperty;

public class IllegalTokenException extends BusinessException {
    public static final IllegalTokenException EXCEPTION = new IllegalTokenException();

    private IllegalTokenException() {
        super(JwtErrorProperty.ILLEGAL_TOKEN);
    }
}
