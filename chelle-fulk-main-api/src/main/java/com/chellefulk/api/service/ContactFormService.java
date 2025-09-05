package com.chellefulk.api.service;

import com.chellefulk.api.dto.ContactFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ContactFormService {
    @Autowired
    private JavaMailSender mailSender;

    public boolean sendContactEmail(ContactFormDTO form) {
        System.out.println("[ContactFormService] Received contact form: " + form);
    SimpleMailMessage message = new SimpleMailMessage();
    System.out.println("[ContactFormService] Creating mail message");
    message.setTo("25culhane@cardinalmail.cua.edu");
    message.setSubject(form.getSubject());
    message.setText("Hi, my name is " + form.getName() + "\n\n" + form.getMessage());
    message.setFrom(form.getEmail());
        System.out.println("[ContactFormService] Sending mail...");
        try {
            mailSender.send(message);
            System.out.println("[ContactFormService] Mail sent!");
            return true;
        } catch (Exception e) {
            System.out.println("[ContactFormService] Mail send failed: " + e.getMessage());
            return false;
        }
    }
}
