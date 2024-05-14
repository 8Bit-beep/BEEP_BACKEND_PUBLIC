package com.beep.beep.domain.user.facade;

import com.beep.beep.domain.user.domain.UserEntity;
import com.beep.beep.domain.user.domain.repository.UserRepository;
import com.beep.beep.domain.user.exception.UserAlreadyExistsException;
import com.beep.beep.domain.user.exception.UserNotFoundException;
import com.beep.beep.domain.user.presentation.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;

    public void existsById(String id){
        if(userRepository.existsById(id))
            throw UserAlreadyExistsException.EXCEPTION;
    }

    public User findUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION );
    }

    public User findUserByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        UserNotFoundException.EXCEPTION);
    }

}
