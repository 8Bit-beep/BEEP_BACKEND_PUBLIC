package com.beep.beep.domain.student.service;

import com.beep.beep.domain.beep.domain.repository.AttendanceRepository;
import com.beep.beep.domain.student.domain.repository.StudentIdRepository;
import com.beep.beep.domain.student.mapper.StudentMapper;
import com.beep.beep.domain.student.presentation.dto.request.StudentByGradeClsReq;
import com.beep.beep.domain.student.presentation.dto.request.SaveStudentIdReq;
import com.beep.beep.domain.student.presentation.dto.response.StudentRes;
import com.beep.beep.domain.student.presentation.dto.response.ClsByGradeRes;
import com.beep.beep.domain.student.presentation.dto.response.StudentByGradeClsRes;
import com.beep.beep.domain.student.presentation.dto.response.StudentByNameRes;
import com.beep.beep.domain.student.presentation.dto.response.StudentByUserRes;
import com.beep.beep.domain.user.domain.repository.UserRepo;
import com.beep.beep.global.common.dto.request.PageRequest;
import com.beep.beep.global.common.service.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentMapper studentMapper;
    private final UserUtil userUtil;
    private final StudentIdRepository studentIdRepository;
    private final AttendanceRepository attendanceRepository;
    private final UserRepo userRepository;

    public void saveStudentId(SaveStudentIdReq request){
        studentIdRepository.save(studentMapper.toStudentId(userUtil.findUserByEmail(request.getEmail()),request));
    }

    public List<StudentRes> studentList(PageRequest request){
        return userRepository.studentList(request);
    }

    public StudentByUserRes studentByUser() {
        return attendanceRepository.studentByUser(userUtil.getCurrentUser());
    }

    public List<StudentByGradeClsRes> studentListByGradeCls(StudentByGradeClsReq request){
        return attendanceRepository.studentListByGradeCls(request);
    }

    public List<StudentByNameRes> studentListByName(String name){
        return userRepository.studentListByName(name);
    }

    public List<ClsByGradeRes> clsListByGrade(int grade){
        return studentIdRepository.clsListByGrade(grade);
    }

}
