package com.crowdgame.aux;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

public class ExecutionInfoTest {

	private ExecutionInfo executionInfo;
	
	private static final Integer batchId = 3;
	private static final Integer taskId = 44;
	private static final Integer timeSpent = 439;
	private static final Integer failedAttempts = 3;
	private static final List<String> wrongAnswers = Lists.newArrayList();
	
	@Test
	public void testCreateFromExecutionResults() {
		ExecutionResults results = new ExecutionResults();
		results.setBatchId(batchId);
		results.setTaskId(taskId);
		results.setTimeSpent(timeSpent);
		results.setFailedAttempts(failedAttempts);
		results.setWrongAnswers(wrongAnswers);
		
		executionInfo = new ExecutionInfo(results);
		
		assertEquals(batchId, executionInfo.getBatchId());
		assertEquals(taskId, executionInfo.getTaskId());
		assertNotNull(executionInfo.getContents());
	}
	
	@Test
	public void testCreateFromExecutionResultsEncodesContents() {
		ExecutionResults results = new ExecutionResults();
		results.setBatchId(batchId);
		results.setTaskId(taskId);
		results.setTimeSpent(timeSpent);
		results.setFailedAttempts(failedAttempts);
		results.setWrongAnswers(wrongAnswers);
		
		executionInfo = new ExecutionInfo(results);
		
		assertEquals(timeSpent, executionInfo.getContents().get("timeSpent"));
		assertEquals(failedAttempts, executionInfo.getContents().get("failedAttempts"));
		assertEquals(wrongAnswers, executionInfo.getContents().get("wrongAnswers"));
	}
}
