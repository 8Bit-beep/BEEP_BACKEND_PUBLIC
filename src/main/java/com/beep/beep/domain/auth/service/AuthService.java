package com.beep.beep.domain.auth.service;

import com.beep.beep.domain.auth.presentation.dto.request.SignInReq;
import com.beep.beep.domain.auth.presentation.dto.request.SignUpReq;
import com.beep.beep.domain.auth.presentation.dto.request.TokenRefreshReq;
import com.beep.beep.domain.auth.presentation.dto.response.TokenRefreshRes;
import com.beep.beep.domain.auth.presentation.dto.response.TokenRes;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.enums.UserType;
import com.beep.beep.domain.user.exception.PasswordWrongException;
import com.beep.beep.domain.user.service.UserService;
import com.beep.beep.global.common.dto.response.Response;
import com.beep.beep.global.common.dto.response.ResponseData;
import com.beep.beep.global.security.jwt.JwtExtractor;
import com.beep.beep.global.security.jwt.JwtProvider;
import com.beep.beep.global.security.jwt.enums.JwtType;
import com.beep.beep.global.security.jwt.exception.TokenTypeException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;
    private final JwtExtractor jwtExtractor;

    public Response signUp(SignUpReq req){
        userService.existsByEmail(req.email());

        userService.save(req.toUserEntity(encoder.encode(req.password())));
        return Response.created("회원가입 성공");
    }

    public ResponseData<TokenRes> signIn(SignInReq req){
        User user = userService.findByEmail(req.email());

        comparePassword(req,user.getPassword());
        return ResponseData.ok("로그인 성공",jwtProvider.generateToken(user.getEmail(), user.getAuthority()));
    }

    public ResponseData<TokenRefreshRes> refresh(TokenRefreshReq req){
        Jws<Claims> claims = jwtExtractor.getClaims(jwtExtractor.extractToken(req.refreshToken())); // 토큰 정보(payload key:value 들) 발췌

        if (jwtExtractor.isWrongType(claims, JwtType.REFRESH)) // refresh 토큰인지 확인
            throw TokenTypeException.EXCEPTION;

        return ResponseData.ok("토큰 재발급 성공",TokenRefreshRes.builder()
                .accessToken(jwtProvider.generateAccessToken(claims.getBody().getSubject(), (UserType) claims.getHeader().get("authority"))).build());
    }

    private void comparePassword(SignInReq req, String password){
        if (!encoder.matches(req.password(), password))
            throw PasswordWrongException.EXCEPTION;
    }

}
