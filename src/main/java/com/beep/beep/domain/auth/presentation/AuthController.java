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

    @PostMapping("/sign-up/student")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "학생 회원가입", description = "학생계정으로 회원가입합니다. (unauthenticated)")
    public void studentSignUp(
            @RequestBody StudentSignUpRequest request
    ){
        authService.studentSignUp(request);
    }

    @PostMapping("/sign-up/teacher")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "선생님 회원가입", description = "선생님계정으로 회원가입합니다. (unauthenticated)")
    public void studentSignUp(
            @RequestBody TeacherSignUpRequest request
    ){
        authService.teacherSignUp(request);
    }

    @PostMapping("/sign-up/admin")
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
    @Operation(summary = "토큰 재발급", description = "access 토큰 재발급 (student,teacher,admin)")
    public TokenRefreshResponse refresh(
            @Validated @RequestBody TokenRefreshRequest request
    ) {
        return authService.refresh(request);
    }


}
