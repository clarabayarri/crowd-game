package com.example.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.model.ExecutionResults;
import com.example.model.Problem;
import com.example.service.ExecutionService;
import com.example.service.ProblemService;

@Controller
@RequestMapping("/game")
public class GameController {

	@Autowired
	private ProblemService problemService;
	
	@Autowired
	private ExecutionService executionService;
	
	@RequestMapping("/")
	public String showGame(Map<String, Object> map) {
		return "game";
	}
	
	@RequestMapping("/problem")
	public @ResponseBody Problem getTask() {
		return problemService.getProblem();
	}
	
	@RequestMapping(value = "/results")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void saveExecution(@RequestBody ExecutionResults execution) {
		executionService.saveExecutionResults(execution);
	}
}
