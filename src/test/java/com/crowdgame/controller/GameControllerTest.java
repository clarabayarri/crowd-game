package com.crowdgame.controller;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.crowdgame.controller.GameController;

@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {

	private GameController controller = new GameController();
	
	@Test
	public void testHandleRequestView() {
		String result = controller.showGame(new HashMap<String, Object>());
		assertEquals("game", result);
	}
}
