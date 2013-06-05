package com.crowdgame.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.crowdgame.aux.ExecutionInfo;
import com.crowdgame.aux.ExecutionResults;
import com.google.common.collect.Lists;

public class ExecutionInfoTest {

	private ExecutionInfo executionInfo;
	
	private static final Integer executionTaskId = 300;
	private static final Integer executionTime = 500;
	private static final Integer executionAttempts = 3;
	private static final String executionWrongAnswer = "bla";
	
	@Test
	public void testGenerateFromExecutionResults() {
		ExecutionResults results = new ExecutionResults();
		results.setTaskId(executionTaskId);
		results.setTimeSpent(executionTime);
		results.setFailedAttempts(executionAttempts);
		results.setWrongAnswers(Lists.newArrayList(executionWrongAnswer));
		
		executionInfo = new ExecutionInfo(results);
		
		assertEquals(executionTaskId, executionInfo.getTaskId());
	}
}
