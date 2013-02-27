package com.crowdgame.model;

import com.google.common.collect.Lists;

public class OmissionProblem extends Problem {

	public OmissionProblem() {
		super();
	}
	
	public OmissionProblem(TaskInput info) {
		super(info);
	}
	
	@Override
	public void generateProblem() {
		this.displayText = Lists.newArrayList();
		
		for (int i = 0; i < this.word.length(); ++i) {
			String letter = this.word.substring(i, i+1);
			this.displayText.add(letter);
		}
		
		this.displayText.add(this.startIndex, this.answers.get(0));
	}
}
