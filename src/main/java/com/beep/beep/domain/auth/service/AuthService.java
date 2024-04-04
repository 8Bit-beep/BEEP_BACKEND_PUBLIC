package com.beep.beep.domain.auth.service;

import com.beep.beep.domain.auth.presentation.dto.request.SignInRequest;
import com.beep.beep.domain.auth.presentation.dto.request.StudentSignUpRequest;
import com.beep.beep.domain.auth.presentation.dto.request.TokenRefreshRequest;
import com.beep.beep.domain.auth.presentation.dto.request.WithdrawalRequest;
import com.beep.beep.domain.auth.presentation.dto.response.SignInResponse;
import com.beep.beep.domain.auth.presentation.dto.response.TokenRefreshResponse;
import com.beep.beep.domain.beep.facade.BeepFacade;
import com.beep.beep.domain.student.facade.StudentFacade;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.enums.UserType;
import com.beep.beep.domain.user.exception.PasswordWrongException;
import com.beep.beep.domain.user.facade.UserFacade;
import com.beep.beep.domain.user.presentation.dto.request.ChangePwRequest;
import com.beep.beep.domain.user.presentation.dto.response.UserIdResponse;
import com.beep.beep.global.security.jwt.JwtProvider;
import com.beep.beep.global.security.jwt.enums.JwtType;
import com.beep.beep.global.security.jwt.exception.TokenTypeException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.transaction.Transactional;
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

//        String accessToken  =  jwtProvider.generateAccessToken(user.getEmail(),user.getAuthority());
//        String refreshToken =  jwtProvider.generateRefreshToken(user.getEmail(),user.getAuthority());

        return SignInResponse.builder()
                .accessToken(jwtProvider.generateAccessToken(user.getEmail(),user.getAuthority()))
                .refreshToken(jwtProvider.generateRefreshToken(user.getEmail(),user.getAuthority()))
                .build();
    }

    public TokenRefreshResponse refresh(TokenRefreshRequest request){
        Jws<Claims> claims = jwtProvider.getClaims(jwtProvider.extractToken(request.getToken())); // 토큰 정보 발췌

        if (jwtProvider.isWrongType(claims, JwtType.REFRESH)) // refresh가 아니면
            throw TokenTypeException.EXCEPTION;

        return TokenRefreshResponse.builder()
                .accessToken(jwtProvider.generateAccessToken(claims.getBody().getSubject(), (UserType) claims.getHeader().get("authority"))).build();
    }

    public UserIdResponse findId(String email) {
        String id = userFacade.findIdByEmail(email);

        return UserIdResponse.builder()
                .id(id).build();
    }

    public void checkIdEmail(String id,String email) {
        userFacade.existsByIdAndEmail(id,email);
    }

    @Transactional
    public void changePw(ChangePwRequest request){
        User user = userFacade.findUserById(request.getId());

        user.updateUser(encoder.encode(request.getPassword()));
    }


}
