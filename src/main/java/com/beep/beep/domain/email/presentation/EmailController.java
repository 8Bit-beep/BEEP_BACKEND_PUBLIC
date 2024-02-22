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
@Tag(name = "Student Server (Email)")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Email Sending API")
    public void sendEmail(
            @RequestBody EmailSendingRequest request
    ) throws MessagingException, NoSuchAlgorithmException {
        emailService.sendEmail(request);
    }

    @GetMapping("/verify")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Email Verify API")
    public void verifyEmail(
            @RequestParam String email,
            @RequestParam String code
    ) {
        emailService.verifyEmail(email,code);
    }

    @GetMapping("/check")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Check Email Exists API")
    public void checkEmail(
            @RequestParam String email
    ){
        emailService.checkEmail(email);
    }



}
