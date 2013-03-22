package com.crowdgame.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GameControllerTest {

	private GameController controller = new GameController();
	
	@Test
	public void testHandleRequestView() {
		String result = controller.loadGame();
		assertEquals("game", result);
	}
}
