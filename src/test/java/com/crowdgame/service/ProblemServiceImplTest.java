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
import org.springframework.web.client.RestTemplate;

import com.crowdgame.model.Problem;
import com.crowdgame.model.TaskInput;
import com.crowdgame.service.ProblemServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProblemServiceImplTest {

	@InjectMocks
	private ProblemServiceImpl service = new ProblemServiceImpl();
	
	@Mock
	private RestTemplate template;
	
	@Before
	public void setUp() {
	    MockitoAnnotations.initMocks(this);
	    TaskInput info = new TaskInput();
		info.setContents("{\"type\":\"insertion\",\"word\":\"palabra\", \"startIndex\":3, \"endIndex\":3, \"answers\":[\"a\", \"b\"]}");
		Mockito.when(template.getForObject(Mockito.anyString(), Mockito.eq(TaskInput.class))).thenReturn(info);
	}
	
	@Test
	public void testGetProblemCallsAPI() {
		service.getProblem();
	
		Mockito.verify(template).getForObject(Mockito.anyString(), Mockito.eq(TaskInput.class));
	}
	
	@Test
	public void testReturnedProblemContainsInfo() {
		Problem problem = service.getProblem();
		
		assertEquals(problem.getType(), "insertion");
		assertEquals(problem.getWord(), "palabra");
	}
}
