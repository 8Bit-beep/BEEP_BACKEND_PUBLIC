package com.beep.beep.domain.student.mapper;


import com.beep.beep.domain.beep.domain.Room;
import com.beep.beep.domain.student.domain.StudentId;
import com.beep.beep.domain.student.presentation.dto.response.AdminStudentResponse;
import com.beep.beep.domain.beep.presentation.dto.response.GetAttendanceResponse;
import com.beep.beep.domain.beep.presentation.dto.response.GetRoomResponse;
import com.beep.beep.domain.student.presentation.dto.response.GetClsResponse;
import com.beep.beep.domain.student.presentation.dto.response.GetStudentResponse;
import com.beep.beep.domain.student.presentation.dto.response.SearchStudentResponse;
import com.beep.beep.domain.student.presentation.dto.response.StudentInfoResponse;
import com.beep.beep.domain.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    public static AdminStudentResponse toAdminStudentDto(User user, StudentId studentId) {
        return AdminStudentResponse.builder()
                .idx(user.getIdx())
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .grade(studentId.getGrade())
                .cls(studentId.getCls())
                .num(studentId.getNum()).build();
    }

    public static GetStudentResponse toGetStudentDto(StudentId studentId, User user, Room room) {
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

    public static SearchStudentResponse toSearchStudentDto(User user, StudentId studentId, Room room) {
        return SearchStudentResponse.builder()
                .name(user.getName())
                .grade(studentId.getGrade())
                .cls(studentId.getCls())
                .num(studentId.getNum())
                .room(room.getName()).build();
    }

    public static StudentInfoResponse toStudentInfoDto(User user, StudentId studentId, Room room) {
        return StudentInfoResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .grade(studentId.getGrade())
                .cls(studentId.getCls())
                .num(studentId.getNum())
                .room(room.getName()).build();
    }
}
