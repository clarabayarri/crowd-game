package com.example.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.model.ExecutionInfo;
import com.example.model.ExecutionResults;

@Service
public class ExecutionServiceImpl implements ExecutionService {

	private RestTemplate template;

	public void setTemplate(RestTemplate template) {
		this.template = template;
	}

	private RestTemplate getTemplate() {
		if (this.template == null) this.template = new RestTemplate();
		return template;
	}

	@Override
	public void saveExecutionResults(ExecutionResults results) {
		ExecutionInfo executionInfo = new ExecutionInfo(results);
		System.out.println(executionInfo);
		getTemplate().postForLocation("http://gentle-gorge-9660.herokuapp.com/API/execution", executionInfo);

	}

}
