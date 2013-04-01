package com.crowdgame.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.crowdgame.model.Problem;
import com.crowdgame.model.ProblemCollection;
import com.crowdgame.model.TaskInput;
import com.google.common.collect.Lists;

@Service
public class ProblemServiceImpl implements ProblemService {

	private RestTemplate template;
	private static final Integer projectId = 1;
	
	@Autowired
	private ProblemCollectionService collectionService;
	
	private RestTemplate getTemplate() {
		if (this.template == null) this.template = new RestTemplate();
		return template;
	}
	
	public Problem getProblem() {
		ProblemCollection collection = collectionService.getCollection();
		if (collection.getProblems().size() == 0) {
			retrieveMoreProblems(collection);
		}
		
		Random random = new Random();
		List<Problem> problems = Lists.newArrayList(collection.getProblems());
		int index = random.nextInt(problems.size());
		collection.removeProblem(problems.get(index));
		return problems.get(index);
	}
	
	private void retrieveMoreProblems(ProblemCollection collection) {
		TaskInput[] tasks = getTemplate().getForObject("http://gentle-gorge-9660.herokuapp.com/API/project/" + projectId + "/task?count=10", TaskInput[].class);
		for (TaskInput task : tasks) {
			Problem problem = new Problem(task);
			collection.addProblem(problem);
		}
		
		collectionService.saveCollection(collection);
	}
	
}
