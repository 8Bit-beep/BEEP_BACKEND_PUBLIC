package com.beep.beep.domain.student.mapper;


import com.beep.beep.domain.beep.domain.RoomEntity;
import com.beep.beep.domain.student.domain.StudentIdEntity;
import com.beep.beep.domain.student.presentation.dto.response.AdminStudentResponse;
import com.beep.beep.domain.student.presentation.dto.response.GetClsResponse;
import com.beep.beep.domain.student.presentation.dto.response.GetStudentResponse;
import com.beep.beep.domain.student.presentation.dto.response.SearchStudentResponse;
import com.beep.beep.domain.student.presentation.dto.response.StudentInfoResponse;
import com.beep.beep.domain.user.domain.UserEntity;
import com.beep.beep.domain.user.presentation.dto.User;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    public static AdminStudentResponse toAdminStudentDto(User user, StudentIdEntity studentIdEntity) {
        return AdminStudentResponse.builder()
                .idx(user.getIdx())
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .grade(studentIdEntity.getGrade())
                .cls(studentIdEntity.getCls())
                .num(studentIdEntity.getNum()).build();
    }

    public static GetStudentResponse toGetStudentDto(StudentIdEntity studentIdEntity, UserEntity userEntity, RoomEntity roomEntity) {
        return GetStudentResponse.builder()
                .name(userEntity.getName())
                .grade(studentIdEntity.getGrade())
                .cls(studentIdEntity.getCls())
                .num(studentIdEntity.getNum())
                .room(roomEntity.getName())
                .floor(roomEntity.getFloor()).build();
    }

    public static GetClsResponse toGetClsDto(int cls, int headCount){
        return GetClsResponse.builder()
                .cls(cls)
                .headCount(headCount).build();
    }

    public static SearchStudentResponse toSearchStudentDto(UserEntity userEntity, StudentIdEntity studentIdEntity, RoomEntity roomEntity) {
        return SearchStudentResponse.builder()
                .name(userEntity.getName())
                .grade(studentIdEntity.getGrade())
                .cls(studentIdEntity.getCls())
                .num(studentIdEntity.getNum())
                .room(roomEntity.getName()).build();
    }

    public static StudentInfoResponse toStudentInfoDto(User user, StudentIdEntity studentIdEntity, RoomEntity roomEntity) {
        return StudentInfoResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .grade(studentIdEntity.getGrade())
                .cls(studentIdEntity.getCls())
                .num(studentIdEntity.getNum())
                .room(roomEntity.getName()).build();
    }
}
