package com.beep.beep.domain.email.service;


import com.beep.beep.domain.email.dao.EmailCertificationDao;
import com.beep.beep.domain.email.exception.EmailNotFoundException;
import com.beep.beep.domain.email.exception.InvalidCodeException;
import com.beep.beep.domain.email.presentation.dto.request.EmailSendingRequest;
import com.beep.beep.domain.user.facade.UserFacade;
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
    private final UserFacade userFacade;

    public void sendEmail(EmailSendingRequest request) throws NoSuchAlgorithmException, MessagingException {
        String code = createCode();
        String content = String.format("반가워요,삑입니다:D \n 이메일 인증번호 : %s" , code);
        String email = request.getEmail();

        userFacade.existsByEmail(email);

        emailDao.saveCode(email,code);
        sendMail(email,content);

    }

    public void verifyEmail(String email, String code) {
        if (!isVerify(email, code))
            throw InvalidCodeException.EXCEPTION;

        emailDao.removeCode(email);
    }

    public void checkEmail(String email){
            userFacade.emailExists(email);
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
