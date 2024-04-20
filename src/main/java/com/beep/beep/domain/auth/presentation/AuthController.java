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
@Tag(name = " Total Server (Auth)")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Student Id-Check API")
    public void studentIdCheck(
            @PathVariable String id
    ) {
        authService.idCheck(id);
    }

    @PostMapping("/student/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Student Sign-UP API")
    public void studentSignUp(
            @RequestBody StudentSignUpRequest request
    ){
        authService.studentSignUp(request);
    }

    @PostMapping("/teacher/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Teacher Sign-UP API")
    public void studentSignUp(
            @RequestBody TeacherSignUpRequest request
    ){
        authService.teacherSignUp(request);
    }

    @PostMapping("/admin/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Admin Sign-UP API")
    public void adminSignUp(
            @RequestBody AdminSignUpRequest request
    ){
        authService.adminSignUp(request);
    }

    @PostMapping("/sign-in")
    @Operation(summary = "Total Sign-In API")
    public SignInResponse signIn(
            @Validated @RequestBody SignInRequest request
    ) {
        return authService.signIn(request);
    }

    @GetMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Total Token-Refresh API")
    public TokenRefreshResponse refresh(
            @Validated @RequestBody TokenRefreshRequest request
    ) {
        return authService.refresh(request);
    }

    @GetMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get UserId API")
    public UserIdResponse findId(
            @PathVariable String email
    ){
        return authService.findId(email);
    }

    @GetMapping("/email/id")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Check Id-Email API")
    public void checkIdEmail(
            @RequestParam String email,
            @RequestParam String id
    ) {
        authService.checkIdEmail(id,email);
    }

    @PutMapping("/pw")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Changing Password API")
    public void changePw(
            @RequestBody ChangePwRequest request
    ) {
        authService.changePw(request);
    }


}
