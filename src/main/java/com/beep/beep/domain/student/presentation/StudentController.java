package com.beep.beep.domain.student.presentation;

import com.beep.beep.domain.student.presentation.dto.request.AttendReq;
import com.beep.beep.domain.student.presentation.dto.request.MemberListReq;
import com.beep.beep.domain.student.presentation.dto.response.AttendListRes;
import com.beep.beep.domain.student.presentation.dto.response.AttendRes;
import com.beep.beep.domain.student.presentation.dto.response.MemberListRes;
import com.beep.beep.domain.student.presentation.dto.response.StudentCodeRes;
import com.beep.beep.domain.student.presentation.dto.response.StudentInfoRes;
import com.beep.beep.domain.student.usecase.StudentUseCase;
import com.beep.beep.global.common.dto.response.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "학생", description = "학생 조회 API")
@RequestMapping("/student")
public class StudentController {

    private final StudentUseCase studentUseCase;

    @GetMapping("/info")
    @Operation(summary = "학생프로필 조회", description = "학생프로필 조회입니다 (student)")
    public ResponseData<StudentInfoRes> studentInfo() {
        return ResponseData.ok("출석 성공",
                studentUseCase.studentInfo()
        );
    }

    @GetMapping("/code")
    @Operation(summary = "학생 실 코드 조회", description = "학생 실 코드를 조회합니다 (student)")
    public ResponseData<StudentCodeRes> studentCode() {
        return ResponseData.ok("학생 실 코드 조회 완료",
                studentUseCase.studentCode()
        );
    }

    @PatchMapping("/attend")
    @Operation(summary = "출석 요청", description = "입실/퇴실을 요청합니다.(student)")
    public ResponseData<AttendRes> attend(
            @RequestBody AttendReq req
    ) {
        return ResponseData.ok("출석 성공",
                studentUseCase.attend(req)
        );
    }

    @GetMapping("/attend-list")
    @Operation(summary = "출석부 조회", description = "실 코드로 입실한 학생목록 조회합니다. (teacher)")
    public ResponseData<List<AttendListRes>> attendList(
            @RequestParam String code
    ){
        return ResponseData.ok("출석부 조회 성공",
                studentUseCase.attendList(code)
        );
    }

    @GetMapping("/member-list")
    @Operation(summary = "반 구성원 조회", description = "학년-반으로 반 구성원 목록을 조회합니다. (teacher)")
    public ResponseData<List<MemberListRes>> memberList(
            @ModelAttribute MemberListReq req
    ){
        return ResponseData.ok("반 구성원 조회 성공",
                studentUseCase.memberList(req)
        );
    }

}
