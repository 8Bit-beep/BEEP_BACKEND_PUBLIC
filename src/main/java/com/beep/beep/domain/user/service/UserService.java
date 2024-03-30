package com.beep.beep.domain.user.service;


import com.beep.beep.domain.auth.presentation.dto.request.WithdrawalRequest;
import com.beep.beep.domain.student.facade.StudentFacade;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.exception.PasswordWrongException;
import com.beep.beep.domain.user.facade.UserFacade;
import com.beep.beep.domain.user.presentation.dto.request.ChangePwRequest;
import com.beep.beep.domain.user.presentation.dto.response.UserIdResponse;
import com.beep.beep.global.security.jwt.JwtProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserFacade userFacade;
    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;

    public void withdrawal(String token,WithdrawalRequest request) {
        User user = userFacade.findUserByEmail(jwtProvider.getTokenSubject(token));

        if (!encoder.matches(request.getPassword(), user.getPassword()))
            throw PasswordWrongException.EXCEPTION;

        userFacade.delete(user);
    }


}
