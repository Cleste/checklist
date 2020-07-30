package ru.kgeu.service.impl;

import java.nio.charset.StandardCharsets;

import javax.mail.internet.MimeMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import ru.kgeu.model.dto.MailDto;
import ru.kgeu.service.api.EmailService;

@Log4j2
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String mailAddress;

    @Override
    public void sendMail(MailDto mail) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            helper.setTo(mail.getTo());
            helper.setText(mail.getText());
            helper.setSubject(mail.getSubject());
            helper.setFrom(mailAddress);
            emailSender.send(message);
        } catch (Exception exception) {
            log.warn("Email send exception!");
        }
    }
}