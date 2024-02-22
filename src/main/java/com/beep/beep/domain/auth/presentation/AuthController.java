package com.beep.beep.domain.auth.presentation;


import com.beep.beep.domain.auth.presentation.dto.request.SignInRequest;
import com.beep.beep.domain.auth.presentation.dto.request.StudentSignUpRequest;
import com.beep.beep.domain.auth.presentation.dto.request.WithdrawalRequest;
import com.beep.beep.domain.auth.presentation.dto.response.SignInResponse;
import com.beep.beep.domain.auth.presentation.dto.response.TokenRefreshResponse;
import com.beep.beep.domain.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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

    @GetMapping("/id-check")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Student Id-Check API")
    public void studentIdCheck(
            @RequestParam String id
    ) {
        authService.idCheck(id);
    }

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Student Sign-UP API")
    public void studentSignUp(
            @RequestBody StudentSignUpRequest request
    ){
        authService.studentSignUp(request);
    }

    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Total Sign-In API")
    public SignInResponse signIn(
            @RequestBody SignInRequest request
    ) {
        return authService.signIn(request);
    }

    @GetMapping("/refresh")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Total Token-Refresh API")
    public TokenRefreshResponse accessRefreshToken(
            @RequestHeader(name = "Authorization") String accessToken
    ) {
        return authService.tokenRefresh(accessToken);
    }

    @DeleteMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Total Logout API")
    public void logout(
            @RequestHeader(name = "Authorization") String accessToken
    ){
        authService.logout(accessToken);
    }

    @DeleteMapping("/withdrawal")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Total Withdrawal API")
    public void withdrawal(
            @RequestHeader(name = "Authorization") String accessToken,
            @RequestBody WithdrawalRequest request
    ){
        authService.withdrawal(accessToken,request);
    }




}
