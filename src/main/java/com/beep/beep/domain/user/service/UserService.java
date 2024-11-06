package com.beep.beep.domain.user.service;

import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.enums.RoomCode;
import com.beep.beep.domain.user.domain.repo.UserJpaRepo;
import com.beep.beep.domain.user.exception.UserAlreadyExistsException;
import com.beep.beep.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
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
        user.setLastUpdated(LocalDateTime.now(ZoneId.of("Asia/Seoul")));
        return userJpaRepo.save(user);
    }

    public List<User> getRoomAttendList(RoomCode code) {
        return userJpaRepo.findAllByCurrentRoom(code);
    }

    public List<User> getRoomStudyList(RoomCode code) {
        return userJpaRepo.findAllByFixedRoom(code);
    }

    public List<User> getClassMemberList(Integer grade, Integer cls) {
        return userJpaRepo.findAllByGradeAndCls(grade,cls);
    }

    public List<User> getStudyListByFloor(List<RoomCode> rooms) {
        return userJpaRepo.findAllByFixedRooms(rooms);
    }

    public List<User> getStudentByName(String keyword) {
        return userJpaRepo.findAllByName(keyword);
    }

    public User findByStudentIdAndName(Integer grade, Integer cls, Integer num, String name) {
        return userJpaRepo.findByGradeAndClsAndNumAndName(grade,cls,num,name)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}
