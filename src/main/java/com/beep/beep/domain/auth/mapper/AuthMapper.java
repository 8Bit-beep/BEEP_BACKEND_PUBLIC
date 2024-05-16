package com.beep.beep.domain.auth.mapper;

import com.beep.beep.domain.auth.presentation.dto.request.AdminSignUpRequest;
import com.beep.beep.domain.auth.presentation.dto.request.StudentSignUpRequest;
import com.beep.beep.domain.auth.presentation.dto.request.TeacherSignUpRequest;
import com.beep.beep.domain.teacher.domain.JobEntity;
import com.beep.beep.domain.user.domain.UserEntity;
import org.springframework.stereotype.Component;

import static com.beep.beep.domain.user.domain.enums.UserType.ADMIN;
import static com.beep.beep.domain.user.domain.enums.UserType.STUDENT;
import static com.beep.beep.domain.user.domain.enums.UserType.TEACHER;

@Component
public class AuthMapper {
    public UserEntity toAdmin(String encryptedPassword, AdminSignUpRequest request){
        return UserEntity.builder()
                .id(request.getId())
                .password(encryptedPassword)
                .email(request.getEmail())
                .name(request.getName())
                .authority(ADMIN).build();
    }

    public UserEntity toStudent(String encryptedPassword, StudentSignUpRequest request){
        return UserEntity.builder()
                .id(request.getId())
                .password(encryptedPassword)
                .email(request.getEmail())
                .name(request.getName())
                .authority(STUDENT).build();
    }

    public UserEntity toTeacher(String encryptedPassword, TeacherSignUpRequest request){
        return UserEntity.builder()
                .id(request.getId())
                .password(encryptedPassword)
                .email(request.getEmail())
                .name(request.getName())
                .authority(TEACHER).build();
    }

}
