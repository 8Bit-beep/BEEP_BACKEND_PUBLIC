package com.beep.beep.domain.beep.service;


import com.beep.beep.domain.beep.domain.Attendance;
import com.beep.beep.domain.beep.domain.Room;
import com.beep.beep.domain.beep.exception.NonExitException;
import com.beep.beep.domain.beep.exception.NotCurrentRoomException;
import com.beep.beep.domain.beep.facade.BeepFacade;
import com.beep.beep.domain.student.facade.StudentFacade;
import com.beep.beep.domain.student.presentation.dto.request.EnterRoomRequest;
import com.beep.beep.domain.student.presentation.dto.request.ExitRoomRequest;
import com.beep.beep.domain.student.presentation.dto.response.GetAttendanceResponse;
import com.beep.beep.domain.student.presentation.dto.response.GetRoomResponse;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.facade.UserFacade;
import com.beep.beep.global.security.jwt.JwtProvider;
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
    private final StudentFacade studentFacade;
    private final JwtProvider jwtProvider;

    @Transactional
    public void enter(String token, EnterRoomRequest request){
        String code = request.getCode();
        beepFacade.existsRoomByCode(code);

        User user = userFacade.findUserByEmail(jwtProvider.getTokenSubject(jwtProvider.parseToken(token)));

        Attendance attendance = beepFacade.findAttendanceByIdx(user.getIdx());
        if (!Objects.equals(attendance.getCode(), "404"))
            throw NonExitException.EXCEPTION;

        attendance.updateAttendance(code);
    }

    @Transactional
    public void exit(String token, ExitRoomRequest request){
        String code = request.getCode();
        beepFacade.existsRoomByCode(code);

        User user = userFacade.findUserByEmail(jwtProvider.getTokenSubject(jwtProvider.parseToken(token)));

        Attendance attendance = beepFacade.findAttendanceByIdx(user.getIdx());
        if (!Objects.equals(code, attendance.getCode()))
            throw NotCurrentRoomException.EXCEPTION;

        attendance.updateAttendance("404");
    }

    public List<GetRoomResponse> getRooms(String name){
        List<Room> roomList = beepFacade.findRoomsByName(name);

        return roomList.stream()
                .map(GetRoomResponse::of)
                .toList();
    }

    public List<GetAttendanceResponse> getAttendance(String code){
        List<User> userList = beepFacade.findAttendancesByCode(code).stream()
                .map(attendance -> userFacade.findUserByIdx(attendance.getUserIdx()))
                .toList();

        return userList.stream()
                .map(user -> GetAttendanceResponse.of(user,studentFacade.findByUserIdx(user.getIdx())))
                .toList();
    }


}
