package com.crowdgame.model;

import java.util.List;

import com.google.common.collect.Lists;

public class ExecutionResults {
	
	private Integer batchId;

	private Integer taskId;
	
	private Integer timeSpent;
	
	private Integer failedAttempts;
	
	private List<String> wrongAnswers;
	
	public ExecutionResults() {
		this.wrongAnswers = Lists.newArrayList();
	}

	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getTimeSpent() {
		return timeSpent;
	}

	public void setTimeSpent(Integer timeSpent) {
		this.timeSpent = timeSpent;
	}

	public Integer getFailedAttempts() {
		return failedAttempts;
	}

	public void setFailedAttempts(Integer failedAttempts) {
		this.failedAttempts = failedAttempts;
	}

	public List<String> getWrongAnswers() {
		return wrongAnswers;
	}

	public void setWrongAnswers(List<String> wrongAnswers) {
		this.wrongAnswers = wrongAnswers;
	}
	
	
}
