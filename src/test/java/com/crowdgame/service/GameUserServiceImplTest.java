package com.crowdgame.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.crowdgame.model.GameUser;
import com.google.common.collect.Lists;

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
	public void testSaveGameUserSavesUser() {
		GameUser user = new GameUser();
		
		service.saveGameUser(user);
		
		Mockito.verify(em).merge(user);
	}
	
	@Test
	public void testGetUserRetrievesUser() {
		service.getUser(username);
		
		Mockito.verify(em).find(GameUser.class, username);
	}
	
	@Test
	public void testGetCurrentUserRetrievesUserWithSecurityUsername() {
		SecurityContext context = Mockito.mock(SecurityContext.class);
		SecurityContextHolder.setContext(context);
		Mockito.when(context.getAuthentication()).thenReturn(new UsernamePasswordAuthenticationToken(username, "12345"));
		
		service.getCurrentUser();
		
		Mockito.verify(em).find(GameUser.class, username);
	}
	
	@Test
	public void testGetCurrentUserReturnsNullIfNoAuthentication() {
		SecurityContext context = Mockito.mock(SecurityContext.class);
		SecurityContextHolder.setContext(context);
		Mockito.when(context.getAuthentication()).thenReturn(null);
		
		GameUser result = service.getCurrentUser();
		
		assertNull(result);
	}
	
	@Test
	public void testGetUserByUsernameOrEmailReturnsUsernameUserFirst() {
		GameUser user1 = new GameUser();
		GameUser user2 = new GameUser();
		Mockito.when(em.find(GameUser.class, username)).thenReturn(user1);
		Query query = Mockito.mock(Query.class);
		Mockito.when(em.createQuery(Mockito.anyString())).thenReturn(query);
		List<GameUser> resultList = Lists.newArrayList(user2);
		Mockito.when(query.getResultList()).thenReturn(resultList);
		
		GameUser result = service.getUserByUsernameOrEmail(username);
		
		assertEquals(user1, result);
	}
	
	@Test
	public void testGetUserByUsernameOrEmailReturnsEmailIfNoUsername() {
		GameUser user2 = new GameUser();
		Mockito.when(em.find(GameUser.class, username)).thenReturn(null);
		Query query = Mockito.mock(Query.class);
		Mockito.when(em.createQuery(Mockito.anyString())).thenReturn(query);
		List<GameUser> resultList = Lists.newArrayList(user2);
		Mockito.when(query.getResultList()).thenReturn(resultList);
		
		GameUser result = service.getUserByUsernameOrEmail(username);
		
		assertEquals(user2, result);
	}
	
	@Test
	public void testGetUserByUsernameOrEmailReturnsNullIfNotFound() {
		Mockito.when(em.find(GameUser.class, username)).thenReturn(null);
		Query query = Mockito.mock(Query.class);
		Mockito.when(em.createQuery(Mockito.anyString())).thenReturn(query);
		List<GameUser> resultList = Lists.newArrayList();
		Mockito.when(query.getResultList()).thenReturn(resultList);
		
		GameUser result = service.getUserByUsernameOrEmail(username);
		
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
	
	@Test
	public void testRemoveUserDeletesIfFound() {
		GameUser user = new GameUser();
		Mockito.when(em.find(GameUser.class, username)).thenReturn(user);
		
		service.removeUser(username);
		
		Mockito.verify(em).remove(user);
	}
	
	@Test
	public void testRemoveUserDoesNothingIfNotFound() {
		service.removeUser(username);
		
		Mockito.verify(em, Mockito.never()).remove(Mockito.any(GameUser.class));
	}
	
}
