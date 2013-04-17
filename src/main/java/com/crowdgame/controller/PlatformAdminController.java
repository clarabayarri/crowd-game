package com.crowdgame.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crowdgame.model.PlatformData;
import com.crowdgame.service.PlatformDataService;

@Controller
public class PlatformAdminController {

	@Autowired
	private PlatformDataService dataService;
	
	@RequestMapping("/admin")
	public String loadAdminPanel(Model model) {
		PlatformData data = dataService.getPlatformData();
		model.addAttribute(data);
		return "admin";
	}
	
	@RequestMapping(value={"/admin/save"}, method=RequestMethod.POST)
	public String saveAdmin(@Valid PlatformData data, BindingResult bindingResult) {
		dataService.savePlatformData(data);
		return "redirect:/admin";
	}
}
