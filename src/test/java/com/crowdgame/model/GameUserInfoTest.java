package com.crowdgame.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GameUserInfoTest {

	private GameUserInfo gameUserInfo;
	
	private static final boolean dyslexic = true;
	private static final String expectedJSON = "{\"dyslexic\":true}";

	@Test
	public void testGenerateFromGameUser() {
		GameUser user = new GameUser();
		user.setDyslexic(dyslexic);
		
		gameUserInfo = new GameUserInfo(user);
		
		assertEquals(expectedJSON, gameUserInfo.getContents());
	}
}
