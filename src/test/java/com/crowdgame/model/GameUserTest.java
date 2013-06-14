package com.crowdgame.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.crowdgame.aux.Registration;

public class GameUserTest {

	private GameUser user;
	
	private static final String username = "username";
	private static final String password = "password";
	private static final String email = "email";
	private static final Boolean dyslexic = true;
	private static final Integer age = 67;
	private static final Boolean spanishSpeaker = false;
	
	@Test
	public void testCreateFromRegistration() {
		Registration registration = new Registration();
		registration.setUsername(username);
		registration.setPassword(password);
		registration.setEmail(email);
		registration.setDyslexic(dyslexic);
		registration.setSpanishSpeaker(spanishSpeaker);
		registration.setAge(age);
		
		user = new GameUser(registration);
		
		assertEquals(username, user.getUsername());
		assertEquals(password, user.getPassword());
		assertEquals(email, user.getEmail());
		assertEquals(dyslexic, user.isDyslexic());
		assertEquals(spanishSpeaker, user.isSpanishSpeaker());
		assertEquals(age, user.getAge());
	}
	
	@Test
	public void testCreateFromRegistrationAssignsUserRole() {
		Registration registration = new Registration();
		
		user = new GameUser(registration);
		
		assertNotNull(user.getRoles());
		assertEquals(1, user.getRoles().size());
		assertEquals("ROLE_USER", user.getRoles().get(0));
	}
}
