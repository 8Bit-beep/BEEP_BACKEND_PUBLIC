package com.beep.beep.domain.auth.service;

import com.beep.beep.domain.auth.mapper.AuthMapper;
import com.beep.beep.domain.auth.presentation.dto.request.AdminSignUpRequest;
import com.beep.beep.domain.auth.presentation.dto.request.TeacherSignUpRequest;
import com.beep.beep.domain.auth.presentation.dto.request.SignInRequest;
import com.beep.beep.domain.auth.presentation.dto.request.StudentSignUpRequest;
import com.beep.beep.domain.auth.presentation.dto.request.TokenRefreshRequest;
import com.beep.beep.domain.auth.presentation.dto.response.SignInResponse;
import com.beep.beep.domain.auth.presentation.dto.response.TokenRefreshResponse;
import com.beep.beep.domain.user.domain.UserEntity;
import com.beep.beep.domain.user.domain.enums.UserType;
import com.beep.beep.domain.user.domain.repository.UserRepository;
import com.beep.beep.domain.user.exception.PasswordWrongException;
import com.beep.beep.domain.user.facade.UserFacade;
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

    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;
    private final UserFacade userFacade;
    private final AuthMapper authMapper;
    private final UserRepository userRepository;
    private final JwtExtractor jwtExtractor;

    public void studentSignUp(StudentSignUpRequest request){
        userFacade.existsById(request.getId());

        userRepository.save(authMapper.toStudent(encoder.encode(request.getPassword()),request));
    }

    public void teacherSignUp(TeacherSignUpRequest request){
        userFacade.existsById(request.getId());

        userRepository.save(authMapper.toTeacher(encoder.encode(request.getPassword()),request));


    }

    public void adminSignUp(AdminSignUpRequest request){
        userFacade.existsById(request.getId());

        UserEntity userEntity = authMapper.toAdmin(encoder.encode(request.getPassword()),request);
        userRepository.save(userEntity);
    }

    public SignInResponse signIn(SignInRequest request){
        UserEntity user = userFacade.findUserById(request.getId());

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


}
