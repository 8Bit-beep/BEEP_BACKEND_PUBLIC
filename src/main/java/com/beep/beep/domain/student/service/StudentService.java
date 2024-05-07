package com.beep.beep.domain.student.service;

import com.beep.beep.domain.student.mapper.StudentMapper;
import com.beep.beep.domain.student.presentation.dto.response.AdminStudentResponse;
import com.beep.beep.domain.beep.domain.Room;
import com.beep.beep.domain.beep.facade.BeepFacade;
import com.beep.beep.domain.student.domain.StudentId;
import com.beep.beep.domain.student.facade.StudentFacade;
import com.beep.beep.domain.beep.presentation.dto.response.GetRoomResponse;
import com.beep.beep.domain.student.presentation.dto.response.GetClsResponse;
import com.beep.beep.domain.student.presentation.dto.response.GetStudentResponse;
import com.beep.beep.domain.student.presentation.dto.response.SearchStudentResponse;
import com.beep.beep.domain.student.presentation.dto.response.StudentInfoResponse;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.facade.UserFacade;
import com.beep.beep.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final UserFacade userFacade;
    private final StudentFacade studentFacade;
    private final BeepFacade beepFacade;
    private final JwtProvider jwtProvider;

    public List<AdminStudentResponse> studentList(){
        List<User> studentList = userFacade.findAllStudents();

        return studentList.stream()
                .map(student ->
                        StudentMapper.toAdminStudentDto(student,studentFacade.findByUserIdx(student.getIdx())))
                .toList();
    }

    public StudentInfoResponse getStudentInfo(String token) {
        User user = userFacade.findUserByEmail(jwtProvider.getTokenSubject(jwtProvider.parseToken(token)));
        System.out.println("유저 찾음.");
        Long userIdx = user.getIdx();

        StudentId studentId = studentFacade.findByUserIdx(userIdx);
        Room room = beepFacade.findByCode(beepFacade.findAttendanceByIdx(userIdx).getCode());

        return StudentMapper.toStudentInfoDto(user,studentId,room);
    }

    public List<GetStudentResponse> getStudents(int grade, int cls){
        List<StudentId> studentIdList = studentFacade.findByGradeCls(grade,cls);

        return studentIdList.stream()
                .map(studentId -> {
                    Long userIdx = studentId.getUserIdx();
                    return StudentMapper.toGetStudentDto(studentId, userFacade.findUserByIdx(userIdx), beepFacade.findRoomByUserIdx(userIdx));
                })
                .toList();
    }

    public List<SearchStudentResponse> searchStudents(String name){
        List<User> userList = userFacade.findStudentsByName(name);
        // 2. studentId 찾기 , attendance -> roomName 찾기
        return userList.stream()
                .map(user -> {
                    Long userIdx = user.getIdx();
                    return StudentMapper.toSearchStudentDto(user, studentFacade.findByUserIdx(userIdx), beepFacade.findRoomByUserIdx(userIdx));
                })
                .toList();
    }

    public List<GetClsResponse> getCls(int grade){
        List<Integer> clsList = studentFacade.findAllClsByGrade(grade);

        return clsList.stream()
                .map( cls -> {
                    int headCount = studentFacade.countStudentsByCls(grade,cls);
                    return StudentMapper.toGetClsDto(cls,headCount);
                }).toList();
    }

}
