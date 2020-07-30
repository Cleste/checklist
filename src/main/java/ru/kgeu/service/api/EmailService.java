package ru.kgeu.service.api;

import ru.kgeu.model.dto.MailDto;

public interface EmailService {
    void sendMail(MailDto mail);
}
