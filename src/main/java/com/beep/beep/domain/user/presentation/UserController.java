package com.beep.beep.domain.user.presentation;


import com.beep.beep.domain.auth.presentation.dto.request.WithdrawalRequest;
import com.beep.beep.domain.user.presentation.dto.request.ChangePwRequest;
import com.beep.beep.domain.user.presentation.dto.response.UserIdResponse;
import com.beep.beep.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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

    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Total Withdrawal API")
    public void withdrawal(
            @RequestHeader("Authorization") String token,
            @RequestBody WithdrawalRequest request
    ){
        userService.withdrawal(token,request);
    }


}
