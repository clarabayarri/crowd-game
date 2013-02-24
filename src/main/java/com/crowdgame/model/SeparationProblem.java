package com.crowdgame.model;

import com.google.common.collect.Lists;

public class SeparationProblem extends Problem {

	public SeparationProblem() {
		super();
	}
	
	public SeparationProblem(TaskInfo info) {
		super(info);
	}
	
	@Override
	public void generateProblem() {
		this.displayText = Lists.newArrayList();
		
		for (int i = 0; i < this.word.length(); ++i) {
			String letter = this.word.substring(i, i+1);
			if (!" ".equals(letter)) {
				this.displayText.add(letter);
			}
		}
	}
}
