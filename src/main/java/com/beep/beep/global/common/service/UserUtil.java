package com.beep.beep.global.common.service;

import com.beep.beep.domain.email.exception.EmailAlreadyExistsException;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.repository.UserRepo;
import com.beep.beep.domain.user.exception.UserAlreadyExistsException;
import com.beep.beep.domain.user.exception.UserNotFoundException;
import com.beep.beep.domain.user.mapper.UserMapper;
import com.beep.beep.domain.user.presentation.dto.UserVO;
import com.beep.beep.global.common.repository.UserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserUtil {

    private final UserSecurity userSecurity;
    private final UserRepo userRepository;
    private final UserMapper userMapper;

    public UserVO getCurrentUser() {
        System.out.println("여기임?");
        return userSecurity.getUser();
    }

    public UserVO findUserByEmail(String email){
        return userRepository.findByEmail(email).map(userMapper::toUserDto)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public void existsById(String id){
        if(userRepository.existsById(id))
            throw UserAlreadyExistsException.EXCEPTION;
    }

    public User findUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION );
    }

    public void checkEmail(String email){
        if(userRepository.existsByEmail(email))
            throw EmailAlreadyExistsException.EXCEPTION;
    }

}
