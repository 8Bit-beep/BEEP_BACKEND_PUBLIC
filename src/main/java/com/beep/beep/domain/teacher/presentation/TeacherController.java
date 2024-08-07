package com.beep.beep.domain.teacher.presentation;

import com.beep.beep.domain.teacher.presentation.dto.response.TeacherInfoRes;
import com.beep.beep.domain.teacher.usecase.TeacherUseCase;
import com.beep.beep.global.common.dto.response.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "선생님", description = "선생님 조회 API")
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherUseCase teacherUseCase;

    @GetMapping("/info")
    @Operation(summary = "선생님프로필 조회", description = "선생님프로필 조회합니다. (teacher)")
    public ResponseData<TeacherInfoRes> teacherInfo() {
        return ResponseData.ok("선생님프로필 조회 성공",
                teacherUseCase.teacherInfo()
        );
    }

}
