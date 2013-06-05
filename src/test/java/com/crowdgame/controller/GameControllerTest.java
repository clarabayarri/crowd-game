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
import org.springframework.ui.Model;

import com.crowdgame.aux.ExecutionResults;
import com.crowdgame.model.GameUser;
import com.crowdgame.model.Problem;
import com.crowdgame.service.ExecutionService;
import com.crowdgame.service.GameUserService;
import com.crowdgame.service.ProblemService;

@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {

	@InjectMocks
	private GameController controller = new GameController();
	
	@Mock
	private ProblemService problemService;
	
	@Mock
	private ExecutionService executionService;
	
	@Mock
	private GameUserService userService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testHandleRequestView() {
		Model model = Mockito.mock(Model.class);
		Mockito.when(userService.getCurrentUser()).thenReturn(new GameUser());
		
		String result = controller.loadGame(model);
		
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
		results.setTimeSpent(300);
		results.setFailedAttempts(0);
		Mockito.when(userService.getCurrentUser()).thenReturn(new GameUser());
		
		controller.saveExecution(results);
		
		Mockito.verify(executionService).saveExecutionResults(results);
	}
}
