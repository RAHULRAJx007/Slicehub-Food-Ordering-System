package com.slice.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.slice.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendVerificationEmail(String to, String link) {

        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject("🍕 SliceHub | Verify Your Email");

            String htmlContent = """
                <!DOCTYPE html>
                <html>
                <head>
                    <meta charset="UTF-8">
                    <style>
                        body {
                            margin: 0;
                            padding: 0;
                            background-color: #f4f6fb;
                            font-family: 'Poppins', Arial, sans-serif;
                        }
                        .container {
                            max-width: 600px;
                            margin: 40px auto;
                            background: #ffffff;
                            border-radius: 16px;
                            overflow: hidden;
                            box-shadow: 0 10px 40px rgba(0,0,0,0.1);
                        }
                        .header {
                            background: linear-gradient(135deg, #6366f1, #8b5cf6);
                            color: white;
                            padding: 30px;
                            text-align: center;
                        }
                        .header h1 {
                            margin: 0;
                            font-size: 28px;
                        }
                        .content {
                            padding: 30px;
                            color: #1f2937;
                            line-height: 1.6;
                        }
                        .content h2 {
                            margin-top: 0;
                        }
                        .btn {
                            display: inline-block;
                            margin: 25px 0;
                            padding: 14px 28px;
                            background: linear-gradient(135deg, #6366f1, #8b5cf6);
                            color: white;
                            text-decoration: none;
                            border-radius: 30px;
                            font-weight: 600;
                        }
                        .btn:hover {
                            opacity: 0.9;
                        }
                        .footer {
                            background: #f9fafb;
                            text-align: center;
                            padding: 20px;
                            font-size: 13px;
                            color: #6b7280;
                        }
                        .link {
                            word-break: break-all;
                            color: #6366f1;
                        }
                    </style>
                </head>

                <body>
                    <div class="container">
                        <div class="header">
                            <h1>🍕 SliceHub</h1>
                            <p>Fresh pizzas, fast delivery</p>
                        </div>

                        <div class="content">
                            <h2>Verify your email address</h2>

                            <p>
                                Welcome to <strong>SliceHub</strong>!  
                                We’re excited to have you on board.
                            </p>

                            <p>
                                Please confirm your email address by clicking the button below:
                            </p>

                            <div style="text-align:center;">
                                <a href="%s" class="btn">Verify Email</a>
                            </div>

                            <p>
                                Or copy and paste this link into your browser:
                            </p>

                            <p class="link">%s</p>

                            <p>
                                ⏰ This verification link will expire in <strong>24 hours</strong>.
                            </p>

                            <p>
                                If you didn’t create a SliceHub account, you can safely ignore this email.
                            </p>
                        </div>

                        <div class="footer">
                            © 2026 SliceHub. All rights reserved.<br>
                            Made with ❤️ and extra cheese.
                        </div>
                    </div>
                </body>
                </html>
                """.formatted(link, link);

            helper.setText(htmlContent, true);

            mailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send verification email", e);
        }
    }
}
