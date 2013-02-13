package com.example.service;

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

import com.example.model.Problem;
import com.example.model.TaskInfo;

@RunWith(MockitoJUnitRunner.class)
public class ProblemServiceImplTest {

	@InjectMocks
	private ProblemServiceImpl service = new ProblemServiceImpl();
	
	@Mock
	private RestTemplate template;
	
	@Before
	public void setUp() {
	    MockitoAnnotations.initMocks(this);
	    TaskInfo info = new TaskInfo();
		info.setContents("{\"type\":\"insertion\",\"word\":\"palabra\", \"startIndex\":3, \"endIndex\":3, \"answers\":[\"a\", \"b\"]}");
		Mockito.when(template.getForObject(Mockito.anyString(), Mockito.eq(TaskInfo.class))).thenReturn(info);
	}
	
	@Test
	public void testGetProblemCallsAPI() {
		service.getProblem();
	
		Mockito.verify(template).getForObject(Mockito.anyString(), Mockito.eq(TaskInfo.class));
	}
	
	@Test
	public void testReturnedProblemContainsInfo() {
		Problem problem = service.getProblem();
		
		assertEquals(problem.getType(), "insertion");
		assertEquals(problem.getWord(), "palabra");
	}
}
