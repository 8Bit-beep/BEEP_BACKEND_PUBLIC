package com.beep.beep.domain.auth.mapper;

import com.beep.beep.domain.auth.presentation.dto.request.AdminSignUpRequest;
import com.beep.beep.domain.auth.presentation.dto.request.StudentSignUpRequest;
import com.beep.beep.domain.auth.presentation.dto.request.TeacherSignUpRequest;
import com.beep.beep.domain.beep.domain.Attendance;
import com.beep.beep.domain.student.domain.StudentId;
import com.beep.beep.domain.teacher.domain.Job;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.domain.enums.UserType;
import org.springframework.stereotype.Component;

import static com.beep.beep.domain.user.domain.enums.UserType.ADMIN;
import static com.beep.beep.domain.user.domain.enums.UserType.STUDENT;

@Component
public class AuthMapper {
    public User toAdmin(String encryptedPassword, AdminSignUpRequest request){
        return User.builder()
                .id(request.getId())
                .password(encryptedPassword)
                .email(request.getEmail())
                .name(request.getName())
                .authority(ADMIN).build();
    }

    public User toStudent(String encryptedPassword, StudentSignUpRequest request){
        return User.builder()
                .id(request.getId())
                .password(encryptedPassword)
                .email(request.getEmail())
                .name(request.getName())
                .authority(STUDENT).build();
    }

    public User toTeacher(String encryptedPassword, TeacherSignUpRequest request){
        return User.builder()
                .id(request.getId())
                .password(encryptedPassword)
                .email(request.getEmail())
                .name(request.getName())
                .authority(UserType.TEACHER).build();
    }

    public StudentId toStudentId(User user, StudentSignUpRequest request) {
        return StudentId.builder()
                .userIdx(user.getIdx())
                .grade(request.getGrade())
                .cls(request.getCls())
                .num(request.getNum()).build();
    }

    public Attendance toAttendance(User user) {
        return Attendance.builder()
                .userIdx(user.getIdx())
                .code("404").build();
    }

    public Job toJob(User user,TeacherSignUpRequest request) {
        return Job.builder()
                .userIdx(user.getIdx())
                .department(request.getDepartment())
                .job(request.getJob()).build();
    }

}
