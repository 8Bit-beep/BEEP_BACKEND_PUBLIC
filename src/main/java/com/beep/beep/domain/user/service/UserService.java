package com.beep.beep.domain.user.service;

import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.repo.UserJpaRepo;
import com.beep.beep.domain.user.exception.UserAlreadyExistsException;
import com.beep.beep.domain.user.exception.UserNotFoundException;
import com.beep.beep.global.common.repository.UserSessionHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserJpaRepo userJpaRepo;
    private final UserSessionHolder userSessionHolder;

    public User findByEmail(String email) {
        return userJpaRepo.findById(email)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public void deleteUser(String email) {
        userJpaRepo.deleteById(email);
    }

    public String getCurrentEmail(){
        return userSessionHolder.getUser().email();
    }

    public void existsByEmail(String email) {
        if(userJpaRepo.existsById(email))
            throw UserAlreadyExistsException.EXCEPTION;
    }

    public void save(User user) {
        userJpaRepo.save(user);
    }

    public void notFoundEmail(String email){
        if(!userJpaRepo.existsById(email))
            throw UserNotFoundException.EXCEPTION;
    }

}
