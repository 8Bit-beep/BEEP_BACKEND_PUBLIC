package com.beep.beep.domain.beep.facade;

import com.beep.beep.domain.beep.domain.AttendanceEntity;
import com.beep.beep.domain.beep.domain.RoomEntity;
import com.beep.beep.domain.beep.domain.repository.AttendanceRepository;
import com.beep.beep.domain.beep.domain.repository.RoomRepository;
import com.beep.beep.domain.beep.exception.RoomNotExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BeepFacade {

    private final AttendanceRepository attendanceRepository;
    private final RoomRepository roomRepository;

    public void existsRoomByCode(String code) {
        if(!roomRepository.existsById(code))
            throw RoomNotExistsException.EXCEPTION;
    }

    public AttendanceEntity findAttendanceByIdx(Long userIdx) {
        return attendanceRepository.findByUserIdx(userIdx);
    }

    public RoomEntity findRoomByUserIdx(Long userIdx) {
        AttendanceEntity attendance = findAttendanceByIdx(userIdx);
        return attendance == null ? null : roomRepository.findByCode(attendance.getCode());
    }

}
