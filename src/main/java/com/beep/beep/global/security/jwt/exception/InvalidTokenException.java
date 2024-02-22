package com.beep.beep.global.security.jwt.exception;


import com.beep.beep.global.exception.BusinessException;
import com.beep.beep.global.security.jwt.exception.error.JwtErrorProperty;

public class InvalidTokenException extends BusinessException {
    public static final InvalidTokenException EXCEPTION = new InvalidTokenException();

    private InvalidTokenException() {
        super(JwtErrorProperty.INVALID_TOKEN);
    }
}
