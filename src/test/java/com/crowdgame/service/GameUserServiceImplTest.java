package com.crowdgame.service;

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
	
}
