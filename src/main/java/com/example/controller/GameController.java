package com.example.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GameController {

	@RequestMapping("/game/")
	public String showGame(Map<String, Object> map) {
		return "game";
	}
	
}
