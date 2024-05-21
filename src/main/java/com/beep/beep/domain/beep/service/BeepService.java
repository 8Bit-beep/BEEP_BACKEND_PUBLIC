package com.beep.beep.domain.beep.service;

import com.beep.beep.domain.beep.domain.RoomEntity;
import com.beep.beep.domain.beep.domain.repository.AttendanceRepository;
import com.beep.beep.domain.beep.domain.repository.RoomRepository;
import com.beep.beep.domain.beep.domain.repository.querydsl.RoomRepositoryCustom;
import com.beep.beep.domain.beep.exception.NonExitException;
import com.beep.beep.domain.beep.exception.NotCurrentRoomException;
import com.beep.beep.domain.beep.exception.RoomNotExistsException;
import com.beep.beep.domain.beep.mapper.BeepMapper;
import com.beep.beep.domain.beep.presentation.dto.Attendance;
import com.beep.beep.domain.student.domain.repository.StudentIdRepository;
import com.beep.beep.domain.beep.presentation.dto.request.EnterRoomRequest;
import com.beep.beep.domain.beep.presentation.dto.request.ExitRoomRequest;
import com.beep.beep.domain.beep.presentation.dto.response.GetAttendanceResponse;
import com.beep.beep.domain.beep.presentation.dto.response.GetRoomResponse;
import com.beep.beep.domain.user.domain.UserEntity;
import com.beep.beep.domain.user.domain.repository.UserRepository;
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
    private final StudentIdRepository studentIdRepository;
    private final UserRepository userRepository;
    private final RoomRepositoryCustom roomRepositoryCustom;

    public void saveAttendance(){
        attendanceRepository.save(beepMapper.toAttendance(userUtil.getCurrentUser()));
    }

    @Transactional
    public void enter(EnterRoomRequest request){
        String code = request.getCode();
        if(!roomRepository.existsById(code))
            throw RoomNotExistsException.EXCEPTION; // room 존재 여부 확인

        Attendance attendance =  getAttendance(); // 유저 출석정보 조회
        if (!Objects.equals(attendance.getCode(), "404"))
            throw NonExitException.EXCEPTION;

        attendance.setCode(code); // room코드 변경
        attendanceRepository.save(beepMapper.toEdit(attendance));
    }

    @Transactional
    public void exit(ExitRoomRequest request){
        String code = request.getCode();
        if(!roomRepository.existsById(code))
            throw RoomNotExistsException.EXCEPTION; // room 존재 여부 확인

        Attendance attendance = getAttendance();
        if (!Objects.equals(code, attendance.getCode()))
            throw NotCurrentRoomException.EXCEPTION;

        attendance.setCode("404"); // room코드 변경
        attendanceRepository.save(beepMapper.toEdit(attendance));
    }

    public List<GetRoomResponse> getRooms(String name){
        List<RoomEntity> roomEntityList = roomRepositoryCustom.findAllByName(name);

        return roomEntityList.stream()
                .map(BeepMapper::toGetRoomDto)
                .toList();
    }

    public List<GetAttendanceResponse> getAttendance(String code){
        List<UserEntity> userEntityList = attendanceRepository.findAllByCode(code).stream()
                .map(attendanceEntity -> userRepository.findByIdx(attendanceEntity.getUserIdx()))
                .toList();

        return userEntityList.stream()
                .map(user -> BeepMapper.toGetAttendanceDto(user,studentIdRepository.findByUserIdx(user.getIdx())))
                .toList();
    }

    private Attendance getAttendance(){
        return beepMapper.toAttendanceDto(attendanceRepository.findByUserIdx(userUtil.getCurrentUser().getIdx()));
    }

}
