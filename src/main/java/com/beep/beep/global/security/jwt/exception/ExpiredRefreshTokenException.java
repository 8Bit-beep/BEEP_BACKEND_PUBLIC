package com.beep.beep.global.security.jwt.exception;


import com.beep.beep.global.exception.BusinessException;
import com.beep.beep.global.security.jwt.exception.error.JwtErrorProperty;

public class ExpiredRefreshTokenException extends BusinessException {
    public static final ExpiredRefreshTokenException EXCEPTION = new ExpiredRefreshTokenException();

    private ExpiredRefreshTokenException(){
        super(JwtErrorProperty.REFRESH_EXPIRED);
    }

}
