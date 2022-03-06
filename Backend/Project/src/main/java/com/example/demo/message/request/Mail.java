package com.example.demo.message.request;

public class Mail {
private String to;
private String code;


 
public String getTo() {
	return to;
}
public void setTo(String to) {
	this.to = to;
}

public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public Mail() {
	super();
	// TODO Auto-generated constructor stub
}
public Mail(String to, String code) {
	super();
	this.to = to;
	this.code = code;
}

}




