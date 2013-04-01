package com.crowdgame.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.crowdgame.model.ExecutionResults;
import com.crowdgame.model.Problem;
import com.crowdgame.service.ExecutionService;
import com.crowdgame.service.ProblemService;

@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {

	@InjectMocks
	private GameController controller = new GameController();
	
	@Mock
	private ProblemService problemService;
	
	@Mock
	private ExecutionService executionService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testHandleRequestView() {
		String result = controller.loadGame();
		assertEquals("game", result);
	}
	
	@Test
	public void testGetTask() {
		Mockito.when(problemService.getProblem()).thenReturn(new Problem());
		
		controller.getTask();
		
		Mockito.verify(problemService).getProblem();
	}
	
	@Test
	public void testSaveExecution() {
		ExecutionResults results = new ExecutionResults();
		
		controller.saveExecution(results);
		
		Mockito.verify(executionService).saveExecutionResults(results);
	}
}
