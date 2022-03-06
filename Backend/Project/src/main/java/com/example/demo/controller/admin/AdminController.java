package com.example.demo.controller.admin;

import java.util.HashSet;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.message.request.SignUpForm;
import com.example.demo.message.responce.ResponseMessage;

import com.example.demo.model.User;

import com.example.demo.repository.UserRepository;
import com.example.demo.security.jwt.JwtProvider;
import com.example.demo.security.service.admin.AdminUserService;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/admin")
public class AdminController {
	public static final String DEFAULT_ROLE = "ROLE_USER";
    public static final String ADMIN_ACCESS = "ROLE_ADMIN";
    public static final String MODERATOR_ACCESS = "ROLE_MODERATOR";
	@Autowired
	  private AdminUserService userService;

	@Autowired
	UserRepository userRepository;



	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;
	    @GetMapping(path = "/user")
	    public List<User> index() {
	        return userService.index();
	    }
	    
	    @GetMapping(path = "/user/{id}")
	    public Optional<User> finduser(@PathVariable Long id) {
	        return userService.getOne(id);
	    }

	    @DeleteMapping(path = "/user/{id}")
	    public ResponseEntity<Void> destroy(@PathVariable Long id) {
	        return userService.delete(id);
	    }
	    
	    @PutMapping(path = "/user/{id}")
	    public User updateUser(@RequestBody User user,@PathVariable Long id){
	    
	     return userService.updateUser(user, id);
	     	 
	    }
	    
	    @GetMapping(path="/{ville}")
	    public List<User> getByVille(@PathVariable String ville) {
	    	return userRepository.findByVille(ville);
	    }
	    
	    @GetMapping(path="ByCin/{cin}")
	    public User getByCin(@PathVariable String cin) {
	    	return userRepository.findByCin(cin);
	    }
	    
	     	
	    @PostMapping("/user")
	    public ResponseEntity<?> createUser(@Valid @RequestBody SignUpForm signUpRequest) {
			if (userRepository.existsByUsername(signUpRequest.getUsername())) {
				return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
						HttpStatus.BAD_REQUEST);
			}

			if (userRepository.existsByEmail(signUpRequest.getEmail())) {
				return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
						HttpStatus.BAD_REQUEST);
			}

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
			userregister.setRoles(signUpRequest.getRole());
	        
			 if((signUpRequest.getRole())=="admin") {	 
				 userregister.setRoles(ADMIN_ACCESS);
			 }else if ((signUpRequest.getRole())=="pm") {
				 userregister.setRoles(MODERATOR_ACCESS);
			 }else {
				 userregister.setRoles(DEFAULT_ROLE);
			 }
			
			userregister.setPassword(encoder.encode(signUpRequest.getPassword()));
		
			 
			userRepository.save(userregister);

			return new ResponseEntity<>(new ResponseMessage("l'utilisateur"+ signUpRequest.getPrenom() + " is registered successfully!"), HttpStatus.OK);
		}
}
