package com.beep.beep.global.security.jwt.exception;


import com.beep.beep.global.exception.BusinessException;
import com.beep.beep.global.security.jwt.exception.error.JwtErrorProperty;

public class TokenTypeException extends BusinessException {
    public static final TokenTypeException EXCEPTION = new TokenTypeException();

    private TokenTypeException(){
        super(JwtErrorProperty.TOKEN_TYPE_ERROR);
    }

}
