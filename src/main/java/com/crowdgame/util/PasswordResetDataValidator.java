package com.crowdgame.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.crowdgame.aux.PasswordResetData;

@Component
public class PasswordResetDataValidator {

	public void validate(Object target, Errors errors) {
		PasswordResetData data = (PasswordResetData) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
		        "registration.password.notEmpty");
		if (data.getPassword() != null && !data.getPassword().equals(data.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "registration.password.noMatch");
		}
	}
}
