package com.example.demo.security.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserActive {
public int active;

public UserActive() {
	super();
	// TODO Auto-generated constructor stub
}

public UserActive(int active) {
	super();
	this.active = active;
}

public int getActive() {
	return active;
}

public void setActive(int active) {
	this.active = active;
}

}
