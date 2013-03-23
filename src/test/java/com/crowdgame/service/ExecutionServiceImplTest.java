package com.crowdgame.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.crowdgame.model.ExecutionResults;
import com.crowdgame.util.RemoteCommunicationService;

@RunWith(MockitoJUnitRunner.class)
public class ExecutionServiceImplTest {

	@InjectMocks
	private ExecutionServiceImpl service = new ExecutionServiceImpl();

	@Mock
	private RemoteCommunicationService remoteService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testSaveExecutionResultsCallsRemoteService() {
		ExecutionResults results = new ExecutionResults();
		
		service.saveExecutionResults(results);
		
		Mockito.verify(remoteService).postExecutionResults(results);
	}
}
