package com.beep.beep.domain.auth.service;

import com.beep.beep.domain.auth.presentation.dto.request.SignInReq;
import com.beep.beep.domain.auth.presentation.dto.request.SignUpReq;
import com.beep.beep.domain.auth.presentation.dto.request.TokenRefreshReq;
import com.beep.beep.domain.auth.presentation.dto.response.TokenRefreshRes;
import com.beep.beep.domain.auth.presentation.dto.response.TokenRes;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.enums.UserType;
import com.beep.beep.domain.user.exception.PasswordWrongException;
import com.beep.beep.domain.user.exception.StudentIdAlreadyExistsException;
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
import org.springframework.transaction.annotation.Transactional;

import static com.beep.beep.domain.user.domain.enums.UserType.STUDENT;
import static com.beep.beep.domain.user.domain.enums.UserType.TEACHER;

@Service
@RequiredArgsConstructor
public class AuthUseCase {

    private final UserService userService;
    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;
    private final JwtExtractor jwtExtractor;

    @Transactional(rollbackFor = Exception.class)
    public Response signUp(SignUpReq req){
        userService.existsByEmail(req.email());

        if(req.authority() == TEACHER) {
            userService.save(req.toTeacher(encoder.encode(req.password())));
        }else if(req.authority() == STUDENT) {
            if(userService.isStudentIdAlreadyExists(req.grade(),req.cls(),req.num())){
                throw StudentIdAlreadyExistsException.EXCEPTION;
            }
            userService.save(req.toStudent(encoder.encode(req.password())));
        }

        return Response.created("회원가입 성공");
    }

    public ResponseData<TokenRes> signIn(SignInReq req){
        User user = userService.findByEmail(req.email());

        comparePassword(req,user.getPassword());
        return ResponseData.ok("로그인 성공",jwtProvider.generateToken(user.getEmail(), user.getAuthority()));
    }

    public ResponseData<TokenRefreshRes> refresh(TokenRefreshReq req) {
        Jws<Claims> claims = jwtExtractor.getClaims(jwtExtractor.extractToken(req.refreshToken()));

        if (jwtExtractor.isWrongType(claims, JwtType.REFRESH)) {
            throw TokenTypeException.EXCEPTION;
        }

        String subject = claims.getBody().getSubject();
        UserType authority = (UserType) claims.getHeader().get("authority");
        String newAccessToken = jwtProvider.generateAccessToken(subject, authority);

        return ResponseData.ok("토큰 재발급 성공", new TokenRefreshRes(newAccessToken));
    }


    private void comparePassword(SignInReq req, String password){
        if (!encoder.matches(req.password(), password))
            throw PasswordWrongException.EXCEPTION;
    }

}
