package com.beep.beep.domain.auth.presentation;


import com.beep.beep.domain.auth.presentation.dto.request.AdminSignUpRequest;
import com.beep.beep.domain.auth.presentation.dto.request.TeacherSignUpRequest;
import com.beep.beep.domain.auth.presentation.dto.request.SignInRequest;
import com.beep.beep.domain.auth.presentation.dto.request.StudentSignUpRequest;
import com.beep.beep.domain.auth.presentation.dto.request.TokenRefreshRequest;
import com.beep.beep.domain.auth.presentation.dto.response.SignInResponse;
import com.beep.beep.domain.auth.presentation.dto.response.TokenRefreshResponse;
import com.beep.beep.domain.auth.service.AuthService;
import com.beep.beep.domain.user.presentation.dto.request.ChangePwRequest;
import com.beep.beep.domain.user.presentation.dto.response.UserIdResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
@Tag(name = "AUTH", description = "auth API")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "아이디 확인", description = "아이디 존재여부 확인 (unauthenticated)")
    public void studentIdCheck(
            @PathVariable String id
    ) {
        authService.idCheck(id);
    }

    @PostMapping("/student/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "학생 회원가입", description = "학생계정으로 회원가입합니다. (unauthenticated)")
    public void studentSignUp(
            @RequestBody StudentSignUpRequest request
    ){
        authService.studentSignUp(request);
    }

    @PostMapping("/teacher/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "선생님 회원가입", description = "선생님계정으로 회원가입합니다. (unauthenticated)")
    public void studentSignUp(
            @RequestBody TeacherSignUpRequest request
    ){
        authService.teacherSignUp(request);
    }

    @PostMapping("/admin/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "관리자 회원가입", description = "관리자계정으로 회원가입합니다. (unauthenticated)")
    public void adminSignUp(
            @RequestBody AdminSignUpRequest request
    ){
        authService.adminSignUp(request);
    }

    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "로그인", description = "모든 계정이 이 요청을 통해 로그인 합니다. (unauthenticated)")
    public SignInResponse signIn(
            @Validated @RequestBody SignInRequest request
    ) {
        return authService.signIn(request);
    }

    @GetMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "토큰 재발급", description = "access 토큰 재발급 (student,teacher,admin)")
    public TokenRefreshResponse refresh(
            @Validated @RequestBody TokenRefreshRequest request
    ) {
        return authService.refresh(request);
    }

    @GetMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "아이디 찾기", description = "검증된 이메일로 아이디를 찾습니다. (unauthenticated)")
    public UserIdResponse findId(
            @PathVariable String email
    ){
        return authService.findId(email);
    }

    @GetMapping("/email/id")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "이메일-유저아이디에 일치하는 유저존재여부 조회", description = "이메일-유저아이디에 일치하는 유저가 존재하는지를 조회합니다.(unauthenticated)")
    public void checkIdEmail(
            @RequestParam String email,
            @RequestParam String id
    ) {
        authService.checkIdEmail(id,email);
    }

    @PutMapping("/pw")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "비밀번호 찾기", description = "아이디와 변경할 비번 값을 받아 비번을 변경해줍니다.(unauthenticated)")
    public void changePw(
            @RequestBody ChangePwRequest request
    ) {
        authService.changePw(request);
    }


}
