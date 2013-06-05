package com.crowdgame.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.crowdgame.aux.ExecutionInfo;
import com.crowdgame.aux.ExecutionResults;
import com.crowdgame.aux.GameUserInfo;
import com.crowdgame.aux.TaskInput;
import com.crowdgame.aux.TaskRequest;
import com.crowdgame.model.GameUser;
import com.crowdgame.model.PlatformData;
import com.crowdgame.model.Problem;
import com.crowdgame.model.ProblemCollection;
import com.google.common.annotations.VisibleForTesting;

@Service
public class RemoteCommunicationServiceImpl implements RemoteCommunicationService {

	private RestTemplate template = new RestTemplate();
	
	@Autowired
	private GameUserService userService;
	
	@Autowired
	private PlatformDataService dataService;
	
	@Autowired
	private ProblemCollectionService collectionService;
	
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	@Override
	public void addTasksToProblemCollection() {
		taskExecutor.execute(new PostForTasks());
	}
	
	@VisibleForTesting
	public PostForTasks getPostForTasksForTesting() {
		return new PostForTasks();
	}
	
	public class PostForTasks implements Runnable {

		@Override
		public void run() {
			PlatformData data = dataService.getPlatformData();
			TaskRequest request = new TaskRequest();
			request.setProjectUid(data.getUID());
			request.setCount(30);
			String taskPostURL = "http://gentle-gorge-9660.herokuapp.com/API/project/" + data.getProjectId() + "/task";
			TaskInput[] tasks = template.postForObject(taskPostURL, request, TaskInput[].class);
			if (tasks.length > 0) {
				ProblemCollection newCollection = collectionService.getCollection();
				for (TaskInput task : tasks) {
					Problem problem = new Problem(task);
					newCollection.addProblem(problem);
				}
				
				collectionService.saveCollection(newCollection);
				System.out.println("Received " + tasks.length + " tasks.");
			}
		}
		
	}

	@Override
	public void postExecutionResults(ExecutionResults results) {
		// Check if it is a real task, not backup
		if (results.getBatchId() != null) {
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
