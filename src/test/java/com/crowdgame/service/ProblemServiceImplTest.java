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
		info.setContents("{\"answers\":[\"t\",\"e\",\"y\",\"h\"],\"id\":7,\"word\":\"trust\",\"level\":1,\"type\":\"insertion1\",\"language\":\"EN\",\"display\":\"trus_\"}");
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
		
		assertEquals(problem.getType(), "insertion1");
		assertEquals(problem.getWord(), "trust");
	}
}
