package com.crowdgame.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.crowdgame.model.ExecutionInfo;
import com.crowdgame.model.ExecutionResults;
import com.crowdgame.model.GameUser;
import com.crowdgame.model.GameUserInfo;
import com.crowdgame.model.PlatformData;
import com.crowdgame.model.TaskInput;
import com.crowdgame.model.TaskRequest;
import com.crowdgame.service.GameUserService;
import com.crowdgame.service.PlatformDataService;
import com.google.common.annotations.VisibleForTesting;

@Service
public class RemoteCommunicationServiceImpl implements RemoteCommunicationService {

	private RestTemplate template = new RestTemplate();
	
	@Autowired
	private GameUserService userService;
	
	@Autowired
	private PlatformDataService dataService;
	
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	@Override
	public TaskInput[] getTasks() {
		PlatformData data = dataService.getPlatformData();
		TaskRequest request = new TaskRequest();
		request.setProjectUid(data.getUID());
		request.setCount(10);
		String taskPostURL = "http://gentle-gorge-9660.herokuapp.com/API/project/" + data.getProjectId() + "/task";
		return template.postForObject(taskPostURL, request, TaskInput[].class);
	}

	@Override
	public void postExecutionResults(ExecutionResults results) {
		ExecutionInfo executionInfo = new ExecutionInfo(results);
		PlatformData data = dataService.getPlatformData();
		executionInfo.setProjectUid(data.getUID());
		GameUser user = userService.getCurrentUser();
		if (user != null) {
			if (user.getPlatformId() == null) {
				postGameUser(user);
			}
			executionInfo.setUserId(user.getPlatformId());
		}
		String executionPostURL = "http://gentle-gorge-9660.herokuapp.com/API/project/" + data.getProjectId() + "/execution";
		template.postForLocation(executionPostURL, executionInfo);
	}

	@Override
	public void postGameUser(GameUser user) {
		taskExecutor.execute(new GameUserPost(user));
	}
	
	@VisibleForTesting
	public GameUserPost getGameUserPostForTesting(GameUser user) {
		return new GameUserPost(user);
	}
	
	public class GameUserPost implements Runnable {

		private GameUser user;
		
		public GameUserPost(GameUser user) {
			this.user = user;
		}
		
		@Override
		public void run() {
			GameUserInfo gameUserInfo = new GameUserInfo(user);
			PlatformData data = dataService.getPlatformData();
			gameUserInfo.setProjectUid(data.getUID());
			String createUserPostUrl = "http://gentle-gorge-9660.herokuapp.com/API/project/" + data.getProjectId() + "/user";
			Integer id = template.postForObject(createUserPostUrl, gameUserInfo, Integer.class);
			if (id != null && id > 0) {
				// Retrieve User again in case there have been updates
				user = userService.getUser(user.getUsername());
				user.setPlatformId(id);
				userService.saveGameUser(user);
			}
		}
		
	}

}
