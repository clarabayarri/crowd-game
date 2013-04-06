package com.crowdgame.model;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

public class GameUserInfo {
	
	private String contents;
	
	public GameUserInfo(GameUser user) {
		this.contents = encodeContents(user);
	}
	
	private String encodeContents(GameUser user) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		node.put("dyslexic", user.isDyslexic());
		return node.toString();
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

}
