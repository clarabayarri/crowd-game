package com.crowdgame.util;

import com.crowdgame.model.ExecutionResults;
import com.crowdgame.model.GameUser;
import com.crowdgame.model.TaskInput;

public interface RemoteCommunicationService {
	
	public static final String TASK_GET_URL = "http://gentle-gorge-9660.herokuapp.com/API/project/1/uid/2959672552433317606/task?count=10";
	
	public static final String EXECUTION_POST_URL = "http://gentle-gorge-9660.herokuapp.com/API/project/1/uid/2959672552433317606/execution";
	
	public static final String CREATE_USER_POST_URL = "http://gentle-gorge-9660.herokuapp.com/API/project/1/uid/2959672552433317606/user";
	
	public TaskInput[] getTasks();
	
	public void postExecutionResults(ExecutionResults results);
	
	public void postGameUser(GameUser user);

}
