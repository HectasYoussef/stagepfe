package com.example.demo.security.service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.message.request.LoginForm;
import com.example.demo.message.request.SignUpForm;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User user = userRepository.findByEmail(email);
		return UserPrinciple.build(user);
	}
	
	public Boolean ifEmailExist(String email) {return userRepository.existsByEmail(email);}
	 
	@Transactional
	public int getUserActive(String email) {
		return userRepository.getActivee(email);
	}
	@Transactional
	public String getPasswordByEmail(String email) {
		return userRepository.getPasswordByEmail(email); 
	}
	
	
	
	public User getUserByMail(String mail) {
		return this.userRepository.findByEmail(mail);
	}
	
	
	public void editUser(User user) {
	this.userRepository.save(user);	
	}
	
}