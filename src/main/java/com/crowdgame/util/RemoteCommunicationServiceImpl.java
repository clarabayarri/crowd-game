package com.crowdgame.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.crowdgame.model.ExecutionInfo;
import com.crowdgame.model.ExecutionResults;
import com.crowdgame.model.GameUser;
import com.crowdgame.model.GameUserInfo;
import com.crowdgame.model.PlatformData;
import com.crowdgame.model.TaskInput;
import com.crowdgame.service.GameUserService;
import com.crowdgame.service.PlatformDataService;

@Service
public class RemoteCommunicationServiceImpl implements RemoteCommunicationService {

	private RestTemplate template = new RestTemplate();
	
	@Autowired
	private GameUserService userService;
	
	@Autowired
	private PlatformDataService dataService;

	@Override
	public TaskInput[] getTasks() {
		PlatformData data = dataService.getPlatformData();
		String taskGetURL = "http://gentle-gorge-9660.herokuapp.com/API/project/" + data.getProjectId() + "/uid/" + data.getUID() + "/task?count=10";
		return template.getForObject(taskGetURL, TaskInput[].class);
	}

	@Override
	public void postExecutionResults(ExecutionResults results) {
		ExecutionInfo executionInfo = new ExecutionInfo(results);
		GameUser user = userService.getCurrentUser();
		if (user != null) {
			if (user.getPlatformId() == null) {
				postGameUser(user);
			}
			executionInfo.setUserId(user.getPlatformId());
		}
		PlatformData data = dataService.getPlatformData();
		String executionPostURL = "http://gentle-gorge-9660.herokuapp.com/API/project/" + data.getProjectId() + "/uid/" + data.getUID() + "/execution";
		template.postForLocation(executionPostURL, executionInfo);
	}

	@Override
	public void postGameUser(GameUser user) {
		GameUserInfo gameUserInfo = new GameUserInfo(user);
		PlatformData data = dataService.getPlatformData();
		String createUserPostUrl = "http://gentle-gorge-9660.herokuapp.com/API/project/" + data.getProjectId() + "/uid/" + data.getUID() + "/user";
		Integer id = template.postForObject(createUserPostUrl, gameUserInfo, Integer.class);
		if (id != null && id > 0) {
			user.setPlatformId(id);
		}
	}

}
