package com.example.demo.controller;
import java.util.Collection;
import java.util.HashSet;




import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.repository.UserRepository;

import com.example.demo.security.jwt.JwtProvider;
import com.example.demo.security.service.EmailService;
import com.example.demo.security.service.UserActive;
import com.example.demo.security.service.UserDetailsServiceImpl;
import com.example.demo.util.UserCode;
import com.example.demo.Constant.UserConstant;
import com.example.demo.message.request.ActiveAccount;
import com.example.demo.message.request.LoginForm;
import com.example.demo.message.request.Mail;
import com.example.demo.message.request.ResetPasswordRequest;
import com.example.demo.message.request.SignUpForm;
import com.example.demo.message.responce.AccountResponce;
import com.example.demo.message.responce.JwtResponse;
import com.example.demo.message.responce.ResponseMessage;
import com.example.demo.model.Code;
import com.example.demo.model.User;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {
	
	public static final String DEFAULT_ROLE = "ROLE_USER";
    public static final String ADMIN_ACCESS = "ROLE_ADMIN";
    public static final String MODERATOR_ACCESS = "ROLE_MODERATOR";



	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;


	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
	EmailService emailService;
	@Autowired
	UserDetailsServiceImpl userservice;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
	}

	@PostMapping("/signup")
	public AccountResponce registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
	
		AccountResponce accountResponce=new AccountResponce();
		Boolean result1=userRepository.existsByUsername(signUpRequest.getUsername());

		Boolean result=userRepository.existsByEmail(signUpRequest.getEmail());
		if(result) {
			accountResponce.setResult(0);
		}else {

		// Creating user's account
		User userregister = new User();
		userregister.setPrenom(signUpRequest.getPrenom());
		userregister.setNom(signUpRequest.getNom());
		userregister.setUsername(signUpRequest.getUsername());
		userregister.setDatenaissance(signUpRequest.getDatenaissance());
		userregister.setCodepostal(signUpRequest.getCodepostal());
		userregister.setTelephone(signUpRequest.getTelephone());
		userregister.setVille(signUpRequest.getVille());
		userregister.setDateajout(signUpRequest.getDateajout());
		userregister.setCin(signUpRequest.getCin());
		userregister.setEmail(signUpRequest.getEmail());
		
		userregister.setActive(0);
	
		String roless=signUpRequest.getRole();
        
		 if(roless=="admin") {	 
			 userregister.setRoles(ADMIN_ACCESS);
		 }else if (roless=="pm") {
			 userregister.setRoles(MODERATOR_ACCESS);
		 }else {
			 userregister.setRoles(DEFAULT_ROLE);
		 }
		 
		 
		userregister.setPassword(encoder.encode(signUpRequest.getPassword()));

		String mycode=UserCode.getcode();	
	    Mail mail=new Mail(signUpRequest.getEmail(),mycode);
     	emailService.sendCodeByMail(mail);
		Code code =new Code();
		code.setCode(mycode);
		userregister.setCode(code);
		userRepository.save(userregister);
		accountResponce.setResult(1);
		}
		return accountResponce;
	
		}
	@PostMapping("/active")
	public UserActive getActiveUser(@RequestBody SignUpForm signUpRequest) {
		String enPassword = userservice.getPasswordByEmail(signUpRequest.getEmail()); //From DB
		Boolean result =encoder.matches(signUpRequest.getPassword(),enPassword); // Sure
        UserActive userActive=new UserActive(); 
		if (result) {
			int act =userservice.getUserActive(signUpRequest.getEmail());
			if (act == 0) {
				String code =UserCode.getcode();
				Mail mail = new Mail(signUpRequest.getEmail(),code);
				emailService.sendCodeByMail(mail);
				User user =userservice.getUserByMail(signUpRequest.getEmail());
				user.getCode().setCode(code);
				userservice.editUser(user);
				}
			userActive.setActive(act);
		}else {
			userActive.setActive(-1);
		}
		return userActive;
	}
	
	@PostMapping("/activated")
	public AccountResponce activeaccount(@RequestBody ActiveAccount activeAccount) {
		User user =userservice.getUserByMail(activeAccount.getMail());
		AccountResponce accountResponce=new AccountResponce();
		if (user.getCode().getCode().equals(activeAccount.getCode())) {
			user.setActive(1);
			userservice.editUser(user);
			accountResponce.setResult(1);
		}else {
			accountResponce.setResult(0);
		}
		return accountResponce;
	}
	
	
	

	
}