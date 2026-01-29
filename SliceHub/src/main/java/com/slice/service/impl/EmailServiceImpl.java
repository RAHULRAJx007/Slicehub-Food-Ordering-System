package com.slice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.slice.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendVerificationEmail(String to, String link) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("SliceHub - Verify Your Email");
        message.setText(
                "Welcome to SliceHub!\n\n"
              + "Please verify your email by clicking the link below:\n"
              + link + "\n\n"
              + "This link will expire in 24 hours."
        );

        mailSender.send(message);
    }
}
