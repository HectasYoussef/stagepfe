package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.message.request.Mail;
import com.example.demo.message.request.NewPassword;
import com.example.demo.message.request.ResetPasswordRequest;
import com.example.demo.message.responce.AccountResponce;
import com.example.demo.model.User;
import com.example.demo.security.service.EmailService;
import com.example.demo.security.service.EmailServiceImpl;
import com.example.demo.security.service.UserDetailsServiceImpl;
import com.example.demo.util.UserCode;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class ResetPasswordController {
	
    @Autowired
    private EmailServiceImpl emailService; 
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	@Autowired
	PasswordEncoder encoder;
	
	@PostMapping("/checkeEmail")
	public AccountResponce resetPassword(@RequestBody ResetPasswordRequest passwordRequest) {
		//Boolean result = this.userDetailsServiceImpl.ifEmailExist(passwordRequest.getEmail());
		User user =this.userDetailsServiceImpl.getUserByMail(passwordRequest.getEmail());
		AccountResponce accountResponce=new AccountResponce();
		if(user!=null) {
			String code=UserCode.getcode();
			Mail mail =new Mail(passwordRequest.getEmail(),code);
			emailService.sendCodeByMail(mail);
			user.getCode().setCode(code);
			this.userDetailsServiceImpl.editUser(user);
			accountResponce.setResult(1);
		}else {
			accountResponce.setResult(0);
		}
		return accountResponce;
	}
	
	@PostMapping("/rezetPassword")
	public AccountResponce rezetpassword(@RequestBody NewPassword newPassword) {
		User user =this.userDetailsServiceImpl.getUserByMail(newPassword.getEmail());
		AccountResponce accountResponce=new AccountResponce();
		if(user!=null) {
			if(user.getCode().getCode().equals(newPassword.getCode())) {
				user.setPassword(encoder.encode(newPassword.getPassword()));
				userDetailsServiceImpl.editUser(user);
				accountResponce.setResult(1);
			}else {
				accountResponce.setResult(0);
			}
		}else {
			accountResponce.setResult(0);
		}
		return accountResponce;
	}
}
