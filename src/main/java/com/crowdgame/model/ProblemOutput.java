package com.crowdgame.model;

import java.util.List;

public class ProblemOutput {

	private Integer batchId;
	
	private Integer taskId;
	
	private String type;
	
	protected String word;
	
	protected List<String> displayText;
	
	protected List<String> displayAnswers;
	
	public ProblemOutput() {
		
	}
	
	public ProblemOutput(Problem problem) {
		this.batchId = problem.getBatchId();
		this.taskId = problem.getTaskId();
		this.type = problem.getType();
		this.word = problem.getWord();
		this.displayText = problem.getDisplayText();
		this.displayAnswers = problem.getAnswers();
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public List<String> getDisplayText() {
		return displayText;
	}

	public void setDisplayText(List<String> displayText) {
		this.displayText = displayText;
	}

	public List<String> getDisplayAnswers() {
		return displayAnswers;
	}

	public void setDisplayAnswers(List<String> displayAnswers) {
		this.displayAnswers = displayAnswers;
	}
}
