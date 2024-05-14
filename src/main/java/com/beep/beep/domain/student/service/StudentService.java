package com.beep.beep.domain.student.service;

import com.beep.beep.domain.beep.domain.repository.RoomRepository;
import com.beep.beep.domain.beep.presentation.dto.Room;
import com.beep.beep.domain.student.domain.repository.StudentIdRepository;
import com.beep.beep.domain.student.mapper.StudentMapper;
import com.beep.beep.domain.student.presentation.dto.StudentId;
import com.beep.beep.domain.student.presentation.dto.request.GetStudentRequest;
import com.beep.beep.domain.student.presentation.dto.response.AdminStudentResponse;
import com.beep.beep.domain.beep.domain.RoomEntity;
import com.beep.beep.domain.beep.facade.BeepFacade;
import com.beep.beep.domain.student.domain.StudentIdEntity;
import com.beep.beep.domain.student.presentation.dto.response.GetClsResponse;
import com.beep.beep.domain.student.presentation.dto.response.GetStudentResponse;
import com.beep.beep.domain.student.presentation.dto.response.SearchStudentResponse;
import com.beep.beep.domain.student.presentation.dto.response.StudentInfoResponse;
import com.beep.beep.domain.user.domain.UserEntity;
import com.beep.beep.domain.user.domain.enums.UserType;
import com.beep.beep.domain.user.domain.repository.UserRepository;
import com.beep.beep.domain.user.facade.UserFacade;
import com.beep.beep.domain.user.presentation.dto.User;
import com.beep.beep.global.common.repository.UserSecurity;
import com.beep.beep.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final UserFacade userFacade;
    private final BeepFacade beepFacade;
    private final JwtProvider jwtProvider;
    private final RoomRepository roomRepository;
    private final StudentIdRepository studentIdRepository;
    private final UserRepository userRepository;
    private final UserSecurity userSecurity;

    public List<AdminStudentResponse> studentList(){
        List<User> studentList = userRepository.findAllByAuthority(UserType.STUDENT);

        return studentList.stream()
                .map(student ->
                        StudentMapper.toAdminStudentDto(student,studentIdRepository.findByUserIdx(student.getIdx())))
                .toList();
    }

    public StudentInfoResponse getStudentInfo() {
        User user = userFacade.findUserByEmail(userSecurity.getUser().getEmail());
        Long userIdx = user.getIdx();

        StudentId studentId = studentIdRepository.findByUserIdx(userIdx);
        Room room = roomRepository.findByCode(beepFacade.findAttendanceByIdx(userIdx).getCode());

        return StudentMapper.toStudentInfoDto(user, studentId, room);
    }

    public List<GetStudentResponse> getStudents(GetStudentRequest request){
        List<StudentId> studentIdEntityList = studentIdRepository.findByGradeAndCls(request.getGrade(),request.getCls());

        return studentIdEntityList.stream()
                .map(studentId -> {
                    Long userIdx = studentId.getUserIdx();
                    return StudentMapper.toGetStudentDto(studentId, userRepository.findByIdx(userIdx), beepFacade.findRoomByUserIdx(userIdx));
                })
                .toList();
    }

    public List<SearchStudentResponse> searchStudents(String name){
        List<User> userEntityList = userRepository.findByName(name, UserType.STUDENT);
        // 2. studentId 찾기 , attendance -> roomName 찾기
        return userEntityList.stream()
                .map(user -> {
                    Long userIdx = user.getIdx();
                    return StudentMapper.toSearchStudentDto(user, studentIdRepository.findByUserIdx(userIdx), beepFacade.findRoomByUserIdx(userIdx));
                })
                .toList();
    }

    public List<GetClsResponse> getCls(int grade){
        List<Integer> clsList = studentIdRepository.findClsByGrade(grade);

        return clsList.stream()
                .map( cls -> {
                    int headCount = studentIdRepository.countByCls(grade,cls);
                    return StudentMapper.toGetClsDto(cls,headCount);
                }).toList();
    }

}
