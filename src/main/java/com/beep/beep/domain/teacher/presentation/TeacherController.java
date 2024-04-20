package com.beep.beep.domain.teacher.presentation;


import com.beep.beep.domain.teacher.presentation.dto.response.AdminTeacherResponse;
import com.beep.beep.domain.teacher.presentation.dto.response.TeacherInfoResponse;
import com.beep.beep.domain.teacher.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Get Info Server (Teacher)")
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/teachers")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get TeacherList API")
    public List<AdminTeacherResponse> teacherList(){
        return teacherService.teacherList();
    }

    @GetMapping("/teacher")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get TeacherInfo API")
    public TeacherInfoResponse getInfo(
            @RequestHeader("Authorization") String token
    ) {
        return teacherService.getTeacherInfo(token);
    }




}
