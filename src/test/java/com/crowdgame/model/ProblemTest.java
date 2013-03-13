package com.crowdgame.model;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class ProblemTest {

	private Problem problem = new Problem();
	
	private static final String simpleWord = "hello";
	private static final String[] simpleWordParts = {"h", "e", "l", "l", "o"};
	
	private static final String separatedWord = "he|ll|o";
	private static final String[] separatedWordParts = {"h", "e", "ll", "o"};
	
	private static final String syllableWord = "[he|ll|o]";
	private static final String[] syllableWordParts = {"he", "ll", "o"};
	
	@Test
	public void testSimpleGeneration() {
		problem.setDisplay(simpleWord);
		
		List<String> result = problem.getDisplayText();
		
		assertEquals(simpleWordParts.length, result.size());
		for (int i = 0; i < result.size(); ++i) {
			assertEquals(simpleWordParts[i], result.get(i));
		}
	}
	
	@Test
	public void testSeparatedGeneration() {
		problem.setDisplay(separatedWord);
		
		List<String> result = problem.getDisplayText();
		
		assertEquals(separatedWordParts.length, result.size());
		for (int i = 0; i < result.size(); ++i) {
			assertEquals(separatedWordParts[i], result.get(i));
		}
	}
	
	@Test
	public void testSyllableGeneration() {
		problem.setDisplay(syllableWord);
		
		List<String> result = problem.getDisplayText();
		
		assertEquals(syllableWordParts.length, result.size());
		for (int i = 0; i < result.size(); ++i) {
			assertEquals(syllableWordParts[i], result.get(i));
		}
	}

}
