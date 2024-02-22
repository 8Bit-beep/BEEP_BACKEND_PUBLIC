package com.beep.beep.global.security.jwt.exception;


import com.beep.beep.global.exception.BusinessException;
import com.beep.beep.global.security.jwt.exception.error.JwtErrorProperty;

public class TokenNotFoundException extends BusinessException {
    public static final TokenNotFoundException EXCEPTION = new TokenNotFoundException();

    private TokenNotFoundException(){
        super(JwtErrorProperty.TOKEN_NOT_FOUND);
    }
}
