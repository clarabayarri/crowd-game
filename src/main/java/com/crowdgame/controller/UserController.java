package com.crowdgame.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crowdgame.model.GameUser;
import com.crowdgame.model.Registration;
import com.crowdgame.service.GameUserService;
import com.crowdgame.util.RegistrationValidator;

@Controller
public class UserController {

	@Autowired
	private RegistrationValidator registrationValidator;
	
	@Autowired @Qualifier("org.springframework.security.authenticationManager")
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private GameUserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping("/login")
	public String loadLogin() {
		return "login";
	}
	
	@RequestMapping(value={"/register"}, method=RequestMethod.GET)
	public String showRegistration(Model model) {
		model.addAttribute(new Registration());
		return "register";
	}

	@RequestMapping(value={"/register"}, method=RequestMethod.POST)
	public String processRegistration(@Valid Registration registration, 
			BindingResult bindingResult, HttpServletRequest request) {
		registrationValidator.validate(registration, bindingResult);
		if (bindingResult.hasErrors()) {
			return "register";
		}
		GameUser newUser = new GameUser(registration);
		newUser.setPassword(passwordEncoder.encodePassword(registration.getPassword(), null));
		userService.addGameUser(newUser);
		GameUser user = userService.getUser(registration.getUsername());
		if (user != null) {
			authenticateUserAndSetSession(registration.getUsername(), registration.getPassword(), request);
			return "redirect:/game";
		}
		bindingResult.reject("registration.error");
		return "register";
	}
	
	@RequestMapping(value={"/user"})
	public String showUserData(Model model) {
		model.addAttribute("user", userService.getCurrentUser());
		return "user";
	}
	
	@RequestMapping(value={"/deleteUser"})
	public String deleteCurrentUser() {
		GameUser user = userService.getCurrentUser();
		userService.removeUser(user.getUsername());
		SecurityContextHolder.getContext().setAuthentication(null);
		return "redirect:/home";
	}
	
	@RequestMapping(value={"/policy"})
	public String showPolicy() {
		return "policy";
	}
	
	private void authenticateUserAndSetSession(String username, String password, HttpServletRequest request) {
		UsernamePasswordAuthenticationToken token = 
				new UsernamePasswordAuthenticationToken(username, password);

	    request.getSession();

	    token.setDetails(new WebAuthenticationDetails(request));
	    Authentication authenticatedUser = authenticationManager.authenticate(token);

	    SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
	}
	
}
