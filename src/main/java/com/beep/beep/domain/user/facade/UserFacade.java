package com.beep.beep.domain.user.facade;


import com.beep.beep.domain.email.exception.EmailAlreadyExistsException;
import com.beep.beep.domain.email.exception.EmailNotFoundException;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.enums.UserType;
import com.beep.beep.domain.user.domain.repository.UserRepository;
import com.beep.beep.domain.user.exception.UserAlreadyExistsException;
import com.beep.beep.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;

    public void existsByEmail(String email) {
        if(userRepository.existsByEmail(email))
            throw EmailAlreadyExistsException.EXCEPTION;
    }

    public void emailExists(String email) {
        if(!userRepository.existsByEmail(email))
            throw EmailNotFoundException.EXCEPTION;
    }

    public void existsById(String id){
        if(userRepository.existsById(id))
            throw UserAlreadyExistsException.EXCEPTION;
    }

    public User findUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION );
    }

    public String findIdByEmail(String email){
        return userRepository.findByEmail(email)
                .get().getId();
    }

    public void existsByIdAndEmail(String id,String email) {
        userRepository.findByIdEmail(id,email)
                .orElseThrow(() ->
                        UserNotFoundException.EXCEPTION);
    }

    public List<User> findStudentsByName(String name){
        return userRepository.findByName(name, UserType.ROLE_STUDENT);
    }

    public User findUserByIdx(Long idx){
        return userRepository.findByIdx(idx);
    }

    public List<User> findAllStudents(){
        return userRepository.findAllByAuthority(UserType.ROLE_STUDENT);
    }

    public List<User> findAllTeachers(){
        return userRepository.findAllByAuthority(UserType.ROLE_TEACHER);
    }

    public void save(User user){
        userRepository.save(user);
    }

    public void delete(User user){
        userRepository.delete(user);
    }

}
