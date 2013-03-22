package com.crowdgame.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DashboardControllerTest {

	private DashboardController controller = new DashboardController();
	
	@Test
	public void testHandleRequestView() {
		String result = controller.loadDashboard();
		assertEquals("dashboard", result);
	}

}
