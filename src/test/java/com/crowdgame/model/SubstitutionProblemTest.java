package com.crowdgame.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

public class SubstitutionProblemTest {

	private SubstitutionProblem problem = new SubstitutionProblem();
	
	private static final String problemWord = "palabra";
	private static final Integer problemStartIndex = 3;
	private static final Integer problemEndIndex = 3;
	private static final String problemAnswer1 = "e";
	private static final String problemAnswer2 = "r";
	private static final List<String> answers = Lists.newArrayList(problemAnswer1, problemAnswer2);
	private static final List<String> expectedDisplayText = 
			Lists.newArrayList("p", "a", "l", " ", "b", "r", "a");
	private static final List<String> expectedDisplayAnswers = 
			Lists.newArrayList("a", problemAnswer1, problemAnswer2);
	
	@Before
	public void setUp() {
		problem.setWord(problemWord);
		problem.setStartIndex(problemStartIndex);
		problem.setEndIndex(problemEndIndex);
		problem.setAnswers(answers);
	}
	
	@Test
	public void testProblemDisplayTextGeneration() {
		problem.generateProblem();
		
		List<String> result = problem.getDisplayText();
		
		assertEquals(result.size(), expectedDisplayText.size());
		for (int i = 0; i < result.size(); ++i) {
			if (i != problemStartIndex) {
				assertEquals(result.get(i), expectedDisplayText.get(i));
			}
		}
		
		assertTrue(answers.contains(result.get(problemStartIndex)));
	}
	
	@Test
	public void testProblemDisplayAnswersGeneration() {
		problem.generateProblem();
		
		List<String> word = problem.getDisplayText();
		List<String> result = problem.getDisplayAnswers();
		
		assertEquals(result.size(), expectedDisplayAnswers.size() - 1);
		assertTrue(expectedDisplayAnswers.containsAll(result));
		assertTrue(!result.contains(word.get(problemStartIndex)));
	}
	
}
