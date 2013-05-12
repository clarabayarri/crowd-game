package com.crowdgame.service;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.crowdgame.model.Problem;
import com.crowdgame.model.ProblemCollection;
import com.crowdgame.model.TaskInput;
import com.crowdgame.util.RemoteCommunicationService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@RunWith(MockitoJUnitRunner.class)
public class ProblemServiceImplTest {

	@InjectMocks
	private ProblemServiceImpl service = new ProblemServiceImpl();
	
	@Mock
	private ProblemCollectionService collectionService;
	
	@Mock
	private RemoteCommunicationService remoteService;
	
	@Before
	public void setUp() {
	    MockitoAnnotations.initMocks(this);
	    TaskInput info = new TaskInput();
	    Map<String, Object> taskContents = Maps.newHashMap();
	    taskContents.put("id", 7);
		taskContents.put("answers", Lists.newArrayList("t", "e", "y", "h"));
		taskContents.put("word", "trust");
		taskContents.put("level", 1);
		taskContents.put("type", "insertion1");
		taskContents.put("language", "EN");
		taskContents.put("display", "trus_");
		info.setContents(taskContents);
		TaskInput[] data = {info};
		Mockito.when(remoteService.getTasks()).thenReturn(data);
		Mockito.when(collectionService.getCollection()).thenReturn(new ProblemCollection());
	}
	
	@Test
	public void testGetProblemCallsRemoteService() {
		service.getProblem();
	
		Mockito.verify(remoteService).getTasks();
	}
	
	@Test
	public void testGetProblemDoesntCallAPIIfProblemsRemaining() {
		ProblemCollection collection = new ProblemCollection();
		collection.addProblem(new Problem());
		Mockito.when(collectionService.getCollection()).thenReturn(collection);
		
		service.getProblem();
		
		Mockito.verifyZeroInteractions(remoteService);
	}
	
	@Test
	public void testReturnedProblemContainsInfo() {
		Problem problem = service.getProblem();
		
		assertEquals(problem.getType(), "insertion1");
		assertEquals(problem.getWord(), "trust");
	}
}
