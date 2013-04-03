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
import org.springframework.web.client.RestTemplate;

import com.crowdgame.model.Problem;
import com.crowdgame.model.ProblemCollection;
import com.crowdgame.model.TaskInput;

@RunWith(MockitoJUnitRunner.class)
public class ProblemServiceImplTest {

	@InjectMocks
	private ProblemServiceImpl service = new ProblemServiceImpl();
	
	@Mock
	private RestTemplate template;
	
	@Mock
	private ProblemCollectionService collectionService;
	
	@Before
	public void setUp() {
	    MockitoAnnotations.initMocks(this);
	    TaskInput info = new TaskInput();
		info.setContents("{\"answers\":[\"t\",\"e\",\"y\",\"h\"],\"id\":7,\"word\":\"trust\",\"level\":1,\"type\":\"insertion1\",\"language\":\"EN\",\"display\":\"trus_\"}");
		TaskInput[] data = {info};
		Mockito.when(template.getForObject(Mockito.anyString(), Mockito.eq(TaskInput[].class))).thenReturn(data);
		Mockito.when(collectionService.getCollection()).thenReturn(new ProblemCollection());
	}
	
	@Test
	public void testGetProblemCallsAPI() {
		service.getProblem();
	
		Mockito.verify(template).getForObject(Mockito.anyString(), Mockito.eq(TaskInput[].class));
	}
	
	@Test
	public void testGetProblemDoesntCallAPIIfProblemsRemaining() {
		ProblemCollection collection = new ProblemCollection();
		collection.addProblem(new Problem());
		Mockito.when(collectionService.getCollection()).thenReturn(collection);
		
		service.getProblem();
		
		Mockito.verifyZeroInteractions(template);
	}
	
	@Test
	public void testReturnedProblemContainsInfo() {
		Problem problem = service.getProblem();
		
		assertEquals(problem.getType(), "insertion1");
		assertEquals(problem.getWord(), "trust");
	}
}
