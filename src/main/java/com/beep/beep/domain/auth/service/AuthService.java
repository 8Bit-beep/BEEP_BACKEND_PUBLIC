package com.beep.beep.domain.auth.service;

import com.beep.beep.domain.auth.mapper.AuthMapper;
import com.beep.beep.domain.auth.presentation.dto.request.SignInRequest;
import com.beep.beep.domain.auth.presentation.dto.request.SignUpRequest;
import com.beep.beep.domain.auth.presentation.dto.request.TokenRefreshRequest;
import com.beep.beep.domain.auth.presentation.dto.response.SignInResponse;
import com.beep.beep.domain.auth.presentation.dto.response.TokenRefreshResponse;
import com.beep.beep.domain.user.domain.UserEntity;
import com.beep.beep.domain.user.domain.enums.UserType;
import com.beep.beep.domain.user.domain.repository.UserRepository;
import com.beep.beep.domain.user.exception.PasswordWrongException;
import com.beep.beep.global.common.service.UserUtil;
import com.beep.beep.global.security.jwt.JwtExtractor;
import com.beep.beep.global.security.jwt.JwtProvider;
import com.beep.beep.global.security.jwt.enums.JwtType;
import com.beep.beep.global.security.jwt.exception.TokenTypeException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.beep.beep.domain.user.domain.enums.UserType.ADMIN;
import static com.beep.beep.domain.user.domain.enums.UserType.STUDENT;
import static com.beep.beep.domain.user.domain.enums.UserType.TEACHER;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;
    private final JwtExtractor jwtExtractor;
    private final AuthMapper authMapper;
    private final UserRepository userRepository;
    private final UserUtil userUtil;

    /** 학생 회원가입 */
    public void studentSignUp(SignUpRequest request){
        signUp(request,STUDENT);
    }

    public void teacherSignUp(SignUpRequest request){
        signUp(request,TEACHER);
    }

    public void adminSignUp(SignUpRequest request){
        signUp(request,ADMIN);
    }

    public SignInResponse signIn(SignInRequest request){
        UserEntity user = userUtil.findUserById(request.getId());

        if (!encoder.matches(request.getPassword(), user.getPassword()))
            throw PasswordWrongException.EXCEPTION;

        return SignInResponse.builder()
                .accessToken(jwtProvider.generateAccessToken(user.getEmail(), user.getAuthority()))
                .refreshToken(jwtProvider.generateRefreshToken(user.getEmail(), user.getAuthority()))
                .build();
    }

    public TokenRefreshResponse refresh(TokenRefreshRequest request){
        Jws<Claims> claims = jwtExtractor.getClaims(jwtExtractor.extractToken(request.getToken())); // 토큰 정보 발췌

        if (jwtExtractor.isWrongType(claims, JwtType.REFRESH)) // refresh가 아니면
            throw TokenTypeException.EXCEPTION;

        return TokenRefreshResponse.builder()
                .accessToken(jwtProvider.generateAccessToken(claims.getBody().getSubject(), (UserType) claims.getHeader().get("authority"))).build();
    }

    private void signUp(SignUpRequest request,UserType authority){
        existsById(request.getId()); // id 중복 체크
        userUtil.checkEmail(request.getEmail()); // email 중복 체크

        request.setPassword(encoder.encode(request.getPassword())); // password 인코딩
        userRepository.save(authMapper.toUser(request,authority)); // 저장
    }

    private void existsById(String id){
        userUtil.existsById(id);
    }


}
