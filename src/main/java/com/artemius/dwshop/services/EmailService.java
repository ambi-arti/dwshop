package com.artemius.dwshop.services;

import org.springframework.mail.javamail.JavaMailSender;

public interface EmailService {
    public int sendHtmlEmail(String subject, String rawText, String sendTo);
	
}
