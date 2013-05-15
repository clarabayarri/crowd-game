package com.crowdgame.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import com.crowdgame.model.ExecutionInfo;
import com.crowdgame.model.ExecutionResults;
import com.crowdgame.model.GameUser;
import com.crowdgame.model.GameUserInfo;
import com.crowdgame.model.PlatformData;
import com.crowdgame.model.ProblemCollection;
import com.crowdgame.model.TaskInput;
import com.crowdgame.service.GameUserService;
import com.crowdgame.service.PlatformDataService;
import com.crowdgame.service.ProblemCollectionService;
import com.crowdgame.util.RemoteCommunicationServiceImpl.GameUserPost;
import com.crowdgame.util.RemoteCommunicationServiceImpl.PostForTasks;
import com.google.common.collect.Maps;

@RunWith(MockitoJUnitRunner.class)
public class RemoteCommunicationServiceImplTest {

	@InjectMocks
	private RemoteCommunicationServiceImpl service = new RemoteCommunicationServiceImpl();
	
	@Mock
	private RestTemplate template;
	
	@Mock
	private GameUserService userService;
	
	@Mock
	private PlatformDataService dataService;
	
	@Mock
	private ProblemCollectionService collectionService;
	
	@Mock
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		Mockito.when(dataService.getPlatformData()).thenReturn(new PlatformData());
	}
	
	@Test
	public void testAddTasksCallsTaskExecutor() {
		service.addTasksToProblemCollection();
		
		Mockito.verify(taskExecutor).execute(Mockito.any(Runnable.class));
	}
	
	@Test
	public void testAddTasksExecutesPost() {
		PostForTasks post = service.getPostForTasksForTesting();
		Mockito.when(template.postForObject(Mockito.anyString(), Mockito.any(), Mockito.eq(TaskInput[].class))).thenReturn(new TaskInput[0]);
		
		post.run();
		
		Mockito.verify(template).postForObject(Mockito.anyString(), Mockito.any(), Mockito.eq(TaskInput[].class));
	}
	
	@Test
	public void testAddTasksSavesReceivedTasksToCollection() {
		PostForTasks post = service.getPostForTasksForTesting();
		TaskInput info = getTestTaskInput();
		Mockito.when(template.postForObject(Mockito.anyString(), Mockito.any(), Mockito.eq(TaskInput[].class))).thenReturn(new TaskInput[] {info});
		ProblemCollection collection = new ProblemCollection();
		Mockito.when(collectionService.getCollection()).thenReturn(collection);
		
		post.run();
		
		assertEquals(1, collection.getProblems().size());
		Mockito.verify(collectionService).saveCollection(collection);
	}
	
	private TaskInput getTestTaskInput() {
		TaskInput result = new TaskInput();
		Map<String, Object> contents = Maps.newHashMap();
		contents.put("word", "hola");
		contents.put("display", "h_la");
		result.setContents(contents);
		return result;
	}
	
	@Test
	public void testPostExecutionResultsDoesntExecutePostIfBackup() {
		ExecutionResults results = new ExecutionResults();
		results.setFailedAttempts(0);
		results.setTimeSpent(100);
		
		service.postExecutionResults(results);
		
		Mockito.verify(template, Mockito.never()).postForLocation(Mockito.anyString(), 
				Mockito.any(ExecutionInfo.class));
	}
	
	@Test
	public void testPostExecutionResultsExecutesPost() {
		ExecutionResults results = new ExecutionResults();
		results.setBatchId(3);
		results.setTaskId(1);
		results.setFailedAttempts(0);
		results.setTimeSpent(100);
		
		service.postExecutionResults(results);
		
		Mockito.verify(template).postForLocation(Mockito.anyString(), 
				Mockito.any(ExecutionInfo.class));
	}
	
	@Test
	public void testPostExecutionResultsAssignsUserId() {
		ExecutionResults results = new ExecutionResults();
		results.setBatchId(3);
		results.setTaskId(1);
		results.setFailedAttempts(0);
		results.setTimeSpent(100);
		GameUser user = Mockito.mock(GameUser.class);
		Mockito.when(userService.getCurrentUser()).thenReturn(user);
		Mockito.when(user.getPlatformId()).thenReturn(1);
		
		service.postExecutionResults(results);
		
		Mockito.verify(user, Mockito.atLeastOnce()).getPlatformId();
	}
	
	@Test
	public void testPostGameUserCallsTaskExecutor() {
		service.postGameUser(new GameUser());
		
		Mockito.verify(taskExecutor).execute(Mockito.any(Runnable.class));
	}
	
	@Test
	public void testPostGameUserExecutesPost() {
		GameUser user = new GameUser();
		user.setUsername("username");
		user.setDyslexic(true);
		GameUserPost post = service.getGameUserPostForTesting(user);
		
		post.run();
		
		Mockito.verify(template).postForObject(Mockito.anyString(), 
				Mockito.any(GameUserInfo.class), Mockito.eq(Integer.class));
	}
	
	@Test
	public void testPostGameUserAssignsPlatformIdIfReceived() {
		GameUser user = new GameUser();
		user.setUsername("username");
		user.setDyslexic(true);
		Mockito.when(userService.getUser("username")).thenReturn(user);
		Mockito.when(template.postForObject(Mockito.anyString(), Mockito.any(GameUserInfo.class), Mockito.eq(Integer.class))).thenReturn(1);
		GameUserPost post = service.getGameUserPostForTesting(user);
		
		post.run();
		
		int result = user.getPlatformId();
		assertEquals(1, result);
	}
	
	@Test
	public void testPostGameUserDoesntAssignPlatformIdIfZeroReceived() {
		GameUser user = new GameUser();
		user.setUsername("username");
		user.setDyslexic(true);
		Mockito.when(template.postForObject(Mockito.anyString(), Mockito.any(GameUserInfo.class), Mockito.eq(Integer.class))).thenReturn(0);
		GameUserPost post = service.getGameUserPostForTesting(user);
		
		post.run();
		
		assertNull(user.getPlatformId());
	}

}
