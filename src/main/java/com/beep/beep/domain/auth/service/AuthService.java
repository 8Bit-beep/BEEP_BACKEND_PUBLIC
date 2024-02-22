package com.beep.beep.domain.auth.service;

import com.beep.beep.domain.auth.domain.Token;
import com.beep.beep.domain.auth.facade.TokenFacade;
import com.beep.beep.domain.auth.presentation.dto.request.SignInRequest;
import com.beep.beep.domain.auth.presentation.dto.request.StudentSignUpRequest;
import com.beep.beep.domain.auth.presentation.dto.request.WithdrawalRequest;
import com.beep.beep.domain.auth.presentation.dto.response.SignInResponse;
import com.beep.beep.domain.auth.presentation.dto.response.TokenRefreshResponse;
import com.beep.beep.domain.beep.facade.BeepFacade;
import com.beep.beep.domain.student.facade.StudentFacade;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.exception.PasswordWrongException;
import com.beep.beep.domain.user.facade.UserFacade;
import com.beep.beep.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;
    private final UserFacade userFacade;
    private final StudentFacade studentFacade;
    private final BeepFacade beepFacade;
    private final TokenFacade tokenFacade;

    public void idCheck(String id) {
        userFacade.existsById(id);
    }

    public void studentSignUp(StudentSignUpRequest request){
        userFacade.save(request.toUserEntity(encoder.encode(request.getPassword())));
        User user = userFacade.findUserById(request.getId());

        studentFacade.save(request.toStudentIdEntity(user));
        beepFacade.save(request.toAttendanceEntity(user));
    }

    public SignInResponse signIn(SignInRequest request){
        User user = userFacade.findUserById(request.getId());

        if (!encoder.matches(request.getPassword(), user.getPassword()))
            throw PasswordWrongException.EXCEPTION;

        String accessToken  =  jwtProvider.generateAccessToken(user.getId());
        String refreshToken =  jwtProvider.generateRefreshToken(user.getId());

        tokenFacade.saveTokenInfo(user.getId(),refreshToken,accessToken);

        return SignInResponse.builder()
                .accessToken(accessToken).build();
    }

    public TokenRefreshResponse tokenRefresh(String accessToken){
        accessToken = jwtProvider.validateAccessToken(accessToken);

        return TokenRefreshResponse.builder()
                .accessToken(accessToken).build();
    }

    public void logout(String accessToken){
        Token tokenInfo = tokenFacade.findByAccessToken(jwtProvider.parseToken(accessToken));
        jwtProvider.validateRefreshToken(tokenInfo.getRefreshToken());

        tokenFacade.delete(tokenInfo);
    }

    public void withdrawal(String accessToken, WithdrawalRequest request) {
        User user = userFacade.findUserById(jwtProvider.getTokenSubject(jwtProvider.parseToken(accessToken)));

        if (!encoder.matches(request.getPassword(), user.getPassword()))
            throw PasswordWrongException.EXCEPTION;

        userFacade.delete(user);

    }




}
