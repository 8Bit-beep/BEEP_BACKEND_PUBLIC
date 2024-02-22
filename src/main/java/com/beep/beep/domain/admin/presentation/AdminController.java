package com.beep.beep.domain.admin.presentation;


import com.beep.beep.domain.admin.presentation.dto.response.AdminStudentResponse;
import com.beep.beep.domain.admin.presentation.dto.response.AdminTeacherResponse;
import com.beep.beep.domain.admin.service.AdminService;
import com.beep.beep.domain.user.domain.enums.UserType;
import com.beep.beep.global.annotation.AuthCheck;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/admin")
@RequiredArgsConstructor
@Tag(name = "Get Info Server (Admin)")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/students")
    @ResponseStatus(HttpStatus.OK)
    @AuthCheck(role = UserType.ROLE_ADMIN)
    @Operation(summary = "Get StudentList API")
    public List<AdminStudentResponse> studentList(){
        return adminService.studentList();
    }

    @GetMapping("/teachers")
    @ResponseStatus(HttpStatus.OK)
    @AuthCheck(role = UserType.ROLE_ADMIN)
    @Operation(summary = "Get TeacherList API")
    public List<AdminTeacherResponse> teacherList(){
        return adminService.teacherList();
    }

}
