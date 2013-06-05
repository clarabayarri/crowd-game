package com.crowdgame.aux;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PasswordResetData {

	@NotNull
	public Long uid;
	
	public String username;
	
	@Size(min = 4, max = 20, message="{registration.password.wrongSize}")
	public String password;
	
	public String confirmPassword;

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

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
}
