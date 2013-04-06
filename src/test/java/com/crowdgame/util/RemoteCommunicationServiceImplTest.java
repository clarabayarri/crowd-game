package com.crowdgame.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import com.crowdgame.model.ExecutionInfo;
import com.crowdgame.model.ExecutionResults;
import com.crowdgame.model.GameUser;
import com.crowdgame.model.GameUserInfo;
import com.crowdgame.model.TaskInput;
import com.crowdgame.service.GameUserService;

@RunWith(MockitoJUnitRunner.class)
public class RemoteCommunicationServiceImplTest {

	@InjectMocks
	private RemoteCommunicationServiceImpl service = new RemoteCommunicationServiceImpl();
	
	@Mock
	private RestTemplate template;
	
	@Mock
	private GameUserService userService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetTasksExecutesGet() {
		service.getTasks();
		
		Mockito.verify(template).getForObject(RemoteCommunicationService.TASK_GET_URL, TaskInput[].class);
	}
	
	@Test
	public void testPostExecutionResultsExecutesPost() {
		ExecutionResults results = new ExecutionResults();
		results.setId(1);
		results.setFailedAttempts(0);
		results.setTimeSpent(100);
		
		service.postExecutionResults(results);
		
		Mockito.verify(template).postForLocation(Mockito.eq(RemoteCommunicationService.EXECUTION_POST_URL), 
				Mockito.any(ExecutionInfo.class));
	}
	
	@Test
	public void testPostExecutionResultsAssignsUserId() {
		ExecutionResults results = new ExecutionResults();
		results.setId(1);
		results.setFailedAttempts(0);
		results.setTimeSpent(100);
		GameUser user = Mockito.mock(GameUser.class);
		Mockito.when(userService.getCurrentUser()).thenReturn(user);
		
		service.postExecutionResults(results);
		
		Mockito.verify(user).getPlatformId();
	}
	
	@Test
	public void testPostGameUserExecutesPost() {
		GameUser user = new GameUser();
		user.setUsername("username");
		user.setDyslexic(true);
		
		service.postGameUser(user);
		
		Mockito.verify(template).postForObject(Mockito.eq(RemoteCommunicationService.CREATE_USER_POST_URL), 
				Mockito.any(GameUserInfo.class), Mockito.eq(Integer.class));
	}
	
	@Test
	public void testPostGameUserAssignsPlatformIdIfReceived() {
		GameUser user = new GameUser();
		user.setUsername("username");
		user.setDyslexic(true);
		Mockito.when(template.postForObject(Mockito.anyString(), Mockito.any(GameUserInfo.class), Mockito.eq(Integer.class))).thenReturn(1);
		
		service.postGameUser(user);
		
		int result = user.getPlatformId();
		assertEquals(1, result);
	}
	
	@Test
	public void testPostGameUserDoesntAssignPlatformIdIfZeroReceived() {
		GameUser user = new GameUser();
		user.setUsername("username");
		user.setDyslexic(true);
		Mockito.when(template.postForObject(Mockito.anyString(), Mockito.any(GameUserInfo.class), Mockito.eq(Integer.class))).thenReturn(0);
		
		service.postGameUser(user);
		
		assertNull(user.getPlatformId());
	}

}
