package com.example.controller;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest {

	private GameController controller = new GameController();
	
	@Test
	public void testHandleRequestView() {
		String result = controller.showGame(new HashMap<String, Object>());
		assertEquals("game", result);
	}
}
