package com.beep.beep.domain.beep.mapper;


import com.beep.beep.domain.beep.domain.Attendance;
import com.beep.beep.domain.beep.presentation.dto.AttendanceVO;
import com.beep.beep.domain.user.presentation.dto.UserVO;
import org.springframework.stereotype.Component;

@Component
public class BeepMapper {

    public Attendance toEdit(AttendanceVO vo){
        return Attendance.builder()
                .userIdx(vo.getUserIdx())
                .code(vo.getCode()).build();
    }

    public Attendance toAttendance(UserVO userVO) {
        return Attendance.builder()
                .userIdx(userVO.getIdx())
                .code("404").build();
    }

    public AttendanceVO toAttendanceDto(Attendance attendance) {
        return AttendanceVO.builder()
                .code(attendance.getCode())
                .userIdx(attendance.getUserIdx()).build();
    }

}
