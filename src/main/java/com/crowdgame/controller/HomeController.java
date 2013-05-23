package com.crowdgame.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value={"/", "/home"})
	public String loadHome() {
		return "index";
	}
	
	@RequestMapping(value={"/favicon.ico"})
	public String findIcon() {
		return "forward:/resources/img/favicon.ico";
	}

}
