package com.crowdgame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crowdgame.aux.ExecutionResults;

@Service
public class ExecutionServiceImpl implements ExecutionService {

	@Autowired
	private RemoteCommunicationService remoteService;

	public void saveExecutionResults(ExecutionResults results) {
		remoteService.postExecutionResults(results);
	}

}
