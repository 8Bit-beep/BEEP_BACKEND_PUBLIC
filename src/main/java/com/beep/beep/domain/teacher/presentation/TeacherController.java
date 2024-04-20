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
@Tag(name = "선생님", description = "선생님 조회 API")
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/teachers")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "선생님계정 조회", description = "선생님계정 전부 조회합니다. (admin)")
    public List<AdminTeacherResponse> teacherList(){
        return teacherService.teacherList();
    }

    @GetMapping("/teacher")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "선생님프로필 조회", description = "선생님프로필 조회합니다. (teacher)")
    public TeacherInfoResponse getInfo(
            @RequestHeader("Authorization") String token
    ) {
        return teacherService.getTeacherInfo(token);
    }




}
