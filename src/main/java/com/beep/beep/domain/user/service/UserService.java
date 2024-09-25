package com.beep.beep.domain.user.service;

import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.enums.RoomCode;
import com.beep.beep.domain.user.domain.repo.UserJpaRepo;
import com.beep.beep.domain.user.exception.UserAlreadyExistsException;
import com.beep.beep.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserJpaRepo userJpaRepo;

    public User findByEmail(String email) {
        return userJpaRepo.findById(email)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public void deleteUser(String email) {
        userJpaRepo.deleteById(email);
    }

    public void existsByEmail(String email) {
        if(userJpaRepo.existsById(email))
            throw UserAlreadyExistsException.EXCEPTION;
    }

    public void save(User user) {
        userJpaRepo.save(user);
    }

    public User updateCurrentRoom(User user) {
        user.setLastUpdated(LocalDateTime.now());
        return userJpaRepo.save(user);
    }

    public List<User> getRoomAttendList(RoomCode code) {
        return userJpaRepo.findAllByCurrentRoom(code);
    }

    public List<User> getClassMemberList(Integer grade, Integer cls) {
        return userJpaRepo.findAllByGradeAndCls(grade,cls);
    }
}
