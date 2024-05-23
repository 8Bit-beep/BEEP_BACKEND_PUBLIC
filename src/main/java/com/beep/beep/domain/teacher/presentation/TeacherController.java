package com.beep.beep.domain.teacher.presentation;


import com.beep.beep.domain.teacher.presentation.dto.request.SaveJobReq;
import com.beep.beep.domain.teacher.presentation.dto.response.TeacherRes;
import com.beep.beep.domain.teacher.presentation.dto.response.TeacherByUserRes;
import com.beep.beep.domain.teacher.service.TeacherService;
import com.beep.beep.global.common.dto.request.PageRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "선생님", description = "선생님 조회 API")
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping("/job")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "직책 저장", description = "선생님 직책을 저장합니다. (teacher)")
    public void saveJob(
            @RequestBody SaveJobReq req
    ){
        teacherService.saveJob(req);
    }

    @GetMapping("")
    @Operation(summary = "선생님계정 조회", description = "선생님계정 전부 조회합니다. (admin)")
    public List<TeacherRes> teacherList(
            @ModelAttribute PageRequest request
    ){
        return teacherService.teacherList(request);
    }

    @GetMapping("/info")
    @Operation(summary = "선생님프로필 조회", description = "선생님프로필 조회합니다. (teacher)")
    public TeacherByUserRes teacherByUser() {
        return teacherService.teacherByUser();
    }




}
