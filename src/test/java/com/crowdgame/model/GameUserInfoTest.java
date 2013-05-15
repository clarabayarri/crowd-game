package com.crowdgame.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GameUserInfoTest {

	private GameUserInfo gameUserInfo;
	
	private static final boolean dyslexic = true;

	@Test
	public void testGenerateFromGameUser() {
		GameUser user = new GameUser();
		user.setDyslexic(dyslexic);
		
		gameUserInfo = new GameUserInfo(user);
		
		assertEquals(dyslexic, gameUserInfo.getContents().get("dyslexic"));
	}
}
