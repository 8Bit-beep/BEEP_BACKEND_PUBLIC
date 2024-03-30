package com.beep.beep.global.security.jwt;

import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.enums.UserType;
import com.beep.beep.domain.user.domain.repository.UserRepository;
import com.beep.beep.domain.user.exception.UserNotFoundException;
import com.beep.beep.global.security.auth.AuthDetails;
import com.beep.beep.global.security.jwt.config.JwtProperties;
import com.beep.beep.global.security.jwt.enums.JwtType;
import com.beep.beep.global.security.jwt.exception.ExpiredTokenException;
import com.beep.beep.global.security.jwt.exception.IllegalTokenException;
import com.beep.beep.global.security.jwt.exception.TokenTypeException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import io.jsonwebtoken.Jws;


import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;


@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final JwtProperties jwtProperties;
    private final UserRepository userRepository;

    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);



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

    public Authentication getAuthentication(final String token) {
        final Jws<Claims> claims = getClaims(token);

        if (isWrongType(claims, JwtType.ACCESS)) {
            throw TokenTypeException.EXCEPTION;
        }

        User user = userRepository.findByEmail(claims.getBody().getSubject())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        final AuthDetails details = new AuthDetails(user);

        // 사용자 인증 정보를 생성 후 관리 (객체 생성을 통해)
        // UsernamePasswordAuthenticationToken(Principal, Credentials, Authorities) // 사용자의 주요정보, 인증과정의 비밀번호 저장 안하려고 null , 사용자의 권한 목록
        return new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
    }

    // token 유효성 검증 및 클레임 정보 반환
    public Jws<Claims> getClaims(final String token) {
        try {
            return Jwts.parser().setSigningKey(jwtProperties.getSecretKey()).parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw new IllegalArgumentException("만료된 토큰");
        } catch (UnsupportedJwtException e) {
            throw new IllegalArgumentException("지원되지 않는 토큰");
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("잘못된 토큰");
        }
    }

    // 토큰 타입 검증 로직
    public boolean isWrongType(final Jws<Claims> claims, final JwtType jwtType) {
        return !(claims.getHeader().get(Header.JWT_TYPE).equals(jwtType.toString()));
    }


    public String extractTokenFromRequest(HttpServletRequest request) {
        return extractToken(request.getHeader(HttpHeaders.AUTHORIZATION));
    }

    public String extractToken(final String token) {
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) { // 토큰이 존재하고 "Bearer "로 시작할때
            return token.substring(7); // "Bearer " 제거
        }
        return token; // 토큰 이상하면 그냥 token return
    }


    public String resolveToken(HttpServletRequest request){
        return parseToken(request.getHeader(jwtProperties.getHeader()));
    }

    public User userValidateToken(String token){
        return userRepository.findById(getTokenSubject(token))
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public void validateRefreshToken(String refreshToken) {
        try {
            System.out.println(refreshToken);
            Jwts.parserBuilder().setSigningKey(getSigningKey(jwtProperties.getSecretKey()))
                    .build().parseClaimsJws(refreshToken);
        } catch (ExpiredJwtException e) {
//            throw ExpiredRefreshTokenException.EXCEPTION;
        } catch (Exception e) {
            throw IllegalTokenException.EXCEPTION;
        }
    }

    private String generateToken(String userId,Long exp){
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + exp))
                .signWith(getSigningKey(jwtProperties.getSecretKey()), SignatureAlgorithm.HS256)
                .compact();
    }

    public String parseToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith(jwtProperties.getPrefix())){
            return bearerToken.replace(jwtProperties.getPrefix(),"").replaceAll("\\s", "");
        }
        return null;
    }


    public String getTokenSubject(String token){
        return getTokenBody(token).getSubject();
    }


    private Claims getTokenBody(String token){
        try {
            return Jwts.parserBuilder().setSigningKey(getSigningKey(jwtProperties.getSecretKey()))
                    .build().parseClaimsJws(token).getBody();
        } catch (ExpiredTokenException e) {
            throw ExpiredTokenException.EXCEPTION;
       } catch (Exception e){
            System.out.println("??");
            throw IllegalTokenException.EXCEPTION;
        }
    }

    private Key getSigningKey(String secretKey){
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
