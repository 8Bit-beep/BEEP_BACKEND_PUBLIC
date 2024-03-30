package com.beep.beep.global.security.jwt.exception;

import com.beep.beep.global.exception.BusinessException;
import com.beep.beep.global.security.jwt.exception.error.JwtErrorProperty;

public class UnsupportedTokenException extends BusinessException {
    public static final UnsupportedTokenException EXCEPTION = new UnsupportedTokenException();

    private UnsupportedTokenException() {
        super(JwtErrorProperty.UNSUPPORTED_TOKEN);
    }
}
