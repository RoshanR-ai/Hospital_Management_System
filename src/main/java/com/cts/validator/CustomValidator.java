package com.cts.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.cts.exception.DuplicateUserException;
import com.cts.model.User;
import com.cts.service.UserService;

@Component
public class CustomValidator {
	@Autowired
	private UserService userService;
	
	public void validateNewUser(User user,BindingResult bs) {
		try {
			userService.saveUser(user);
		}
		catch(DuplicateUserException ex) {
			bs.rejectValue("userName","userName.error", ex.getMessage());
		}
	}
}
