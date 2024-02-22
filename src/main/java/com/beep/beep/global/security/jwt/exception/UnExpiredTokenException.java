package com.beep.beep.global.security.jwt.exception;

import com.beep.beep.global.exception.BusinessException;
import com.beep.beep.global.security.jwt.exception.error.JwtErrorProperty;

public class UnExpiredTokenException extends BusinessException {
    public static final UnExpiredTokenException EXCEPTION = new UnExpiredTokenException();

    private UnExpiredTokenException() {
        super(JwtErrorProperty.UNEXPIRED_TOKEN);
    }
}
