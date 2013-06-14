package com.crowdgame.aux;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.crowdgame.model.GameUser;

public class GameUserInfoTest {

	private GameUserInfo userInfo;
	
	private static final Boolean dyslexic = true;
	private static final Boolean spanishSpeaker = true;
	private static final Integer age = 48;
	
	@Test
	public void testCreateFromGameUser() {
		GameUser user = new GameUser();
		user.setDyslexic(dyslexic);
		user.setSpanishSpeaker(spanishSpeaker);
		user.setAge(age);
		
		userInfo = new GameUserInfo(user);
		
		assertNotNull(userInfo.getContents());
		assertEquals(dyslexic, userInfo.getContents().get("dyslexic"));
		assertEquals(spanishSpeaker, userInfo.getContents().get("spanishSpeaker"));
		assertEquals(age, userInfo.getContents().get("age"));
	}
}
