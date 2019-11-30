package ies.grupo33.CampusMonitoring.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServices {

    @Autowired
    private JavaMailSender emailSender;

    public void sendNotification(List<String> emails, String subject, String text) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();

        // change to set Bcc if you want to hide the recipients.
        message.setTo(emails.toArray(new String[0]));
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
