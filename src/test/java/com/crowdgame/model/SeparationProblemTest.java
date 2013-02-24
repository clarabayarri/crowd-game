package com.crowdgame.model;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

public class SeparationProblemTest {

	private SeparationProblem problem = new SeparationProblem();
	
	private static final String problemWord = "palabra con espacios";
	private static final List<String> expectedDisplayText = 
			Lists.newArrayList("p", "a", "l", "a", "b", "r", "a", "c", "o", "n", "e", "s", "p", "a", "c", "i", "o", "s");
	
	@Before
	public void setUp() {
		problem.setWord(problemWord);
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
