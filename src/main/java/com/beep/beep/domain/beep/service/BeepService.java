package com.beep.beep.domain.beep.service;


import com.beep.beep.domain.beep.domain.AttendanceEntity;
import com.beep.beep.domain.beep.domain.RoomEntity;
import com.beep.beep.domain.beep.domain.repository.AttendanceRepository;
import com.beep.beep.domain.beep.domain.repository.RoomRepository;
import com.beep.beep.domain.beep.exception.NonExitException;
import com.beep.beep.domain.beep.exception.NotCurrentRoomException;
import com.beep.beep.domain.beep.facade.BeepFacade;
import com.beep.beep.domain.beep.mapper.BeepMapper;
import com.beep.beep.domain.student.domain.repository.StudentIdRepository;
import com.beep.beep.domain.beep.presentation.dto.request.EnterRoomRequest;
import com.beep.beep.domain.beep.presentation.dto.request.ExitRoomRequest;
import com.beep.beep.domain.beep.presentation.dto.response.GetAttendanceResponse;
import com.beep.beep.domain.beep.presentation.dto.response.GetRoomResponse;
import com.beep.beep.domain.user.domain.UserEntity;
import com.beep.beep.domain.user.domain.repository.UserRepository;
import com.beep.beep.domain.user.facade.UserFacade;
import com.beep.beep.domain.user.presentation.dto.User;
import com.beep.beep.global.common.repository.UserSecurity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BeepService {

    private final BeepFacade beepFacade;
    private final UserFacade userFacade;
    private final RoomRepository roomRepository;
    private final AttendanceRepository attendanceRepository;
    private final StudentIdRepository studentIdRepository;
    private final UserRepository userRepository;
    private final UserSecurity userSecurity;

    @Transactional
    public void enter(EnterRoomRequest request){
        String code = request.getCode();
        beepFacade.existsRoomByCode(code);

        User user = userFacade.findUserByEmail(userSecurity.getUser().getEmail());

        AttendanceEntity attendanceEntity = beepFacade.findAttendanceByIdx(user.getIdx());
        if (!Objects.equals(attendanceEntity.getCode(), "404"))
            throw NonExitException.EXCEPTION;

        attendanceEntity.updateAttendance(code);
    }

    @Transactional
    public void exit(ExitRoomRequest request){
        String code = request.getCode();
        beepFacade.existsRoomByCode(code);

        User user = userFacade.findUserByEmail(userSecurity.getUser().getEmail());

        AttendanceEntity attendanceEntity = beepFacade.findAttendanceByIdx(user.getIdx());
        if (!Objects.equals(code, attendanceEntity.getCode()))
            throw NotCurrentRoomException.EXCEPTION;

        attendanceEntity.updateAttendance("404");
    }

    public List<GetRoomResponse> getRooms(String name){
        List<RoomEntity> roomEntityList = roomRepository.findAllByName(name);

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


}
