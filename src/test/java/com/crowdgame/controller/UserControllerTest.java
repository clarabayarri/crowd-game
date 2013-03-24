package com.crowdgame.controller;

import static org.junit.Assert.assertEquals;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.crowdgame.model.GameUser;
import com.crowdgame.model.Registration;
import com.crowdgame.service.GameUserService;
import com.crowdgame.util.RegistrationValidator;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	@InjectMocks
	private UserController controller = new UserController();
	
	@Mock
	private AuthenticationManager manager;
	
	@Mock
	private RegistrationValidator validator;
	
	@Mock
	private GameUserService service;
	
	@Test
	public void testLoadLoginHandleRequestView() {
		String result = controller.loadLogin();
		
		assertEquals("login", result);
	}
	
	@Test
	public void testShowRegistrationHandleRequestView() {
		Model model = Mockito.mock(Model.class);
		
		String result = controller.showRegistration(model);
		
		assertEquals("register", result);
	}
	
	@Test
	public void testShowRegistrationAddsEmptyRegistrationToModel() {
		Model model = Mockito.mock(Model.class);
		
		controller.showRegistration(model);
		
		Mockito.verify(model).addAttribute(Mockito.any(Registration.class));
	}
	
	@Test
	public void testProcessRegistrationValidatesInput() {
		Registration registration = new Registration();
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		
		controller.processRegistration(registration, bindingResult, request);
		
		Mockito.verify(validator).validate(registration, bindingResult);
	}
	
	@Test
	public void testProcessRegistrationCallsUserService() {
		Registration registration = new Registration();
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		Mockito.when(bindingResult.hasErrors()).thenReturn(false);
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		
		controller.processRegistration(registration, bindingResult, request);
		
		Mockito.verify(service).addGameUser(Mockito.any(GameUser.class));
	}

}
