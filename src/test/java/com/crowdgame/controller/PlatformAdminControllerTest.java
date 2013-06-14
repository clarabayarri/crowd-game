package com.crowdgame.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.crowdgame.model.PlatformData;
import com.crowdgame.service.PlatformDataService;

@RunWith(MockitoJUnitRunner.class)
public class PlatformAdminControllerTest {

	@InjectMocks
	private PlatformAdminController controller = new PlatformAdminController();
	
	@Mock
	private PlatformDataService dataService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testLoadAdminPanelHandleRequestView() {
		Model model = Mockito.mock(Model.class);
		
		String result = controller.loadAdminPanel(model);
		
		assertEquals("admin", result);
	}
	
	@Test
	public void testLoadAdminPanelAddsPlatformDataToModel() {
		Model model = Mockito.mock(Model.class);
		PlatformData data = new PlatformData();
		Mockito.when(dataService.getPlatformData()).thenReturn(data);
		
		controller.loadAdminPanel(model);
		
		Mockito.verify(model).addAttribute(data);
	}
	
	@Test
	public void testSaveAdminHandleRequestView() {
		PlatformData data = new PlatformData();
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		
		String result = controller.saveAdmin(data, bindingResult);
		
		assertEquals("redirect:/admin", result);
	}
	
	@Test
	public void testSaveAdminSavesData() {
		PlatformData data = new PlatformData();
		BindingResult bindingResult = Mockito.mock(BindingResult.class);
		
		controller.saveAdmin(data, bindingResult);
		
		Mockito.verify(dataService).savePlatformData(data);
	}
}
