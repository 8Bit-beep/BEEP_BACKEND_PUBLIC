package com.beep.beep.domain.auth.service;

import com.beep.beep.domain.auth.presentation.dto.request.SignInReq;
import com.beep.beep.domain.auth.presentation.dto.request.StudentSignUpReq;
import com.beep.beep.domain.auth.presentation.dto.request.TeacherSignUpReq;
import com.beep.beep.domain.auth.presentation.dto.request.TokenRefreshReq;
import com.beep.beep.domain.auth.presentation.dto.response.SignInRes;
import com.beep.beep.domain.auth.presentation.dto.response.TokenRefreshRes;
import com.beep.beep.domain.student.domain.Student;
import com.beep.beep.domain.student.domain.repository.StudentJpaRepo;
import com.beep.beep.domain.teacher.domain.Teacher;
import com.beep.beep.domain.teacher.domain.repository.TeacherJpaRepo;
import com.beep.beep.domain.user.domain.enums.UserType;
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

import static com.beep.beep.domain.user.domain.enums.UserType.STUDENT;
import static com.beep.beep.domain.user.domain.enums.UserType.TEACHER;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final StudentJpaRepo studentJpaRepo;
    private final TeacherJpaRepo teacherJpaRepo;
    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;
    private final JwtExtractor jwtExtractor;

    public void studentSignUp(StudentSignUpReq req){
        if(studentJpaRepo.existsByEmail(req.email()))
            throw UserAlreadyExistsException.EXCEPTION;

        studentJpaRepo.save(req.toStudentEntity(encoder.encode(req.password())));
    }

    public void teacherSignUp(TeacherSignUpReq req){
        if(teacherJpaRepo.existsByEmail(req.email()))
            throw UserAlreadyExistsException.EXCEPTION;

        teacherJpaRepo.save(req.toTeacherEntity(encoder.encode(req.password())));
    }

    public SignInRes signIn(SignInReq req){
        if(req.authority() == STUDENT){
            Student student = studentJpaRepo.findByEmail(req.email())
                    .orElseThrow(() -> UserNotFoundException.EXCEPTION);

            comparePassword(req,student.getPassword());

            return SignInRes.builder()
                    .accessToken(jwtProvider.generateAccessToken(student.getEmail(), STUDENT))
                    .refreshToken(jwtProvider.generateRefreshToken(student.getEmail(), STUDENT))
                    .build();
        } else if (req.authority() == TEACHER){
            Teacher teacher = teacherJpaRepo.findByEmail(req.email())
                    .orElseThrow(() -> UserNotFoundException.EXCEPTION);

            comparePassword(req,teacher.getPassword());

            return SignInRes.builder()
                    .accessToken(jwtProvider.generateAccessToken(teacher.getEmail(), TEACHER))
                    .refreshToken(jwtProvider.generateRefreshToken(teacher.getEmail(), TEACHER))
                    .build();
        }
        return null;
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
