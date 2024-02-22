package com.beep.beep.domain.teacher.presentation;


import com.beep.beep.domain.teacher.presentation.dto.response.TeacherInfoResponse;
import com.beep.beep.domain.teacher.service.TeacherService;
import com.beep.beep.domain.user.domain.enums.UserType;
import com.beep.beep.global.annotation.AuthCheck;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/teacher")
@RequiredArgsConstructor
@Tag(name = "Get Info Server (Teacher)")
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/info")
    @ResponseStatus(HttpStatus.OK)
    @AuthCheck(role = UserType.ROLE_TEACHER)
    @Operation(summary = "Get TeacherInfo API")
    public TeacherInfoResponse getInfo(
            @RequestHeader String token
    ) {
        return teacherService.getTeacherInfo(token);
    }


}
