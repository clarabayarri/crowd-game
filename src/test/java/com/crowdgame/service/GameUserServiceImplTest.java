package com.crowdgame.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import com.crowdgame.model.GameUser;
import com.crowdgame.util.RemoteCommunicationService;

@RunWith(MockitoJUnitRunner.class)
public class GameUserServiceImplTest {

	@InjectMocks
	private GameUserServiceImpl service = new GameUserServiceImpl();

	@Mock
	private EntityManager em;
	
	@Mock
	private RemoteCommunicationService remoteService;
	
	private static final String username = "username";
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testAddGameUserPersistsUser() {
		GameUser user = new GameUser();
		
		service.addGameUser(user);
		
		Mockito.verify(em).persist(user);
	}
	
	@Test
	public void testAddGameUserCreatesUserRemotely() {
		GameUser user = new GameUser();
		
		service.addGameUser(user);
		
		Mockito.verify(remoteService).postGameUser(user);
	}
	
	@Test
	public void testGetUserRetrievesUser() {
		service.getUser(username);
		
		Mockito.verify(em).find(GameUser.class, username);
	}
	
	@Test
	public void testGetCurrentUserRetrievesUserWithSecurityUsername() {
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, "12345"));
		
		service.getCurrentUser();
		
		Mockito.verify(em).find(GameUser.class, username);
	}
	
	@Test
	public void testGetCurrentUserReturnsNullIfNoAuthentication() {
		SecurityContextHolder.getContext().setAuthentication(null);
		
		GameUser result = service.getCurrentUser();
		
		assertNull(result);
	}
	
	@Test
	public void testUsernameExistsForTrue() {
		Mockito.when(em.find(GameUser.class, username)).thenReturn(new GameUser());
		
		boolean result = service.usernameExists(username);
		
		assertEquals(true, result);
	}
	
	@Test
	public void testUsernameExistsForFalse() {
		Mockito.when(em.find(GameUser.class, username)).thenReturn(null);
		
		boolean result = service.usernameExists(username);
		
		assertEquals(false, result);
	}
	
}
