package com.crowdgame.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.crowdgame.aux.PasswordResetData;
import com.crowdgame.aux.Registration;
import com.crowdgame.model.GameUser;
import com.crowdgame.model.PasswordResetRequest;
import com.crowdgame.service.GameUserService;
import com.crowdgame.util.MailSender;
import com.crowdgame.util.PasswordResetDataValidator;
import com.crowdgame.util.RegistrationValidator;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	@InjectMocks
	private UserController controller = new UserController();
	
	@Mock
	private RegistrationValidator registrationValidator;
	
	@Mock
	private PasswordResetDataValidator passwordResetValidator;
	
	@Mock
	private AuthenticationManager manager;
	
	@Mock
	private GameUserService service;
	
	@Mock
	private PasswordEncoder encoder;
	
	@Mock
	private MailSender mailSender;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		Mockito.when(encoder.encodePassword(Mockito.anyString(), Mockito.isNull())).thenReturn("password");
	}
	
	@Test
	public void testLoadLoginHandleRequestView() {
		String result = controller.loadLogin(null, null);
		
		assertEquals("login", result);
	}
	
	@Test
	public void testLoadLoginAddsErrorAttributeIfProvided() {
		Model model = Mockito.mock(Model.class);
		
		controller.loadLogin(model, true);
		
		Mockito.verify(model).addAttribute("error", true);
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
	public void testProcessRegistrationHandleRequestViewBindingErrors() {
		Registration registration = new Registration();
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		Mockito.when(bindingResult.hasErrors()).thenReturn(true);
		
		String result = controller.processRegistration(registration, bindingResult, null);
		
		assertEquals("register", result);
	}
	
	@Test
	public void testProcessRegistrationHandleRequestViewCreationErrors() {
		Registration registration = new Registration();
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		
		String result = controller.processRegistration(registration, bindingResult, null);
		
		assertEquals("register", result);
	}
	
	@Test
	public void testProcessRegistrationHandleRequestViewSuccess() {
		Registration registration = new Registration();
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		Mockito.when(service.getUser(Mockito.anyString())).thenReturn(new GameUser());
		
		String result = controller.processRegistration(registration, bindingResult, request);
		
		assertEquals("redirect:/game", result);
	}
	
	@Test
	public void testProcessRegistrationValidatesInput() {
		Registration registration = new Registration();
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		
		controller.processRegistration(registration, bindingResult, request);
		
		Mockito.verify(registrationValidator).validate(registration, bindingResult);
	}
	
	@Test
	public void testProcessRegistrationEncodesPassword() {
		Registration registration = new Registration();
		String password = "password";
		registration.setPassword(password);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		
		controller.processRegistration(registration, bindingResult, request);
		
		Mockito.verify(encoder).encodePassword(password, null);
	}
	
	@Test
	public void testProcessRegistrationCallsUserService() {
		Registration registration = new Registration();
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		
		controller.processRegistration(registration, bindingResult, request);
		
		Mockito.verify(service).addGameUser(Mockito.any(GameUser.class));
	}
	
	@Test
	public void testProcessRegistrationHandlesLogin() {
		Registration registration = new Registration();
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		Mockito.when(service.getUser(Mockito.anyString())).thenReturn(new GameUser());
		
		controller.processRegistration(registration, bindingResult, request);
		
		Mockito.verify(manager).authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class));
	}
	
	@Test
	public void testShowUserDataHandleRequestView() {
		Model model = Mockito.mock(Model.class);
		
		String result = controller.showUserData(model);
		
		assertEquals("user", result);
	}
	
	@Test
	public void testShowUserDataAddsCurrentUserToModel() {
		Model model = Mockito.mock(Model.class);
		GameUser user = new GameUser();
		Mockito.when(service.getCurrentUser()).thenReturn(user);
		
		controller.showUserData(model);
		
		Mockito.verify(model).addAttribute("user", user);
	}
	
	@Test
	public void testDeleteCurrentUserHandleRequestView() {
		Mockito.when(service.getCurrentUser()).thenReturn(new GameUser());
		
		String result = controller.deleteCurrentUser();
		
		assertEquals("redirect:/home", result);
	}
	
	@Test
	public void testDeleteCurrentUserLogsOutUser() {
		Mockito.when(service.getCurrentUser()).thenReturn(new GameUser());
		SecurityContext context = Mockito.mock(SecurityContext.class);
		SecurityContextHolder.setContext(context);
		
		controller.deleteCurrentUser();
		
		Mockito.verify(context).setAuthentication(null);
	}

	@Test
	public void testShowPolicyHandleRequestView() {
		String result = controller.showPolicy();
		
		assertEquals("policy", result);
	}
	
	@Test
	public void testForgotPasswordWithUnknownUser() {
		boolean result = controller.forgotPassword("unknown username");
		
		assertEquals(false, result);
	}
	
	@Test
	public void testForgotPasswordCreatedNewPasswordResetRequest() {
		GameUser user = new GameUser();
		String username = "username";
		Mockito.when(service.getUserByUsernameOrEmail(username)).thenReturn(user);
		
		controller.forgotPassword(username);
		
		assertNotNull(user.getPasswordResetRequest());
		Mockito.verify(service).saveGameUser(user);
	}
	
	@Test
	public void testForgotPasswordSendsEmail() {
		GameUser user = new GameUser();
		String username = "username";
		Mockito.when(service.getUserByUsernameOrEmail(username)).thenReturn(user);
		
		controller.forgotPassword(username);
		
		Mockito.verify(mailSender).sendPasswordResetMail(user, user.getPasswordResetRequest().getId());
	}
	
	@Test
	public void testForgotPasswordReturnsTrueWhenAllCorrect() {
		GameUser user = new GameUser();
		String username = "username";
		Mockito.when(service.getUserByUsernameOrEmail(username)).thenReturn(user);
		
		boolean result = controller.forgotPassword(username);
		
		assertEquals(true, result);
	}
	
	@Test
	public void testLoadPasswordResetHandleRequestView() {
		Long uid = new Long(333);
		Model model = Mockito.mock(Model.class);
		
		String result = controller.loadPasswordReset(uid, model);
		
		assertEquals("password-reset", result);
	}
	
	@Test
	public void testLoadPasswordResetAddsPasswordResetDataToModel() {
		Long uid = new Long(333);
		Model model = Mockito.mock(Model.class);
		
		controller.loadPasswordReset(uid, model);
		
		Mockito.verify(model).addAttribute(Mockito.any(PasswordResetData.class));
		Mockito.verify(model).addAttribute("uid", uid);
	}
	
	@Test
	public void testResetPasswordHandleRequestViewWithErrors() {
		PasswordResetData data = new PasswordResetData();
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		Mockito.when(bindingResult.hasErrors()).thenReturn(true);
		
		String result = controller.resetPassword(data, bindingResult);
		
		assertEquals("password-reset", result);
	}
	
	@Test
	public void testResetPasswordHandleRequestViewWithoutErrors() {
		PasswordResetData data = new PasswordResetData();
		data.setUsername("username");
		data.setPassword("password");
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		Mockito.when(bindingResult.hasErrors()).thenReturn(false);
		GameUser user = new GameUser();
		PasswordResetRequest request = new PasswordResetRequest();
		request.setId(new Long(1));
		user.setPasswordResetRequest(request);
		Mockito.when(service.getUserByUsernameOrEmail(Mockito.anyString())).thenReturn(user);
		Mockito.when(encoder.encodePassword("password", null)).thenReturn("password");
		
		String result = controller.resetPassword(data, bindingResult);
		
		assertEquals("redirect:/login", result);
	}
	
	@Test
	public void testResetPasswordValidatesData() {
		PasswordResetData data = new PasswordResetData();
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		Mockito.when(bindingResult.hasErrors()).thenReturn(true);
		
		controller.resetPassword(data, bindingResult);
		
		Mockito.verify(passwordResetValidator).validate(data, bindingResult);
	}
	
	@Test
	public void testResetPasswordInvalidatesIfUserIsNull() {
		PasswordResetData data = new PasswordResetData();
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		Mockito.when(bindingResult.hasErrors()).thenReturn(true);
		
		controller.resetPassword(data, bindingResult);
		
		Mockito.verify(bindingResult).reject("password.change.error");
	}
	
	@Test
	public void testResetPasswordInvalidatesIfUserHasNoRequest() {
		PasswordResetData data = new PasswordResetData();
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		Mockito.when(bindingResult.hasErrors()).thenReturn(true);
		Mockito.when(service.getUserByUsernameOrEmail(Mockito.anyString())).thenReturn(new GameUser());
		
		controller.resetPassword(data, bindingResult);
		
		Mockito.verify(bindingResult).reject("password.change.error");
	}
	
	@Test
	public void testResetPasswordInvalidatesIfWrongUid() {
		PasswordResetData data = new PasswordResetData();
		data.setUid(new Long(5));
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		Mockito.when(bindingResult.hasErrors()).thenReturn(true);
		GameUser user = new GameUser();
		PasswordResetRequest request = new PasswordResetRequest();
		request.setId(new Long(1));
		user.setPasswordResetRequest(request);
		Mockito.when(service.getUserByUsernameOrEmail(Mockito.anyString())).thenReturn(user);
		
		controller.resetPassword(data, bindingResult);
		
		Mockito.verify(bindingResult).reject("password.change.error");
	}
	
	@Test
	public void testResetPasswordInvalidatesOldRequest() {
		PasswordResetData data = new PasswordResetData();
		data.setUid(new Long(1));
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		Mockito.when(bindingResult.hasErrors()).thenReturn(false);
		GameUser user = new GameUser();
		PasswordResetRequest request = new PasswordResetRequest();
		request.setId(new Long(1));
		request.setGenerationDate(generateOldDate());
		user.setPasswordResetRequest(request);
		Mockito.when(service.getUserByUsernameOrEmail(Mockito.anyString())).thenReturn(user);
		
		controller.resetPassword(data, bindingResult);
		
		Mockito.verify(bindingResult).reject("password.change.error");
	}
	
	private Date generateOldDate() {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, -24);
		return calendar.getTime();
	}
	
	@Test
	public void testResetPasswordErasesRequestAndChangesPasswordWhenCorrect() {
		PasswordResetData data = new PasswordResetData();
		data.setUid(new Long(1));
		String password = "new password";
		data.setPassword(password);
		Mockito.when(encoder.encodePassword(password, null)).thenReturn(password);
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		Mockito.when(bindingResult.hasErrors()).thenReturn(false);
		GameUser user = new GameUser();
		PasswordResetRequest request = new PasswordResetRequest();
		request.setId(new Long(1));
		request.setGenerationDate(new Date());
		user.setPasswordResetRequest(request);
		Mockito.when(service.getUserByUsernameOrEmail(Mockito.anyString())).thenReturn(user);
		
		controller.resetPassword(data, bindingResult);
		
		assertEquals(password, user.getPassword());
		Mockito.verify(service).saveGameUser(user);
	}
}
