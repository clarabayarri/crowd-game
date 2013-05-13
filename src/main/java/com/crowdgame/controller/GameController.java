package com.crowdgame.controller;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.crowdgame.model.ExecutionResults;
import com.crowdgame.model.GameUser;
import com.crowdgame.model.Problem;
import com.crowdgame.model.ProblemOutput;
import com.crowdgame.service.ExecutionService;
import com.crowdgame.service.GameUserService;
import com.crowdgame.service.ProblemService;

@Controller
public class GameController {

	@Autowired
	private ProblemService problemService;
	
	@Autowired
	private ExecutionService executionService;
	
	@Autowired
	private GameUserService userService;
	
	@RequestMapping("/game")
	public String loadGame(Model model) {
		GameUser user = userService.getCurrentUser();
		model.addAttribute("score", user.getScore());
		return "game";
	}
	
	@RequestMapping("/game/problem")
	public @ResponseBody ProblemOutput getTask() {
		Problem problem = problemService.getProblem();
		return new ProblemOutput(problem);
	}
	
	@RequestMapping(value = "/game/results")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void saveExecution(@RequestBody ExecutionResults execution) {
		executionService.saveExecutionResults(execution);
		GameUser user = userService.getCurrentUser();
		user.increaseScore(1);
		userService.saveGameUser(user);
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	ErrorMessage handleException(MethodArgumentNotValidException ex) {
	    List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
	    List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
	    List<String> errors = new ArrayList<String>(fieldErrors.size() + globalErrors.size());
	    String error;
	    for (FieldError fieldError : fieldErrors) {
	        error = fieldError.getField() + ", " + fieldError.getDefaultMessage();
	        errors.add(error);
	    }
	    for (ObjectError objectError : globalErrors) {
	        error = objectError.getObjectName() + ", " + objectError.getDefaultMessage();
	        errors.add(error);
	    }
	    return new ErrorMessage(errors);
	}
	
	@XmlRootElement
	public class ErrorMessage {
	 
	    private List<String> errors;
	 
	    public ErrorMessage() {
	    }
	 
	    public ErrorMessage(List<String> errors) {
	        this.errors = errors;
	    }
	 
	    public List<String> getErrors() {
	        return errors;
	    }
	 
	    public void setErrors(List<String> errors) {
	        this.errors = errors;
	    }
	}
}
