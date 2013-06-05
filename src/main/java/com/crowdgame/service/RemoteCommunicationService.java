package com.crowdgame.service;

import com.crowdgame.aux.ExecutionResults;
import com.crowdgame.model.GameUser;

public interface RemoteCommunicationService {
	
	public void addTasksToProblemCollection();
	
	public void postExecutionResults(ExecutionResults results);
	
	public void postGameUser(GameUser user);

}
