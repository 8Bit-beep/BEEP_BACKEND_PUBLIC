package com.beep.beep.domain.student.service;

import com.beep.beep.domain.beep.domain.repository.AttendanceRepository;
import com.beep.beep.domain.beep.domain.repository.RoomRepository;
import com.beep.beep.domain.beep.mapper.BeepMapper;
import com.beep.beep.domain.beep.presentation.dto.Attendance;
import com.beep.beep.domain.student.domain.repository.StudentIdRepository;
import com.beep.beep.domain.student.mapper.StudentMapper;
import com.beep.beep.domain.student.presentation.dto.request.GetStudentRequest;
import com.beep.beep.domain.student.presentation.dto.request.StudentIdRequest;
import com.beep.beep.domain.student.presentation.dto.response.AdminStudentResponse;
import com.beep.beep.domain.beep.domain.RoomEntity;
import com.beep.beep.domain.student.domain.StudentIdEntity;
import com.beep.beep.domain.student.presentation.dto.response.GetClsResponse;
import com.beep.beep.domain.student.presentation.dto.response.GetStudentResponse;
import com.beep.beep.domain.student.presentation.dto.response.SearchStudentResponse;
import com.beep.beep.domain.student.presentation.dto.response.StudentInfoResponse;
import com.beep.beep.domain.user.domain.UserEntity;
import com.beep.beep.domain.user.domain.repository.UserRepository;
import com.beep.beep.domain.user.presentation.dto.User;
import com.beep.beep.global.common.service.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.beep.beep.domain.user.domain.enums.UserType.STUDENT;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentMapper studentMapper;
    private final BeepMapper beepMapper;
    private final UserUtil userUtil;
    private final RoomRepository roomRepository;
    private final StudentIdRepository studentIdRepository;
    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;

    public void saveStudentId(StudentIdRequest request){
        studentIdRepository.save(studentMapper.toStudentId(userUtil.findUserByEmail(request.getEmail()),request));
    }

    public List<AdminStudentResponse> studentList(){
        List<UserEntity> studentList = userRepository.findAllByAuthority(STUDENT);
        System.out.println(studentList.get(0));
        return studentList.stream()
                .map(student ->
                        StudentMapper.toAdminStudentDto(student,studentIdRepository.findByUserIdx(student.getIdx())))
                .toList();
    }

    public StudentInfoResponse getStudentInfo() {
        User user = userUtil.getCurrentUser();
        return StudentMapper.toStudentInfoDto(user, studentIdRepository.findByUserIdx(userIdx), findRoomByUserIdx(userIdx));
    }

    public List<GetStudentResponse> getStudents(GetStudentRequest request){
            List<StudentIdEntity> studentIdEntityList = studentIdRepository.findByGradeAndCls(request.getGrade(), request.getCls());

            return studentIdEntityList.stream()
                    .map(studentId -> {
                        Long userIdx = studentId.getUserIdx();
                        return StudentMapper.toGetStudentDto(studentId, userRepository.findByIdx(userIdx), findRoomByUserIdx(userIdx));
                    })
                    .toList();
    }

    public List<SearchStudentResponse> searchStudents(String name){
        List<UserEntity> userEntityList = userRepository.findByName(name, STUDENT);
        // 2. studentId 찾기 , attendance -> roomName 찾기
        return userEntityList.stream()
                .map(user -> {
                    Long userIdx = user.getIdx();
                    return StudentMapper.toSearchStudentDto(user, studentIdRepository.findByUserIdx(userIdx), findRoomByUserIdx(userIdx));
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

    private RoomEntity findRoomByUserIdx(Long userIdx) {
        Attendance attendance = beepMapper.toAttendanceDto(attendanceRepository.findByUserIdx(userIdx));
        return roomRepository.findByCode(attendance.getCode());
    }

}
