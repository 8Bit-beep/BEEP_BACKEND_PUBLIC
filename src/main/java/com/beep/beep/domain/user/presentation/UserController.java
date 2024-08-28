package com.beep.beep.domain.user.presentation;

import com.beep.beep.domain.user.presentation.dto.request.ChangePwReq;
import com.beep.beep.domain.user.usecase.UserUseCase;
import com.beep.beep.global.common.dto.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "유저", description = "유저 API")
@RequestMapping("/user")
public class UserController {

    private final UserUseCase userUseCase;

    @DeleteMapping("")
    @Operation(summary = "회원탈퇴", description = "회원탈퇴 요청합니다. (student,teacher)")
    public Response withdrawal(){
        userUseCase.withdrawal();
        return Response.ok("회원탈퇴 성공");
    }

    @PatchMapping("/change-pw")
    @Operation(summary = "비밀번호 변경", description = "이메일과 변경할 비번 값을 받아 비번을 변경해줍니다.(unauthenticated)")
    public Response changePw(
            @RequestBody ChangePwReq request
    ) {
        userUseCase.changePw(request);
        return Response.ok("비밀번호 변경 성공");
    }

}
