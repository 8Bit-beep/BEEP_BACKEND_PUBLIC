package com.beep.beep.domain.beep.mapper;


import com.beep.beep.domain.beep.domain.RoomEntity;
import com.beep.beep.domain.beep.presentation.dto.response.GetAttendanceResponse;
import com.beep.beep.domain.beep.presentation.dto.response.GetRoomResponse;
import com.beep.beep.domain.student.domain.StudentIdEntity;
import com.beep.beep.domain.user.domain.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class BeepMapper {

    public static GetAttendanceResponse toGetAttendanceDto(UserEntity userEntity, StudentIdEntity studentIdEntity) {
        return GetAttendanceResponse.builder()
                .name(userEntity.getName())
                .grade(studentIdEntity.getGrade())
                .cls(studentIdEntity.getCls())
                .num(studentIdEntity.getNum()).build();
    }

    public static GetRoomResponse toGetRoomDto(RoomEntity roomEntity) {
        return GetRoomResponse.builder()
                .room(roomEntity.getName()).build();
    }

}
