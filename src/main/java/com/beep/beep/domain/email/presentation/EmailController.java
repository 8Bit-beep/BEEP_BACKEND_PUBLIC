package com.beep.beep.domain.email.presentation;


import com.beep.beep.domain.email.presentation.dto.request.EmailSendingRequest;
import com.beep.beep.domain.email.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(value = "/email")
@RequiredArgsConstructor
@Tag(name = "이메일", description = "이메일 인증 API")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "이메일 전송", description = "이메일을 전송합니다. (unauthenticated)")
    public void sendEmail(
            @RequestBody EmailSendingRequest request
    ) throws MessagingException, NoSuchAlgorithmException {
        emailService.sendEmail(request);
    }

//    @GetMapping("/verify")
//    @Operation(summary = "이메일 인증", description = "이메일에 전송한 코드와 param으로 받은 코드를 대조하여 인증합니다. (unauthenticated)")
//    public void verifyEmail(
//            @RequestParam String email,
//            @RequestParam String code
//    ) {
//        emailService.verifyEmail(email,code);
//    }

    @GetMapping("/check")
    @Operation(summary = "이메일 중복 확인", description = "이 이메일이 이미 유저계정 중에 사용 중인것인지 확인합니다. (unauthenticated)")
    public void checkEmail(
            @RequestParam String email
    ){
        emailService.checkEmail(email);
    }



}
