package com.crowdgame.model;

import java.util.Random;

import com.google.common.collect.Lists;

public class DerivationProblem extends Problem {

	public DerivationProblem() {
		super();
	}
	
	public DerivationProblem(TaskInfo info) {
		super(info);
	}
	
	@Override
	public void generateProblem() {
		generateDisplayText();
		generateDisplayAnswers();
	}
	
	private void generateDisplayText() {
		this.displayText = Lists.newArrayList();
		
		for (int i = 0; i < this.word.length() && i < this.startIndex; ++i) {
			String letter = this.word.substring(i, i+1);
			this.displayText.add(letter);
		}
	}
	
	private void generateDisplayAnswers() {
		this.displayAnswers = Lists.newArrayList(this.answers);
		Random random = new Random();
		int position = random.nextInt(this.displayAnswers.size());
		this.displayAnswers.add(position, this.word.substring(this.startIndex, this.word.length()));
	}
}
