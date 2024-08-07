package com.beep.beep.domain.user.presentation.dto;

import com.beep.beep.domain.student.domain.Student;
import com.beep.beep.domain.teacher.domain.Teacher;
import com.beep.beep.domain.user.domain.enums.UserType;
import lombok.Builder;

import static com.beep.beep.domain.user.domain.enums.UserType.STUDENT;
import static com.beep.beep.domain.user.domain.enums.UserType.TEACHER;


@Builder
public record UserVO(Long id, String email, String password, String name, UserType authority) {
    public static UserVO fromStudent(Student student) {
        return UserVO.builder()
                .id(student.getId())
                .email(student.getEmail())
                .password(student.getPassword())
                .name(student.getName())
                .authority(STUDENT)
                .build();
    }
    public static UserVO fromTeacher(Teacher teacher) {
        return UserVO.builder()
                .id(teacher.getId())
                .email(teacher.getEmail())
                .password(teacher.getPassword())
                .name(teacher.getName())
                .authority(TEACHER)
                .build();
    }
}
