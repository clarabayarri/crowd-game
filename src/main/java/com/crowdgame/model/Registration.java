package com.crowdgame.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


public class Registration {

	private String username;
	
	@Size(min = 4, max = 20)
	private String password;
	
	private String confirmPassword;
	
	@NotEmpty
	@Email
	private String email;
	
	@NotNull
	private boolean dyslexic;
	
	@NotNull
	@Min(4)
	@Max(120)
	private Integer age;
	
	@NotNull
	private boolean spanishSpeaker;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isDyslexic() {
		return dyslexic;
	}

	public void setDyslexic(boolean dyslexic) {
		this.dyslexic = dyslexic;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public boolean isSpanishSpeaker() {
		return spanishSpeaker;
	}

	public void setSpanishSpeaker(boolean isSpanishSpeaker) {
		this.spanishSpeaker = isSpanishSpeaker;
	}

}
