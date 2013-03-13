package com.crowdgame.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.crowdgame.model.Problem;
import com.crowdgame.model.TaskInput;

@Service
public class ProblemServiceImpl implements ProblemService {

	private RestTemplate template;
	
	public void setTemplate(RestTemplate template) {
		this.template = template;
	}
	
	private RestTemplate getTemplate() {
		if (this.template == null) this.template = new RestTemplate();
		return template;
	}
	
	public Problem getProblem() {
		TaskInput task = getTemplate().getForObject("http://gentle-gorge-9660.herokuapp.com/API/task", TaskInput.class);
		return new Problem(task);
	}
	
}
