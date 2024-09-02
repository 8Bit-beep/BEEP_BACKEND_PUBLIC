package com.beep.beep.global.security.jwt;

import com.beep.beep.domain.auth.presentation.dto.response.TokenRes;
import com.beep.beep.domain.user.domain.enums.UserType;
import com.beep.beep.global.security.jwt.config.JwtProperties;
import com.beep.beep.global.security.jwt.enums.JwtType;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.Date;


@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final JwtProperties jwtProperties;

    public TokenRes generateToken(String email, UserType userType) {
        return TokenRes.builder()
                .accessToken(generateAccessToken(email, userType))
                .refreshToken(generateRefreshToken(email, userType))
                .build();
    }

    public String generateAccessToken(final String email, final UserType authority){
        return Jwts.builder()
                .setHeaderParam(Header.JWT_TYPE, JwtType.ACCESS)
                .setSubject(email)
                .claim("authority", authority)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getAccessExp()))
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

    public String generateRefreshToken(final String email, final UserType authority) {
        return Jwts.builder()
                .setHeaderParam(Header.JWT_TYPE, JwtType.REFRESH)
                .setSubject(email)
                .claim("authority", authority)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getRefreshExp()))
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }


}
