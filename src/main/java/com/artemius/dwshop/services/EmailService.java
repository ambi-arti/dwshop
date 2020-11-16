package com.artemius.dwshop.services;

import org.springframework.mail.javamail.JavaMailSender;

public interface EmailService {
    public void sendHtmlEmail(String subject, String rawText, String sendTo, JavaMailSender mailSender);
	
}
