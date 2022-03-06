 package com.example.demo.security.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.message.request.Mail;
import com.example.demo.util.UserCode;
@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
	private JavaMailSender javaMailSender;
    
	@Autowired
	public EmailServiceImpl(JavaMailSender javaMailSender) {
		this.javaMailSender=javaMailSender;
	}
	


	@Override
	@Async
	public void sendCodeByMail(Mail mail) {
		SimpleMailMessage simpleMailMessage =new SimpleMailMessage();
		simpleMailMessage.setFrom("youssefchouchene09@gmail.com");
		simpleMailMessage.setTo(mail.getTo());
		simpleMailMessage.setSubject("Code Active");
		simpleMailMessage.setText(mail.getCode());
		javaMailSender.send(simpleMailMessage);
		
	}

}
