package com.example.model;

import java.util.Random;

import com.google.common.collect.Lists;

public class InsertionProblem extends Problem {

	public InsertionProblem() {
		super();
	}
	
	public InsertionProblem(TaskInfo info) {
		super(info);
	}

	@Override
	public void generateProblem() {
		generateDisplayText();
		generateDisplayAnswers();
	}
	
	private void generateDisplayText() {
		this.displayText = Lists.newArrayList();
		
		for (int i = 0; i < this.word.length(); ++i) {
			if (i != this.startIndex) {
				String letter = this.word.substring(i, i+1);
				this.displayText.add(letter);
			} else {
				this.displayText.add(" ");
				i = this.endIndex;
			}
		}
	}
	
	private void generateDisplayAnswers() {
		this.displayAnswers = Lists.newArrayList(this.answers);
		Random random = new Random();
		int position = random.nextInt(this.displayAnswers.size());
		this.displayAnswers.add(position, this.word.substring(this.startIndex, this.endIndex + 1));
	}

}
