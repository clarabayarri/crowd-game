package com.crowdgame.util;

import com.crowdgame.model.ExecutionResults;
import com.crowdgame.model.GameUser;

public interface RemoteCommunicationService {
	
	public static final String EXECUTION_POST_URL = "http://gentle-gorge-9660.herokuapp.com/API/execution";
	
	public static final String USER_POST_URL = "http://gentle-gorge-9660.herokuapp.com/API/user";
	
	public void postExecutionResults(ExecutionResults results);
	
	public void postGameUser(GameUser user);

}
