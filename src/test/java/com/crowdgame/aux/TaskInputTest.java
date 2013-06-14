package com.crowdgame.aux;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class TaskInputTest {

	private TaskInput input = new TaskInput();
	
	private static final String type = "insertion";
	private static final String word = "word";
	private static final Integer problemId = 4;
	private static final Integer level = 4;
	private static final String language = "ES";
	private static final String displayText = "display text";
	private static final List<String> answers = Lists.newArrayList();
	
	@Test
	public void testGetTypeReadsFromContents() {
		Map<String, Object> contents = Maps.newHashMap();
		contents.put("type", type);
		input.setContents(contents);
		
		String result = input.getType();
		
		assertEquals(type, result);
	}
	
	@Test
	public void testGetWordReadsFromContents() {
		Map<String, Object> contents = Maps.newHashMap();
		contents.put("word", word);
		input.setContents(contents);
		
		String result = input.getWord();
		
		assertEquals(word, result);
	}
	
	@Test
	public void testGetProblemIdReadsFromContents() {
		Map<String, Object> contents = Maps.newHashMap();
		contents.put("id", problemId);
		input.setContents(contents);
		
		Integer result = input.getProblemId();
		
		assertEquals(problemId, result);
	}
	
	@Test
	public void testGetLevelReadsFromContents() {
		Map<String, Object> contents = Maps.newHashMap();
		contents.put("level", level);
		input.setContents(contents);
		
		Integer result = input.getLevel();
		
		assertEquals(level, result);
	}
	
	@Test
	public void testGetLanguageReadsFromContents() {
		Map<String, Object> contents = Maps.newHashMap();
		contents.put("language", language);
		input.setContents(contents);
		
		String result = input.getLanguage();
		
		assertEquals(language, result);
	}
	
	@Test
	public void testGetDisplayTextReadsFromContents() {
		Map<String, Object> contents = Maps.newHashMap();
		contents.put("display", displayText);
		input.setContents(contents);
		
		String result = input.getDisplayText();
		
		assertEquals(displayText, result);
	}
	
	@Test
	public void testGetAnswersReadsFromContents() {
		Map<String, Object> contents = Maps.newHashMap();
		contents.put("answers", answers);
		input.setContents(contents);
		
		List<String> result = input.getAnswers();
		
		assertEquals(answers, result);
	}
}
