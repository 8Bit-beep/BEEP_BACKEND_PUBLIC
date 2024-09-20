package com.beep.beep.domain.email.service;


import com.beep.beep.domain.email.dao.EmailCertificationDao;
import com.beep.beep.domain.email.exception.EmailAlreadyExistsException;
import com.beep.beep.domain.email.exception.EmailNotFoundException;
import com.beep.beep.domain.email.exception.InvalidCodeException;
import com.beep.beep.domain.email.presentation.dto.request.EmailSendingRequest;
import com.beep.beep.domain.student.domain.repository.StudentJpaRepo;
import com.beep.beep.domain.user.domain.repo.UserJpaRepo;
import com.beep.beep.domain.user.service.UserService;
import com.beep.beep.global.common.dto.response.Response;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class EmailService {

    private static final String MAIL_TITLE_CERTIFICATION = "안녕하세요~ 8bit입니다.삑의 회원이 돼주셔서 감사합니다! 인증번호를 확인해주세요 ";
    private final JavaMailSender mailSender;
    private final EmailCertificationDao emailDao;
    private final UserService userService;

    public Response sendEmail(EmailSendingRequest request) throws NoSuchAlgorithmException, MessagingException {
        userService.existsByEmail(request.email());

        String code = createCode();
        String content = String.format("반가워요,삑입니다:D \n 이메일 인증번호 : %s" , code);
        String email = request.email();

        emailDao.saveCode(email,code);
        sendMail(email,content);

        return Response.created("이메일 전송 성공");
    }

    public Response verifyEmail(String email, String code) {
        if (!isVerify(email, code))
            throw InvalidCodeException.EXCEPTION;

        emailDao.removeCode(email);

        return Response.ok("이메일 인증 성공");
    }

    private String createCode() throws NoSuchAlgorithmException {
        String code;

        do {
            int num = SecureRandom.getInstanceStrong().nextInt(999999);
            code = String.valueOf(num);
        } while (code.length() != 4);

        return code;
    }

    private void sendMail(String email, String content) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setTo(email);
        helper.setSubject(MAIL_TITLE_CERTIFICATION);
        helper.setText(content);
        mailSender.send(mimeMessage);
    }

    private boolean isVerify(String email, String code) {
        boolean validatedEmail = isEmailExists(email);
        if (!isEmailExists(email))
            throw EmailNotFoundException.EXCEPTION;

        return (validatedEmail &&
                emailDao.getCode(email).equals(code));
    }

    private boolean isEmailExists(String email) {
        return emailDao.hasKey(email);
    }

}
