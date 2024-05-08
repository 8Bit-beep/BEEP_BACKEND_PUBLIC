package com.beep.beep.domain.user.service;


import com.beep.beep.domain.user.domain.repository.UserRepository;
import com.beep.beep.domain.user.exception.UserNotFoundException;
import com.beep.beep.domain.user.presentation.dto.request.WithdrawalRequest;
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
    private final UserRepository userRepository;

    public void withdrawal(String token,WithdrawalRequest request) {
        User user = userFacade.findUserByEmail(jwtProvider.getTokenSubject(jwtProvider.parseToken(token)));

        if (!encoder.matches(request.getPassword(), user.getPassword()))
            throw PasswordWrongException.EXCEPTION;

        userRepository.delete(user);
    }

    public void idCheck(String id) {
        userFacade.existsById(id);
    }

    public UserIdResponse findId(String email) {
        String id = userRepository.findByEmail(email)
                .get().getId();

        return UserIdResponse.builder()
                .id(id).build();
    }

    public void checkIdEmail(String id,String email) {
        userRepository.findByIdEmail(id,email)
                .orElseThrow(() ->
                        UserNotFoundException.EXCEPTION);
    }

    @Transactional
    public void changePw(ChangePwRequest request){
        User user = userFacade.findUserById(request.getId());

        user.updateUser(encoder.encode(request.getPassword()));
    }

}
