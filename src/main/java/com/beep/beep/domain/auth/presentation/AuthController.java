package com.beep.beep.domain.auth.presentation;

import com.beep.beep.domain.auth.presentation.dto.request.SignInReq;
import com.beep.beep.domain.auth.presentation.dto.request.SignUpReq;
import com.beep.beep.domain.auth.presentation.dto.request.TokenRefreshReq;
import com.beep.beep.domain.auth.presentation.dto.response.TokenRefreshRes;
import com.beep.beep.domain.auth.presentation.dto.response.TokenRes;
import com.beep.beep.domain.auth.service.AuthService;
import com.beep.beep.global.common.dto.response.Response;
import com.beep.beep.global.common.dto.response.ResponseData;
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

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "회원가입", description = "회원가입합니다. (unauthenticated)")
    public Response signUp(
            @RequestBody SignUpReq req
    ) {
        return authService.signUp(req);
    }

    @PostMapping("/sign-in")
    @Operation(summary = "로그인", description = "모든 계정이 이 요청을 통해 로그인 합니다. (unauthenticated)")
    public ResponseData<TokenRes> signIn(
            @Validated @RequestBody SignInReq req
    ) {
        return authService.signIn(req);
    }

    @GetMapping("/refresh")
    @Operation(summary = "토큰 재발급", description = "access 토큰 재발급 (student,teacher)")
    public ResponseData<TokenRefreshRes> refresh(
            @Validated @RequestBody TokenRefreshReq req
    ) {
        return authService.refresh(req);
    }

}
