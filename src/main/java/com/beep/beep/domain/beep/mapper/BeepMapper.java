package com.beep.beep.domain.beep.mapper;


import com.beep.beep.domain.beep.domain.AttendanceEntity;
import com.beep.beep.domain.beep.presentation.dto.Attendance;
import com.beep.beep.domain.beep.presentation.dto.Room;
import com.beep.beep.domain.beep.presentation.dto.response.GetAttendanceResponse;
import com.beep.beep.domain.beep.presentation.dto.response.GetRoomResponse;
import com.beep.beep.domain.student.presentation.dto.StudentId;
import com.beep.beep.domain.user.presentation.dto.User;
import org.springframework.stereotype.Component;

@Component
public class BeepMapper {

    public AttendanceEntity toEdit(Attendance attendance){
        return AttendanceEntity.builder()
                .userIdx(attendance.getUserIdx())
                .code(attendance.getCode()).build();
    }

    public static GetAttendanceResponse toGetAttendanceDto(User user, StudentId studentId) {
        return GetAttendanceResponse.builder()
                .name(user.getName())
                .grade(studentId.getGrade())
                .cls(studentId.getCls())
                .num(studentId.getNum()).build();
    }

    public static GetRoomResponse toGetRoomDto(Room room) {
        return GetRoomResponse.builder()
                .room(room.getName()).build();
    }

}
