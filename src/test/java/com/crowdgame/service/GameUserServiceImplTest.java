package com.crowdgame.service;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

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
	public void testGetUserCallsRetrievesUser() {
		service.getUser("username");
		
		Mockito.verify(em).find(GameUser.class, "username");
	}
	
	@Test
	public void testUsernameExistsForTrue() {
		String username = "username";
		Mockito.when(em.find(GameUser.class, username)).thenReturn(new GameUser());
		
		boolean result = service.usernameExists(username);
		
		assertEquals(true, result);
	}
	
	@Test
	public void testUsernameExistsForFalse() {
		String username = "username";
		Mockito.when(em.find(GameUser.class, username)).thenReturn(null);
		
		boolean result = service.usernameExists(username);
		
		assertEquals(false, result);
	}
	
}
