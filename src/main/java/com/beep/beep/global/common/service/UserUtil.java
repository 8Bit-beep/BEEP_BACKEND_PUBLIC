package com.beep.beep.global.common.service;

import com.beep.beep.domain.email.exception.EmailAlreadyExistsException;
import com.beep.beep.domain.email.exception.EmailNotFoundException;
import com.beep.beep.domain.user.domain.UserEntity;
import com.beep.beep.domain.user.domain.repository.UserRepository;
import com.beep.beep.domain.user.exception.UserAlreadyExistsException;
import com.beep.beep.domain.user.exception.UserNotFoundException;
import com.beep.beep.domain.user.mapper.UserMapper;
import com.beep.beep.domain.user.presentation.dto.User;
import com.beep.beep.global.common.repository.UserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserUtil {

    private final UserSecurity userSecurity;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User getCurrentUser() {
        System.out.println("여기임?");
        return userSecurity.getUser();
    }

    public User findUserByEmail(String email){
        return userRepository.findByEmail(email).map(userMapper::toUserDto)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public void existsById(String id){
        if(userRepository.existsById(id))
            throw UserAlreadyExistsException.EXCEPTION;
    }

    public UserEntity findUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION );
    }

    public void checkEmail(String email){
        if(userRepository.existsByEmail(email))
            throw EmailAlreadyExistsException.EXCEPTION;
    }

}
