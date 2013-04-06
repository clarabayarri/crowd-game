package com.crowdgame.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.crowdgame.model.ExecutionInfo;
import com.crowdgame.model.ExecutionResults;
import com.crowdgame.model.GameUser;
import com.crowdgame.model.GameUserInfo;
import com.crowdgame.model.TaskInput;
import com.crowdgame.service.GameUserService;

@Service
public class RemoteCommunicationServiceImpl implements RemoteCommunicationService {

	private RestTemplate template = new RestTemplate();
	
	@Autowired
	private GameUserService userService;

	@Override
	public TaskInput[] getTasks() {
		return template.getForObject(TASK_GET_URL, TaskInput[].class);
	}

	@Override
	public void postExecutionResults(ExecutionResults results) {
		ExecutionInfo executionInfo = new ExecutionInfo(results);
		GameUser user = userService.getCurrentUser();
		if (user != null) {
			executionInfo.setUserId(user.getPlatformId());
		}
		template.postForLocation(EXECUTION_POST_URL, executionInfo);
	}

	@Override
	public void postGameUser(GameUser user) {
		GameUserInfo gameUserInfo = new GameUserInfo(user);
		Integer id = template.postForObject(CREATE_USER_POST_URL, gameUserInfo, Integer.class);
		if (id != null && id > 0) {
			user.setPlatformId(id);
		}
	}

}
