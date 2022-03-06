package com.example.demo.repository;
import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Constant.UserConstant;
import com.example.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
      
	   User findByRoles(String adminAccess);
	   User findByCin(String cin);
	   User findByEmail(String email);
	   User findByUsername(String username);
	   public User findByResetPasswordToken(String token);
	   List<User> findByVille(String ville);
	   Boolean existsByUsername(String username);
	   Boolean existsByEmail(String email);
	   Optional<User> findById(Long id);
	   User findByActive(int active);
	  
	  
	  
	  @Query("select u.active from User u where u.email=?1")
	  int getActivee(String email);
	  
	  @Query("select u.password from User u where u.email=?1")
	  public String getPasswordByEmail(String email);
	 
}