package com.example.demo.message.request;

public class NewPassword {
  private String email;
  private String code;
  private String password;
  

public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public NewPassword(String email,String code, String password) {
	super();
	this.email=email;
	this.code = code;
	this.password = password;
}
public NewPassword() {
	super();
	// TODO Auto-generated constructor stub
}
  

}
