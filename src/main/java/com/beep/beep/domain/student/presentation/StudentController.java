package com.beep.beep.domain.student.presentation;


import com.beep.beep.domain.student.presentation.dto.response.AdminStudentResponse;
import com.beep.beep.domain.student.presentation.dto.response.GetStudentResponse;
import com.beep.beep.domain.student.presentation.dto.response.SearchStudentResponse;
import com.beep.beep.domain.student.presentation.dto.response.StudentInfoResponse;
import com.beep.beep.domain.student.service.StudentService;
import com.beep.beep.domain.teacher.presentation.dto.response.GetClsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "학생", description = "학생 조회 API")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/students")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "학생계정 조회", description = "학생계정 전부 조회합니다. (admin)")
    public List<AdminStudentResponse> studentList(){
        return studentService.studentList();
    }

    @GetMapping("student")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "학생프로필 조회", description = "학생프로필 조회입니다 (student)")
    public StudentInfoResponse getInfo(
            @RequestHeader(name = "Authorization") String token
    ) {
        return studentService.getStudentInfo(token);
    }

    @GetMapping("/students/{grade}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "반 조회", description = "학년 param로 반을 조회합니다.(teacher)")
    public List<GetClsResponse> getCls(
            @PathVariable int grade
    ){
        return studentService.getCls(grade);
    }

    @GetMapping("/students/{grade}/{cls}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "반 구성원 조회", description = "학년-반으로 반 구성원 목록을 조회합니다. (teacher)")
    public List<GetStudentResponse> getStudents(
            @PathVariable int grade,
            @PathVariable int cls
    ){
        return studentService.getStudents(grade,cls);
    }

    @GetMapping("/students/name")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "학생 조회", description = "학생이름으로 학생을 조회합니다.(teacher)")
    public List<SearchStudentResponse> searchStudents(
            @RequestParam String name
    ){
        return studentService.searchStudents(name);
    }
}
