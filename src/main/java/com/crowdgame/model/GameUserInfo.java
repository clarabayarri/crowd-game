package com.crowdgame.model;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

public class GameUserInfo {

	private String username;
	
	private String contents;
	
	public GameUserInfo(GameUser user) {
		this.username = user.getUsername();
		this.contents = encodeContents(user);
	}
	
	private String encodeContents(GameUser user) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		node.put("dyslexic", user.isDyslexic());
		return node.toString();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

}
