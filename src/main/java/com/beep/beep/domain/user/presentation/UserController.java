package com.beep.beep.domain.user.presentation;


import com.beep.beep.domain.user.presentation.dto.request.ChangePwRequest;
import com.beep.beep.domain.user.presentation.dto.response.UserIdResponse;
import com.beep.beep.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
@Tag(name = "UserInfo Server (User)")
public class UserController {

    private final UserService userService;

    @GetMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get UserId API")
    public UserIdResponse findId(
            @RequestParam String email
    ){
        return userService.findId(email);
    }

    @GetMapping("/check")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Check Id-Email API")
    public void checkIdEmail(
            @RequestParam String id,
            @RequestParam String email
    ) {
        userService.checkIdEmail(id,email);
    }

    @PutMapping("/pw")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Changing Password API")
    public void changePw(
            @RequestBody ChangePwRequest request
    ) {
        userService.changePw(request);
    }


}
