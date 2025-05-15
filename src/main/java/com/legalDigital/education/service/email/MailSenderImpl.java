package com.legalDigital.education.service.email;

import com.legalDigital.education.domain.mail.RequestToSendEmailDto;
import io.micrometer.common.util.StringUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MailSenderImpl implements MailSenderService {
    private final JavaMailSender mailSender;

    private List<String> receivers;
    private SimpleMailMessage message;

    public MailSenderImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public String sendSimpleMessage(RequestToSendEmailDto requestToSendEmailDto) {
        String subject = requestToSendEmailDto.subject();
        String text = requestToSendEmailDto.text();
        String sender = requestToSendEmailDto.senderEmail();
        receivers = requestToSendEmailDto.receivers();
        String receiver = requestToSendEmailDto.receiver();


        if (StringUtils.isBlank(text) || StringUtils.isBlank(sender) || StringUtils.isBlank(subject)) {
            throw new RuntimeException("Invalid argument values for method sendEmail");
        }
        message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(receiver);
        message.setSubject(subject);
        message.setText(text);
        message.setSentDate(new Date());
        if (receiver == null) {
            try {
                getReceiver();
            } catch (Exception e) {
                throw new RuntimeException("Не удалось отправить сообщение" + e.getMessage());

            }
        } else {
            try {
                mailSender.send(message);
                return "OK";
            } catch (Exception e) {
                throw new RuntimeException("Не удалось отправить сообщение" + e.getMessage());
            }
        }
        return "";
    }
    private void getReceiver() {
        receivers.forEach(receiver -> {
            if (receivers == null) return;
            message.setTo(receiver);
            mailSender.send(message);
        });
    }
}
