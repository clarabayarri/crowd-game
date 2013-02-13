package com.example.model;

import java.util.List;

public class Problem {

	private Integer id;
	
	private String type;
	
	private String word;
	
	private Integer startIndex;
	
	private Integer endIndex;
	
	private List<String> answers;
	
	private List<String> displayText;
	
	private List<String> displayAnswers;
	
	public Problem() {
		
	}
	
	public Problem(TaskInfo task) {
		this.id = task.getId();
		this.type = task.getType();
		this.word = task.getWord();
		this.startIndex = task.getStartIndex();
		this.endIndex = task.getEndIndex();
		this.answers = task.getAnswers();
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

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
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
