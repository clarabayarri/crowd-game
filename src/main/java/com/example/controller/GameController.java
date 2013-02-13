package com.example.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.example.model.ExecutionInfo;
import com.example.model.Problem;
import com.example.service.ProblemService;

@Controller
@RequestMapping("/game")
public class GameController {

	@Autowired
	private ProblemService problemService;
	
	@RequestMapping("/")
	public String showGame(Map<String, Object> map) {
		return "game";
	}
	
	@RequestMapping("/problem")
	public @ResponseBody Problem getTask() {
		return problemService.getProblem();
	}
	
	public void saveExecution(ExecutionInfo execution) {
		new RestTemplate().postForLocation("http://gentle-gorge-9660.herokuapp.com/API/execution", execution);
	}
	
}
