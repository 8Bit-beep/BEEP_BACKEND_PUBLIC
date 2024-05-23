package com.beep.beep.domain.auth.service;

import com.beep.beep.domain.auth.mapper.AuthMapper;
import com.beep.beep.domain.auth.presentation.dto.request.SignInReq;
import com.beep.beep.domain.auth.presentation.dto.request.SignUpReq;
import com.beep.beep.domain.auth.presentation.dto.request.TokenRefreshReq;
import com.beep.beep.domain.auth.presentation.dto.response.SignInRes;
import com.beep.beep.domain.auth.presentation.dto.response.TokenRefreshRes;
import com.beep.beep.domain.user.domain.User;
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
    public void studentSignUp(SignUpReq req){
        signUp(req,STUDENT);
    }

    public void teacherSignUp(SignUpReq req){
        signUp(req,TEACHER);
    }

    public void adminSignUp(SignUpReq req){
        signUp(req,ADMIN);
    }

    public SignInRes signIn(SignInReq req){
        User user = userUtil.findUserById(req.getId());

        if (!encoder.matches(req.getPassword(), user.getPassword()))
            throw PasswordWrongException.EXCEPTION;

        return SignInRes.builder()
                .accessToken(jwtProvider.generateAccessToken(user.getEmail(), user.getAuthority()))
                .refreshToken(jwtProvider.generateRefreshToken(user.getEmail(), user.getAuthority()))
                .build();
    }

    public TokenRefreshRes refresh(TokenRefreshReq req){
        Jws<Claims> claims = jwtExtractor.getClaims(jwtExtractor.extractToken(req.getToken())); // 토큰 정보 발췌

        if (jwtExtractor.isWrongType(claims, JwtType.REFRESH)) // refresh가 아니면
            throw TokenTypeException.EXCEPTION;

        return TokenRefreshRes.builder()
                .accessToken(jwtProvider.generateAccessToken(claims.getBody().getSubject(), (UserType) claims.getHeader().get("authority"))).build();
    }

    private void signUp(SignUpReq req, UserType authority){
        existsById(req.getId()); // id 중복 체크
        userUtil.checkEmail(req.getEmail()); // email 중복 체크

        req.setPassword(encoder.encode(req.getPassword())); // password 인코딩
        userRepository.save(authMapper.toUser(req,authority)); // 저장
    }

    private void existsById(String id){
        userUtil.existsById(id);
    }


}
