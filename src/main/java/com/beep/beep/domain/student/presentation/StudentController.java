package com.beep.beep.domain.student.presentation;

import com.beep.beep.domain.room.domain.Club;
import com.beep.beep.domain.student.presentation.dto.request.AttendReq;
import com.beep.beep.domain.student.presentation.dto.request.StudentSignUpReq;
import com.beep.beep.domain.student.presentation.dto.response.AttendListRes;
import com.beep.beep.domain.student.presentation.dto.response.AttendRes;
import com.beep.beep.domain.student.presentation.dto.response.MemberListRes;
import com.beep.beep.domain.student.presentation.dto.response.StudentCodeRes;
import com.beep.beep.domain.student.presentation.dto.response.StudentInfoRes;
import com.beep.beep.domain.student.presentation.dto.response.StudyListRes;
import com.beep.beep.domain.student.usecase.StudentUseCase;
import com.beep.beep.global.common.dto.response.Response;
import com.beep.beep.global.common.dto.response.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@Tag(name = "학생", description = "학생 조회 API")
@RequestMapping("/student")
public class StudentController {

    private final StudentUseCase studentUseCase;

    @PostMapping("/info")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "학생 기본정보 저장", description = "회원가입 시 학생 기본 정보를 저장합니다. (unauthenticated)")
    public Response studentSignUp(
            @RequestBody StudentSignUpReq req
    ){
        return studentUseCase.signUp(req);
    }

    @GetMapping("/info")
    @Operation(summary = "학생프로필 조회", description = "학생프로필 조회입니다 (student)")
    public ResponseData<StudentInfoRes> studentInfo() {
        return studentUseCase.studentInfo();
    }

    @GetMapping("/code")
    @Operation(summary = "학생 실 코드 조회", description = "학생 실 코드를 조회합니다 (student)")
    public ResponseData<StudentCodeRes> studentCode() {
        return studentUseCase.studentCode();
    }

    @Async
    @PatchMapping("/attend")
    @Operation(summary = "출석 요청", description = "입실/퇴실을 요청합니다.(student)")
    public CompletableFuture<ResponseData<AttendRes>> attend(
            @RequestBody AttendReq req
    ) {
        return studentUseCase.attend(req);
    }

    @GetMapping("/attend-list")
    @Operation(summary = "출석부 조회", description = "실 코드로 입실한 학생목록 조회합니다. (teacher)")
    public ResponseData<List<AttendListRes>> attendList(
            @RequestParam String code
    ){
        return studentUseCase.attendList(code);
    }

    @GetMapping("/member-list")
    @Operation(summary = "반 구성원 조회", description = "학년-반으로 반 구성원 목록을 조회합니다. (teacher)")
    public ResponseData<List<MemberListRes>> memberList(
            @RequestParam Integer grade,
            @RequestParam Integer cls
    ){
        return studentUseCase.memberList(grade,cls);
    }

    @GetMapping("/study-list")
    @Operation(summary = "스터디 구성원 조회", description = "스터디 출석정보 구성원 조회합니다.(teacher)")
    public ResponseData<List<StudyListRes>> studyList(
            @RequestParam Club club
    ){
        return studentUseCase.studyList(club);
    }

}
