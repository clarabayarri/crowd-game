package com.crowdgame.model;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.crowdgame.model.TaskInfo;



public class TaskInfoTest {

	private TaskInfo task = new TaskInfo();
	
	private static final String problemType = "insertion";
	private static final String problemWord = "palabra";
	private static final Integer problemStartIndex = 3;
	private static final Integer problemEndIndex = 3;
	private static final String problemAnswer1 = "a";
	private static final String taskContents = "{\"type\":\"insertion\",\"word\":\"palabra\", \"startIndex\":3, \"endIndex\":3, \"answers\":[\"a\", \"b\"]}";
	
	@Before
	public void setUp() {
		task.setContents(taskContents);
	}
	
	@Test
	public void testDecodeType() {
		String result = task.getType();
		assertEquals(result, problemType);
	}
	
	@Test
	public void testDecodeWord() {
		String result = task.getWord();
		assertEquals(result, problemWord);
	}
	
	@Test
	public void testDecodeStartIndex() {
		Integer result = task.getStartIndex();
		assertEquals(result, problemStartIndex);
	}
	
	@Test
	public void testDecodeEndIndex() {
		Integer result = task.getEndIndex();
		assertEquals(result, problemEndIndex);
	}
	
	@Test
	public void testDecodeAnswers() {
		List<String> result = task.getAnswers();
		assert(result.contains(problemAnswer1));
	}
}
