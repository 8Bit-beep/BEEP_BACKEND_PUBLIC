package com.beep.beep.domain.beep.facade;

import com.beep.beep.domain.beep.domain.Attendance;
import com.beep.beep.domain.beep.domain.Room;
import com.beep.beep.domain.beep.domain.repository.AttendanceRepository;
import com.beep.beep.domain.beep.domain.repository.RoomRepository;
import com.beep.beep.domain.beep.exception.RoomNotExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BeepFacade {

    private final AttendanceRepository attendanceRepository;
    private final RoomRepository roomRepository;

    public void existsRoomByCode(String code) {
        if(!roomRepository.existsById(code))
            throw RoomNotExistsException.EXCEPTION;
    }

    public Attendance findAttendanceByIdx(Long userIdx) {
        return attendanceRepository.findByUserIdx(userIdx);
    }

    public Room findByCode(String code){
        return roomRepository.findByCode(code);
    }

    public List<Room> findRoomsByName(String name) {
        return roomRepository.findAllByName(name);
    }

    public List<Attendance> findAttendancesByCode(String code) {
        return attendanceRepository.findAllByCode(code);
    }

    public Room findRoomByUserIdx(Long userIdx) {
        Attendance attendance = findAttendanceByUserIdx(userIdx);
        return attendance == null ? null : roomRepository.findByCode(attendance.getCode());
    }

    public Attendance findAttendanceByUserIdx(Long userIdx){
        return attendanceRepository.findByUserIdx(userIdx);
    }


    public void save(Attendance attendance){
        attendanceRepository.save(attendance);
    }


}
