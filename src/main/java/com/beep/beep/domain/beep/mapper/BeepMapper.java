package com.beep.beep.domain.beep.mapper;


import com.beep.beep.domain.beep.domain.AttendanceEntity;
import com.beep.beep.domain.beep.domain.RoomEntity;
import com.beep.beep.domain.beep.presentation.dto.Attendance;
import com.beep.beep.domain.beep.presentation.dto.response.GetAttendanceResponse;
import com.beep.beep.domain.beep.presentation.dto.response.GetRoomResponse;
import com.beep.beep.domain.student.domain.StudentIdEntity;
import com.beep.beep.domain.user.domain.UserEntity;
import com.beep.beep.domain.user.presentation.dto.User;
import org.springframework.stereotype.Component;

@Component
public class BeepMapper {

    public AttendanceEntity toEdit(Attendance attendance){
        return AttendanceEntity.builder()
                .userIdx(attendance.getUserIdx())
                .code(attendance.getCode()).build();
    }

    public static GetAttendanceResponse toGetAttendanceDto(UserEntity user, StudentIdEntity studentId) {
        return GetAttendanceResponse.builder()
                .name(user.getName())
                .grade(studentId.getGrade())
                .cls(studentId.getCls())
                .num(studentId.getNum()).build();
    }

    public static GetRoomResponse toGetRoomDto(RoomEntity room) {
        return GetRoomResponse.builder()
                .room(room.getName()).build();
    }

    public AttendanceEntity toAttendance(User user) {
        return AttendanceEntity.builder()
                .userIdx(user.getIdx())
                .code("404").build();
    }

}
