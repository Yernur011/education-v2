package com.legalDigital.education.service.email;


import com.legalDigital.education.domain.mail.RequestToSendEmailDto;

public interface MailSenderService {
    String sendSimpleMessage(RequestToSendEmailDto requestToSendEmailDto);
}
