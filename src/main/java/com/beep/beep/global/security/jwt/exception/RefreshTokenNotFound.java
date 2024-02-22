package com.beep.beep.global.security.jwt.exception;


import com.beep.beep.global.exception.BusinessException;
import com.beep.beep.global.security.jwt.exception.error.JwtErrorProperty;

public class RefreshTokenNotFound extends BusinessException {
    public static final RefreshTokenNotFound EXCEPTION = new RefreshTokenNotFound();

    private RefreshTokenNotFound(){
        super(JwtErrorProperty.REFRESH_NOT_FOUND);
    }
}
