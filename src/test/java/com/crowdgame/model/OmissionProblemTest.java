package com.crowdgame.model;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

public class OmissionProblemTest {

	private OmissionProblem problem = new OmissionProblem();
	
	private static final String problemWord = "palabra";
	private static final Integer problemStartIndex = 3;
	private static final Integer problemEndIndex = 3;
	private static final String problemAnswer1 = "e";
	private static final List<String> expectedDisplayText = 
			Lists.newArrayList("p", "a", "l", "e", "a", "b", "r", "a");
	
	@Before
	public void setUp() {
		problem.setWord(problemWord);
		problem.setStartIndex(problemStartIndex);
		problem.setEndIndex(problemEndIndex);
		problem.setAnswers(Lists.newArrayList(problemAnswer1));
	}
	
	@Test
	public void testProblemDisplayTextGeneration() {
		problem.generateProblem();
		
		List<String> result = problem.getDisplayText();
		
		assertEquals(result.size(), expectedDisplayText.size());
		for (int i = 0; i < result.size(); ++i) {
			assertEquals(result.get(i), expectedDisplayText.get(i));
		}
	}

}
