package com.beep.beep.domain.user.service;


import com.beep.beep.domain.user.domain.repository.UserRepository;
import com.beep.beep.domain.user.exception.UserNotFoundException;
import com.beep.beep.domain.user.presentation.dto.User;
import com.beep.beep.domain.user.presentation.dto.request.WithdrawalRequest;
import com.beep.beep.domain.user.domain.UserEntity;
import com.beep.beep.domain.user.exception.PasswordWrongException;
import com.beep.beep.domain.user.facade.UserFacade;
import com.beep.beep.domain.user.presentation.dto.request.ChangePwRequest;
import com.beep.beep.domain.user.presentation.dto.response.UserIdResponse;
import com.beep.beep.global.common.repository.UserSecurity;
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
    private final UserSecurity userSecurity;
    private final UserRepository userRepository;

    public void withdrawal(WithdrawalRequest request) {
        User user = userFacade.findUserByEmail(userSecurity.getUser().getEmail());

        if (!encoder.matches(request.getPassword(), user.getPassword()))
            throw PasswordWrongException.EXCEPTION;

        userRepository.deleteById(user.getIdx());
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
        UserEntity userEntity = userFacade.findUserById(request.getId());

        userEntity.updateUser(encoder.encode(request.getPassword()));
    }

}
