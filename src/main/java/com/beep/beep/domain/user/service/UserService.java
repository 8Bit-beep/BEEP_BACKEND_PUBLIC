package com.beep.beep.domain.user.service;


import com.beep.beep.domain.user.domain.repository.UserRepository;
import com.beep.beep.domain.user.exception.UserNotFoundException;
import com.beep.beep.domain.user.mapper.UserMapper;
import com.beep.beep.domain.user.presentation.dto.User;
import com.beep.beep.domain.user.presentation.dto.request.WithdrawalRequest;
import com.beep.beep.domain.user.exception.PasswordWrongException;
import com.beep.beep.domain.user.presentation.dto.request.ChangePwRequest;
import com.beep.beep.domain.user.presentation.dto.response.UserIdResponse;
import com.beep.beep.global.common.service.UserUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final UserUtil userUtil;
    private final UserMapper userMapper;

    public void withdrawal() {
        User user = userUtil.getCurrentUser();

        userRepository.deleteById(user.getIdx());
    }

    public void idCheck(String id) {
        userUtil.existsById(id);
    }

    public UserIdResponse findId(String email) {
        return UserIdResponse.builder()
                .id(userUtil.findUserByEmail(email).getId()).build();
    }

    public void checkIdEmail(String id,String email) {
        userRepository.findByIdEmail(id,email)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    @Transactional
    public void changePw(ChangePwRequest request){
        User user = userUtil.findUserByEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        userRepository.save(userMapper.toEdit(user));
    }

}
