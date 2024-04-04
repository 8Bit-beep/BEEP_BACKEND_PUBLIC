package com.beep.beep.domain.teacher.service;

import com.beep.beep.domain.beep.domain.Room;
import com.beep.beep.domain.beep.facade.BeepFacade;
import com.beep.beep.domain.student.presentation.dto.response.GetAttendanceResponse;
import com.beep.beep.domain.student.presentation.dto.response.GetRoomResponse;
import com.beep.beep.domain.student.presentation.dto.response.GetStudentResponse;
import com.beep.beep.domain.student.presentation.dto.response.SearchStudentResponse;
import com.beep.beep.domain.student.domain.StudentId;
import com.beep.beep.domain.student.facade.StudentFacade;
import com.beep.beep.domain.teacher.domain.Job;
import com.beep.beep.domain.teacher.domain.facade.TeacherFacade;
import com.beep.beep.domain.teacher.presentation.dto.response.TeacherInfoResponse;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.facade.UserFacade;
import com.beep.beep.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final JwtProvider jwtProvider;
    private final UserFacade userFacade;
    private final TeacherFacade teacherFacade;
    private final StudentFacade studentFacade;
    private final BeepFacade beepFacade;

    public TeacherInfoResponse getTeacherInfo(String token){
        User user = userFacade.findUserByEmail(jwtProvider.getTokenSubject(jwtProvider.parseToken(token)));
        Job job = teacherFacade.findByUserIdx(user.getIdx());

        return TeacherInfoResponse.of(user,job);
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

    public List<GetStudentResponse> getStudents(int grade, int cls){
        List<StudentId> studentIdList = studentFacade.findByGradeCls(grade,cls);

        return studentIdList.stream()
                .map(studentId -> {
                    Long userIdx = studentId.getUserIdx();
                    return GetStudentResponse.of(studentId, userFacade.findUserByIdx(userIdx), beepFacade.findRoomByUserIdx(userIdx));
                })
                .toList();
    }

    public List<SearchStudentResponse> searchStudents(String name){
        List<User> userList = userFacade.findStudentsByName(name);
        // 2. studentId 찾기 , attendance -> roomName 찾기
        return userList.stream()
                .map(user -> {
                    Long userIdx = user.getIdx();
                    return SearchStudentResponse.of(user, studentFacade.findByUserIdx(userIdx), beepFacade.findRoomByUserIdx(userIdx));
                })
                .toList();
    }


}
