package com.crowdgame.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.crowdgame.model.ExecutionInfo;
import com.crowdgame.model.ExecutionResults;
import com.google.common.collect.Lists;

public class ExecutionInfoTest {

	private ExecutionInfo executionInfo;
	
	private static final Integer executionId = 300;
	private static final Integer executionTime = 500;
	private static final Integer executionAttempts = 3;
	private static final String executionWrongAnswer = "bla";
	private static final String expectedJSON = "{\"failedAttempts\":3,\"timeSpent\":500,\"wrongAnswers\":[\"bla\"]}";
	
	@Test
	public void testGenerateFromExecutionResults() {
		ExecutionResults results = new ExecutionResults();
		results.setId(executionId);
		results.setTimeSpent(executionTime);
		results.setFailedAttempts(executionAttempts);
		results.setWrongAnswers(Lists.newArrayList(executionWrongAnswer));
		
		executionInfo = new ExecutionInfo(results);
		
		assertEquals(executionId, executionInfo.getTaskId());
		assertEquals(expectedJSON, executionInfo.getContents());
	}
}
