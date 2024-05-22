package com.beep.beep.domain.beep.service;

import com.beep.beep.domain.beep.domain.repository.AttendanceRepository;
import com.beep.beep.domain.beep.domain.repository.RoomRepository;
import com.beep.beep.domain.beep.exception.NonExitException;
import com.beep.beep.domain.beep.exception.NotCurrentRoomException;
import com.beep.beep.domain.beep.exception.RoomNotExistsException;
import com.beep.beep.domain.beep.mapper.BeepMapper;
import com.beep.beep.domain.beep.presentation.dto.Attendance;
import com.beep.beep.domain.beep.presentation.dto.Room;
import com.beep.beep.domain.beep.presentation.dto.request.SaveAttendanceRequest;
import com.beep.beep.domain.beep.presentation.dto.request.EnterRoomRequest;
import com.beep.beep.domain.beep.presentation.dto.request.ExitRoomRequest;
import com.beep.beep.domain.beep.presentation.dto.response.GetAttendanceResponse;
import com.beep.beep.global.common.service.UserUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BeepService {

    private final BeepMapper beepMapper;
    private final UserUtil userUtil;
    private final RoomRepository roomRepository;
    private final AttendanceRepository attendanceRepository;

    public void saveAttendance(SaveAttendanceRequest request){
        attendanceRepository.save(beepMapper.toAttendance(userUtil.findUserByEmail(request.getEmail())));
    }

    @Transactional
    public void enter(EnterRoomRequest request){
        String code = request.getCode();
        existsByCode(code); // room 존재 여부 확인

        Attendance attendance =  findUserAttendance(); // 유저 출석정보 조회
        if (!Objects.equals(attendance.getCode(), "404"))
            throw NonExitException.EXCEPTION;

        attendance.setCode(code); // room코드 변경
        attendanceRepository.save(beepMapper.toEdit(attendance));
    }

    @Transactional
    public void exit(ExitRoomRequest request){
        String code = request.getCode();
        existsByCode(code);
        Attendance attendance = findUserAttendance();
        if (!Objects.equals(code, attendance.getCode()))
            throw NotCurrentRoomException.EXCEPTION;

        attendance.setCode("404"); // room코드 변경
        attendanceRepository.save(beepMapper.toEdit(attendance));
    }

    public List<Room> getRooms(String name){
        return roomRepository.roomListByName(name);
    }

    public List<GetAttendanceResponse> getAttendance(String code){
        return attendanceRepository.attendanceListByCode(code);
    }

    private Attendance findUserAttendance(){
        return beepMapper.toAttendanceDto(attendanceRepository.findByUserIdx(userUtil.getCurrentUser().getIdx()));
    }

    private void existsByCode(String code){
        if(!roomRepository.existsById(code))
            throw RoomNotExistsException.EXCEPTION; // room 존재 여부 확인
    }

}
