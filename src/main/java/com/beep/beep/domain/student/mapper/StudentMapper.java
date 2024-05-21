package com.beep.beep.domain.student.mapper;

import com.beep.beep.domain.beep.domain.RoomEntity;
import com.beep.beep.domain.student.domain.StudentIdEntity;
import com.beep.beep.domain.student.presentation.dto.request.StudentIdRequest;
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
    public static AdminStudentResponse toAdminStudentDto(UserEntity user, StudentIdEntity studentId) {
        return AdminStudentResponse.builder()
                .idx(user.getIdx())
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .grade(studentId.getGrade())
                .cls(studentId.getCls())
                .num(studentId.getNum()).build();
    }

    public static GetStudentResponse toGetStudentDto(StudentIdEntity studentId, UserEntity user, RoomEntity room) {
        return GetStudentResponse.builder()
                .name(user.getName())
                .grade(studentId.getGrade())
                .cls(studentId.getCls())
                .num(studentId.getNum())
                .room(room.getName())
                .floor(room.getFloor()).build();
    }

    public static GetClsResponse toGetClsDto(int cls, int headCount){
        return GetClsResponse.builder()
                .cls(cls)
                .headCount(headCount).build();
    }

    public static SearchStudentResponse toSearchStudentDto(UserEntity user, StudentIdEntity studentId, RoomEntity room) {
        return SearchStudentResponse.builder()
                .name(user.getName())
                .grade(studentId.getGrade())
                .cls(studentId.getCls())
                .num(studentId.getNum())
                .room(room.getName()).build();
    }

    public static StudentInfoResponse toStudentInfoDto(User user, StudentIdEntity studentId, RoomEntity room) {
        return StudentInfoResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .grade(studentId.getGrade())
                .cls(studentId.getCls())
                .num(studentId.getNum())
                .room(room.getName()).build();
    }

    public StudentIdEntity toStudentId(User user, StudentIdRequest request) {
        return StudentIdEntity.builder()
                .userIdx(user.getIdx())
                .grade(request.getGrade())
                .cls(request.getCls())
                .num(request.getNum()).build();
    }
}
