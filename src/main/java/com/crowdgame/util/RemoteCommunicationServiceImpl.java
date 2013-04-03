package com.crowdgame.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.crowdgame.model.ExecutionInfo;
import com.crowdgame.model.ExecutionResults;
import com.crowdgame.model.GameUser;
import com.crowdgame.model.GameUserInfo;
import com.crowdgame.service.GameUserService;

@Service
public class RemoteCommunicationServiceImpl implements RemoteCommunicationService {

	private RestTemplate template;
	
	@Autowired
	private GameUserService userService;

	public void setTemplate(RestTemplate template) {
		this.template = template;
	}

	public RestTemplate getTemplate() {
		if (this.template == null) {
			this.template = new RestTemplate();
		}
		return template;
	}

	@Override
	public void postExecutionResults(ExecutionResults results) {
		ExecutionInfo executionInfo = new ExecutionInfo(results);
		GameUser user = userService.getCurrentUser();
		if (user != null) {
			executionInfo.setUserId(user.getPlatformId());
			System.out.println(executionInfo.getUserId() + " " + user.getUsername());
		}
		getTemplate().postForLocation(EXECUTION_POST_URL, executionInfo);
	}

	@Override
	public void postGameUser(GameUser user) {
		GameUserInfo gameUserInfo = new GameUserInfo(user);
		Integer id = getTemplate().postForObject(USER_POST_URL, gameUserInfo, Integer.class);
		System.out.println(id);
		if (id != null && id > 0) {
			user.setPlatformId(id);
		}
		// TODO: check for errors in creation
	}

}
