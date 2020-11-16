package com.artemius.dwshop.services.imps;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.artemius.dwshop.services.EmailService;

@Service
public class EService implements EmailService {
    
    @Autowired
    JavaMailSender mailSender;

    public void sendHtmlEmail(String subject, String rawText, String sendTo, JavaMailSender mailSender) {
	
	 try {
	      MimeMessage message = mailSender.createMimeMessage();
	      MimeMessageHelper helper;
	      message.setSubject(subject);	      
	      helper = new MimeMessageHelper(message, true);
	      helper.setFrom("DW Shop Russia");
	      helper.setTo(sendTo);
	      helper.setText(rawText, true);
	      mailSender.send(message);
	  }   
	  catch (MessagingException ex) {
	     // Logger.getLogger(HTMLMail.class.getName()).log(Level.SEVERE, null, ex);
	  }

    }

}
