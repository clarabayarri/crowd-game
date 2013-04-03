package com.crowdgame.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.crowdgame.model.Registration;
import com.crowdgame.service.GameUserService;

@Component
public class RegistrationValidator {

	@Autowired
	private GameUserService service;
	
	public void validate(Registration registration, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", 
				"registration.username.notEmpty");
		String username = registration.getUsername();
		if (service.usernameExists(username)) {
			errors.rejectValue("username", "registration.username.exists");
		}
		if (username.length() > 50) {
			errors.rejectValue("username", "registration.username.lengthExceeded");
		}
		if (!registration.getPassword().equals(registration.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "registration.password.noMatch");
		}
	}

}
