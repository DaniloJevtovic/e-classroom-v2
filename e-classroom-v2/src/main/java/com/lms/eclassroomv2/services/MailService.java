package com.lms.eclassroomv2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	
	@Autowired
	Environment environment;

	@Autowired
	JavaMailSender javaMailSender;

	public void sendMail(String reciver, String subject, String text) {
		SimpleMailMessage mail = new SimpleMailMessage();

		mail.setFrom(environment.getProperty("spring.mail.username"));

		mail.setTo(reciver);
		mail.setSubject(subject);
		mail.setText(text);

		javaMailSender.send(mail);
	}

}
