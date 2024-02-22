package com.beep.beep.global.security.jwt.exception;


import com.beep.beep.global.exception.BusinessException;
import com.beep.beep.global.security.jwt.exception.error.JwtErrorProperty;

public class ExpiredTokenException extends BusinessException {
    public static final ExpiredTokenException EXCEPTION = new ExpiredTokenException();

    private ExpiredTokenException() {
        super(JwtErrorProperty.EXPIRED_TOKEN);
    }
}
