package com.beep.beep.domain.teacher.service;

import com.beep.beep.domain.teacher.domain.Teacher;
import com.beep.beep.domain.teacher.domain.repository.TeacherJpaRepo;
import com.beep.beep.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherJpaRepo teacherJpaRepo;

    public Teacher findByEmail(String email) {
        return teacherJpaRepo.findByEmail(email).orElseThrow(
                () -> UserNotFoundException.EXCEPTION);
    }
}
