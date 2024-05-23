package com.beep.beep.domain.auth.presentation;


import com.beep.beep.domain.auth.presentation.dto.request.SignInReq;
import com.beep.beep.domain.auth.presentation.dto.request.SignUpReq;
import com.beep.beep.domain.auth.presentation.dto.request.TokenRefreshReq;
import com.beep.beep.domain.auth.presentation.dto.response.SignInRes;
import com.beep.beep.domain.auth.presentation.dto.response.TokenRefreshRes;
import com.beep.beep.domain.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
@Tag(name = "AUTH", description = "auth API")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up/student")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "학생 회원가입", description = "학생계정으로 회원가입합니다. (unauthenticated)")
    public void studentSignUp(
            @RequestBody SignUpReq req
    ){
        authService.studentSignUp(req);
    }

    @PostMapping("/sign-up/teacher")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "선생님 회원가입", description = "선생님계정으로 회원가입합니다. (unauthenticated)")
    public void teacherSignUp(
            @RequestBody SignUpReq req
    ){
        authService.teacherSignUp(req);
    }

    @PostMapping("/sign-up/admin")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "관리자 회원가입", description = "관리자계정으로 회원가입합니다. (unauthenticated)")
    public void adminSignUp(
            @RequestBody SignUpReq req
    ){
        authService.adminSignUp(req);
    }

    @PostMapping("/sign-in")
    @Operation(summary = "로그인", description = "모든 계정이 이 요청을 통해 로그인 합니다. (unauthenticated)")
    public SignInRes signIn(
            @Validated @RequestBody SignInReq req
    ) {
        return authService.signIn(req);
    }

    @GetMapping("/refresh")
    @Operation(summary = "토큰 재발급", description = "access 토큰 재발급 (student,teacher,admin)")
    public TokenRefreshRes refresh(
            @Validated @RequestBody TokenRefreshReq req
    ) {
        return authService.refresh(req);
    }


}
