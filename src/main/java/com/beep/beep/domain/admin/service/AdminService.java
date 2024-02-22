package com.beep.beep.domain.admin.service;


import com.beep.beep.domain.admin.presentation.dto.response.AdminStudentResponse;
import com.beep.beep.domain.admin.presentation.dto.response.AdminTeacherResponse;
import com.beep.beep.domain.student.facade.StudentFacade;
import com.beep.beep.domain.teacher.domain.facade.TeacherFacade;
import com.beep.beep.domain.user.domain.User;
import com.beep.beep.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserFacade userFacade;
    private final StudentFacade studentFacade;
    private final TeacherFacade teacherFacade;

    public List<AdminStudentResponse> studentList(){
        List<User> studentList = userFacade.findAllStudents();

        return studentList.stream()
                .map(student ->
                        AdminStudentResponse.of(student,studentFacade.findByUserIdx(student.getIdx())))
                .toList();
    }

    public List<AdminTeacherResponse> teacherList() {
        List<User> teacherList = userFacade.findAllTeachers();

        return teacherList.stream()
                .map(teacher ->
                        AdminTeacherResponse.of(teacher,teacherFacade.findByUserIdx(teacher.getIdx())))
                .toList();
    }
}
