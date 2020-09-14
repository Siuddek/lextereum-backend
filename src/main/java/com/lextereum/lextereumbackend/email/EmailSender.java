package com.lextereum.lextereumbackend.email;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailSender {

    private final EmailProperties properties;
    private final JavaMailSender mailSender;

    public void sendEmail(String emailTo, String message) {
        try {
            MimeMessage mimeMessage = getMimeMessage(emailTo, message);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new EmailSendingException("Exception occurred while preparing email message", e);
        }
    }

    private MimeMessage getMimeMessage(String emailTo, String message) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setFrom(properties.getFrom());
        helper.setTo(emailTo);
        helper.setSubject(properties.getSubject());
        helper.setText(message, true);
        return mimeMessage;
    }
}
