package com.crowdgame.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.crowdgame.factory.ProblemFactory;
import com.crowdgame.model.Problem;
import com.crowdgame.model.TaskInfo;

@Service
public class ProblemServiceImpl implements ProblemService {

	private RestTemplate template;
	
	private ProblemFactory factory;
	
	public void setTemplate(RestTemplate template) {
		this.template = template;
	}
	
	private RestTemplate getTemplate() {
		if (this.template == null) this.template = new RestTemplate();
		return template;
	}
	
	public void setFactory(ProblemFactory factory) {
		this.factory = factory;
	}
	
	public ProblemFactory getFactory() {
		if (this.factory == null) this.factory = new ProblemFactory();
		return factory;
	}
	
	public Problem getProblem() {
		TaskInfo task = getTemplate().getForObject("http://gentle-gorge-9660.herokuapp.com/API/task", TaskInfo.class);
		return getFactory().createProblem(task);
	}
	
}
