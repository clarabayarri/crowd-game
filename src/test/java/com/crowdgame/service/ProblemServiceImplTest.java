package com.crowdgame.service;

import static org.junit.Assert.assertEquals;

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
import com.crowdgame.util.RemoteCommunicationService;

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
	}
	
	@Test
	public void testGetProblemCallsRemoteService() {
		ProblemCollection collection = new ProblemCollection();
	    collection.addProblem(new Problem());
	    Mockito.when(collectionService.getCollection()).thenReturn(collection);
		
		service.getProblem();
	
		Mockito.verify(remoteService).addTasksToProblemCollection();
	}
	
	@Test
	public void testGetProblemGivesProblemFromMainCollection() {
		ProblemCollection collection = new ProblemCollection();
	    Problem problem = new Problem();
	    collection.addProblem(problem);
	    Mockito.when(collectionService.getCollection()).thenReturn(collection);
		
		Problem result = service.getProblem();
		
		assertEquals(problem, result);
	}
	
	@Test
	public void testGetProblemFallsToBackupIfNoProblems() {
		Mockito.when(collectionService.getCollection()).thenReturn(new ProblemCollection());
	    ProblemCollection collection = new ProblemCollection();
	    Problem problem = new Problem();
	    collection.addProblem(problem);
	    Mockito.when(collectionService.getBackupCollection()).thenReturn(collection);
		
		Problem result = service.getProblem();
		
		assertEquals(problem, result);
	}
	
	@Test
	public void testGetProblemDoesntCallAPIIfProblemsRemaining() {
		ProblemCollection collection = new ProblemCollection();
		collection.addProblem(new Problem());
		collection.addProblem(new Problem());
		collection.addProblem(new Problem());
		Mockito.when(collectionService.getCollection()).thenReturn(collection);
		
		service.getProblem();
		
		Mockito.verifyZeroInteractions(remoteService);
	}
}
