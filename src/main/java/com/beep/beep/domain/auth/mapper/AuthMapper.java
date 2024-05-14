package com.beep.beep.domain.auth.mapper;

import com.beep.beep.domain.auth.presentation.dto.request.AdminSignUpRequest;
import com.beep.beep.domain.auth.presentation.dto.request.StudentSignUpRequest;
import com.beep.beep.domain.auth.presentation.dto.request.TeacherSignUpRequest;
import com.beep.beep.domain.beep.domain.AttendanceEntity;
import com.beep.beep.domain.student.domain.StudentIdEntity;
import com.beep.beep.domain.teacher.domain.JobEntity;
import com.beep.beep.domain.user.domain.UserEntity;
import com.beep.beep.domain.user.presentation.dto.User;
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

    public StudentIdEntity toStudentId(User user, StudentSignUpRequest request) {
        return StudentIdEntity.builder()
                .userIdx(user.getIdx())
                .grade(request.getGrade())
                .cls(request.getCls())
                .num(request.getNum()).build();
    }

    public AttendanceEntity toAttendance(User user) {
        return AttendanceEntity.builder()
                .userIdx(user.getIdx())
                .code("404").build();
    }

    public JobEntity toJob(User user, TeacherSignUpRequest request) {
        return JobEntity.builder()
                .userIdx(user.getIdx())
                .department(request.getDepartment())
                .job(request.getJob()).build();
    }

}
