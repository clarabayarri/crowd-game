package com.crowdgame.model;

import java.util.Random;

import com.google.common.collect.Lists;

public class SubstitutionProblem extends Problem {

	public SubstitutionProblem() {
		super();
	}
	
	public SubstitutionProblem(TaskInput info) {
		super(info);
	}
	
	@Override
	public void generateProblem() {
		generateDisplayText();
		generateDisplayAnswers();
	}
	
	private void generateDisplayText() {
		this.displayText = Lists.newArrayList();
		int answerIndex = new Random().nextInt(this.answers.size());
		String insertedAnswer = this.answers.get(answerIndex);
		
		for (int i = 0; i < this.word.length(); ++i) {
			if (i != this.startIndex) {
				String letter = this.word.substring(i, i+1);
				this.displayText.add(letter);
			} else {
				this.displayText.add(insertedAnswer);
				i = this.endIndex;
			}
		}
	}
	
	private void generateDisplayAnswers() {
		this.displayAnswers = Lists.newArrayList(this.answers);
		Random random = new Random();
		int position = random.nextInt(this.displayAnswers.size());
		this.displayAnswers.add(position, this.word.substring(this.startIndex, this.endIndex + 1));
		this.displayAnswers.remove(displayText.get(this.startIndex));
	}
}
