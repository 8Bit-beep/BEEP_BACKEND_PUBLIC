package com.beep.beep.global.security.jwt.exception;


import com.beep.beep.global.exception.BusinessException;
import com.beep.beep.global.security.jwt.exception.error.JwtErrorProperty;

public class RefreshTokenNotFoundException extends BusinessException {
    public static final RefreshTokenNotFoundException EXCEPTION = new RefreshTokenNotFoundException();

    private RefreshTokenNotFoundException(){
        super(JwtErrorProperty.REFRESH_NOT_FOUND);
    }

}
