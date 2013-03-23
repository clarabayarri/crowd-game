package com.crowdgame.model;

import java.util.List;

import com.google.common.collect.Lists;

public class ExecutionResults {

	private Integer id;
	
	private Integer timeSpent;
	
	private Integer failedAttempts;
	
	private List<String> wrongAnswers;
	
	public ExecutionResults() {
		this.wrongAnswers = Lists.newArrayList();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
