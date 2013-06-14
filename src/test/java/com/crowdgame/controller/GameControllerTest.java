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
	public void testLoadGameHandleRequestView() {
		Model model = Mockito.mock(Model.class);
		
		String result = controller.loadGame(model);
		
		assertEquals("game", result);
	}
	
	@Test
	public void testLoadGameAddsCurrentUserToModel() {
		Model model = Mockito.mock(Model.class);
		GameUser user = new GameUser();
		Mockito.when(userService.getCurrentUser()).thenReturn(user);
		
		controller.loadGame(model);
		
		Mockito.verify(model).addAttribute("user", user);
	}
	
	@Test
	public void testGetTask() {
		Mockito.when(problemService.getProblem()).thenReturn(new Problem());
		
		controller.getTask();
		
		Mockito.verify(problemService).getProblem();
	}
	
	@Test
	public void testSaveExecutionSavesExecution() {
		ExecutionResults results = new ExecutionResults();
		results.setTimeSpent(300);
		results.setFailedAttempts(0);
		GameUser user = new GameUser();
		Mockito.when(userService.getCurrentUser()).thenReturn(user);
		
		controller.saveExecution(results);
		
		Mockito.verify(executionService).saveExecutionResults(results);
	}
	
	@Test
	public void testSaveExecutionDoesntSaveExecutionIfSuspiciousTimeData() {
		ExecutionResults results = new ExecutionResults();
		results.setTimeSpent(-300);
		results.setFailedAttempts(0);
		GameUser user = new GameUser();
		Mockito.when(userService.getCurrentUser()).thenReturn(user);
		
		controller.saveExecution(results);
		
		Mockito.verify(executionService, Mockito.never()).saveExecutionResults(results);
	}
	
	@Test
	public void testSaveExecutionDoesntSaveExecutionIfSuspiciousTimeData2() {
		ExecutionResults results = new ExecutionResults();
		results.setTimeSpent(GameController.MAX_TIME + 1);
		results.setFailedAttempts(0);
		GameUser user = new GameUser();
		Mockito.when(userService.getCurrentUser()).thenReturn(user);
		
		controller.saveExecution(results);
		
		Mockito.verify(executionService, Mockito.never()).saveExecutionResults(results);
	}
	
	@Test
	public void testSaveExecutionDoesntSaveExecutionIfSuspiciousAnswerData() {
		ExecutionResults results = new ExecutionResults();
		results.setTimeSpent(456);
		results.setFailedAttempts(1);
		GameUser user = new GameUser();
		Mockito.when(userService.getCurrentUser()).thenReturn(user);
		
		controller.saveExecution(results);
		
		Mockito.verify(executionService, Mockito.never()).saveExecutionResults(results);
	}
	
	@Test
	public void testSaveExecutionIncreasesUserScore() {
		ExecutionResults results = new ExecutionResults();
		results.setTimeSpent(300);
		results.setFailedAttempts(0);
		GameUser user = new GameUser();
		user.setScore(33);
		Mockito.when(userService.getCurrentUser()).thenReturn(user);
		
		int result = controller.saveExecution(results);
		
		assertEquals(33 + 1, result);
		assertEquals(33 + 1, user.getScore().intValue());
	}
}
