package com.crowdgame.model;

import java.util.List;

public class ProblemOutput {

private Integer id;
	
	private String type;
	
	protected String word;
	
	protected List<String> displayText;
	
	protected List<String> displayAnswers;
	
	public ProblemOutput() {
		
	}
	
	public ProblemOutput(Problem problem) {
		this.id = problem.getId();
		this.type = problem.getType();
		this.word = problem.getWord();
		this.displayText = problem.getDisplayText();
		this.displayAnswers = problem.getDisplayAnswers();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
