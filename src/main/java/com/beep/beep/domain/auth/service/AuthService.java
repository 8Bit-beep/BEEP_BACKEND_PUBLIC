package com.beep.beep.domain.auth.service;

import com.beep.beep.domain.auth.presentation.dto.request.SignInReq;
import com.beep.beep.domain.auth.presentation.dto.request.SignUpReq;
import com.beep.beep.domain.auth.presentation.dto.request.TokenRefreshReq;
import com.beep.beep.domain.auth.presentation.dto.response.TokenRefreshRes;
import com.beep.beep.domain.auth.presentation.dto.response.TokenRes;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.enums.UserType;
import com.beep.beep.domain.user.domain.repo.UserJpaRepo;
import com.beep.beep.domain.user.exception.PasswordWrongException;
import com.beep.beep.domain.user.exception.UserAlreadyExistsException;
import com.beep.beep.domain.user.exception.UserNotFoundException;
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

    private final UserJpaRepo userJpaRepo;
    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;
    private final JwtExtractor jwtExtractor;

    public void signUp(SignUpReq req){
        if(userJpaRepo.existsById(req.email()))
            throw UserAlreadyExistsException.EXCEPTION;

        userJpaRepo.save(req.toUserEntity(encoder.encode(req.password())));
    }

    public TokenRes signIn(SignInReq req){
        User user = userJpaRepo.findById(req.email())
                        .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        comparePassword(req,user.getPassword());
        return jwtProvider.generateToken(user.getEmail(), user.getAuthority());
    }

    public TokenRefreshRes refresh(TokenRefreshReq req){
        Jws<Claims> claims = jwtExtractor.getClaims(jwtExtractor.extractToken(req.refreshToken())); // 토큰 정보(payload key:value 들) 발췌

        if (jwtExtractor.isWrongType(claims, JwtType.REFRESH)) // refresh 토큰인지 확인
            throw TokenTypeException.EXCEPTION;

        return TokenRefreshRes.builder()
                .accessToken(jwtProvider.generateAccessToken(claims.getBody().getSubject(), (UserType) claims.getHeader().get("authority"))).build();
    }

    private void comparePassword(SignInReq req, String password){
        if (!encoder.matches(req.password(), password))
            throw PasswordWrongException.EXCEPTION;
    }

}
