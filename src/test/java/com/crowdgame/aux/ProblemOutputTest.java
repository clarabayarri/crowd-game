package com.crowdgame.aux;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.crowdgame.model.Problem;
import com.google.common.collect.Lists;

public class ProblemOutputTest {

	private ProblemOutput output;
	
	private static final Integer batchId = 44;
	private static final Integer taskId = 87;
	private static final String type = "insertion";
	private static final String word = "word";
	private static final List<String> displayText = Lists.newArrayList();
	private static final List<String> displayAnswers = Lists.newArrayList();
	
	@Test
	public void testCreateFromProblem() {
		Problem problem = new Problem();
		problem.setBatchId(batchId);
		problem.setTaskId(taskId);
		problem.setType(type);
		problem.setWord(word);
		problem.setDisplayText(displayText);
		problem.setAnswers(displayAnswers);
		
		output = new ProblemOutput(problem);
		
		assertEquals(batchId, output.getBatchId());
		assertEquals(taskId, output.getTaskId());
		assertEquals(type, output.getType());
		assertEquals(word, output.getWord());
		assertEquals(displayText, output.getDisplayText());
		assertEquals(displayAnswers, output.getDisplayAnswers());
	}
}
