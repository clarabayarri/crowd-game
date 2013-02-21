package com.crowdgame.model;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


public class TaskInfo {

	private Integer id;
	
	private String contents;
	
	private TaskInfoDecoded decoded;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
		decodeContents();
	}
	
	public void decodeContents() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			decoded = mapper.readValue(contents, TaskInfoDecoded.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getType() {
		return decoded.getType();
	}

	public String getWord() {
		return decoded.getWord();
	}

	public Integer getStartIndex() {
		return decoded.getStartIndex();
	}

	public Integer getEndIndex() {
		return decoded.getEndIndex();
	}

	public List<String> getAnswers() {
		return decoded.getAnswers();
	}
	
	public static class TaskInfoDecoded {
		
		private String type;
		
		private String word;
		
		private Integer startIndex;
		
		private Integer endIndex;
		
		private List<String> answers;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getWord() {
			return word;
		}

		public void setWord(String word) {
			this.word = word;
		}

		public Integer getStartIndex() {
			return startIndex;
		}

		public void setStartIndex(Integer startIndex) {
			this.startIndex = startIndex;
		}

		public Integer getEndIndex() {
			return endIndex;
		}

		public void setEndIndex(Integer endIndex) {
			this.endIndex = endIndex;
		}

		public List<String> getAnswers() {
			return answers;
		}

		public void setAnswers(List<String> answers) {
			this.answers = answers;
		}
		
		
	}
}
