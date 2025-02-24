package com.rdlab.education.domain.mail;

import java.util.List;

public record RequestToSendEmailDto(String receiver,
                                    List<String> receivers,
                                    String senderEmail,
                                    String subject,
                                    String text) {
}
