package com.crowdgame.model;

import java.util.Map;

import com.google.common.collect.Maps;

public class GameUserInfo {
	
	private Long projectUid;
	
	private Map<String, Object> contents;
	
	public GameUserInfo(GameUser user) {
		this.contents = encodeContents(user);
	}
	
	public Long getProjectUid() {
		return projectUid;
	}

	public void setProjectUid(Long projectUid) {
		this.projectUid = projectUid;
	}

	private Map<String, Object> encodeContents(GameUser user) {
		Map<String, Object> result = Maps.newHashMap();
		result.put("dyslexic", user.isDyslexic());
		result.put("age", user.getAge());
		result.put("spanishSpeaker", user.isSpanishSpeaker());
		return result;
	}

	public Map<String, Object> getContents() {
		return contents;
	}

	public void setContents(Map<String, Object> contents) {
		this.contents = contents;
	}

}
