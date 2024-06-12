package com.beep.beep.domain.beep.service;

import com.beep.beep.domain.beep.domain.repository.AttendanceRepository;
import com.beep.beep.domain.beep.domain.repository.RoomRepository;
import com.beep.beep.domain.beep.exception.NotExitedException;
import com.beep.beep.domain.beep.exception.NotCurrentRoomException;
import com.beep.beep.domain.beep.exception.RoomNotExistsException;
import com.beep.beep.domain.beep.mapper.BeepMapper;
import com.beep.beep.domain.beep.presentation.dto.AttendanceVO;
import com.beep.beep.domain.beep.presentation.dto.RoomVO;
import com.beep.beep.domain.beep.presentation.dto.request.InitializeAttendanceReq;
import com.beep.beep.domain.beep.presentation.dto.request.EnterRoomReq;
import com.beep.beep.domain.beep.presentation.dto.request.ExitRoomReq;
import com.beep.beep.domain.beep.presentation.dto.response.AttendanceByCodeRes;
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

    public void initializeAttendance(InitializeAttendanceReq req){
        attendanceRepository.save(beepMapper.toAttendance(userUtil.findUserByEmail(req.getEmail())));
    }

    @Transactional
    public void enter(EnterRoomReq req){
        String code = req.getCode();
        existsByCode(code); // room 존재 여부 확인

        AttendanceVO attendance =  findUserAttendance(); // 유저 출석정보 조회
        if (!Objects.equals(attendance.getCode(), "404"))
            throw NotExitedException.EXCEPTION;

        attendance.setCode(code); // room코드 변경
        attendanceRepository.save(beepMapper.toEdit(attendance));
    }

    @Transactional
    public void exit(ExitRoomReq req){
        String code = req.getCode();
        existsByCode(code);
        AttendanceVO attendance = findUserAttendance();
        if (!Objects.equals(code, attendance.getCode()))
            throw NotCurrentRoomException.EXCEPTION;

        attendance.setCode("404"); // room코드 변경
        attendanceRepository.save(beepMapper.toEdit(attendance));
    }

    public List<RoomVO> roomListByName(String name){
        return roomRepository.roomListByName(name);
    }

    public List<RoomVO> roomListByFloor(Integer floor){
        return roomRepository.roomListByFloor(floor);
    }

    public List<AttendanceByCodeRes> attendanceByCode(String code){
        return attendanceRepository.attendanceListByCode(code);
    }

    private AttendanceVO findUserAttendance(){
        return beepMapper.toAttendanceDto(attendanceRepository.findById(userUtil.getCurrentUser().getIdx()).get());
    }

    private void existsByCode(String code){
        if(!roomRepository.existsById(code))
            throw RoomNotExistsException.EXCEPTION; // room 존재 여부 확인
    }

}
