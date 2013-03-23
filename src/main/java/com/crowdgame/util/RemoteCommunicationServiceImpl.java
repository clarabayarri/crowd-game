package com.crowdgame.util;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.crowdgame.model.ExecutionInfo;
import com.crowdgame.model.ExecutionResults;
import com.crowdgame.model.GameUser;
import com.crowdgame.model.GameUserInfo;

@Service
public class RemoteCommunicationServiceImpl implements RemoteCommunicationService {

	private RestTemplate template;

	public void setTemplate(RestTemplate template) {
		this.template = template;
	}

	private RestTemplate getTemplate() {
		if (this.template == null) this.template = new RestTemplate();
		return template;
	}

	@Override
	public void postExecutionResults(ExecutionResults results) {
		ExecutionInfo executionInfo = new ExecutionInfo(results);
		getTemplate().postForLocation(EXECUTION_POST_URL, executionInfo);
	}

	@Override
	public void postGameUser(GameUser user) {
		GameUserInfo gameUserInfo = new GameUserInfo(user);
		getTemplate().postForLocation(USER_POST_URL, gameUserInfo);
		// TODO: check for errors in creation
	}

}
