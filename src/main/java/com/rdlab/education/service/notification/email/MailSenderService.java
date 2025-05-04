package com.rdlab.education.service.notification.email;


import com.rdlab.education.domain.mail.RequestToSendEmailDto;

public interface MailSenderService {
    String sendSimpleMessage(RequestToSendEmailDto requestToSendEmailDto);
}
