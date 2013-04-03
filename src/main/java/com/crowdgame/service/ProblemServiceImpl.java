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

	private RestTemplate template = new RestTemplate();
	private static final String PROBLEM_URL = "http://gentle-gorge-9660.herokuapp.com/API/project/1/task?count=10";
	
	@Autowired
	private ProblemCollectionService collectionService;
	
	public Problem getProblem() {
		ProblemCollection collection = collectionService.getCollection();
		if (collection.getProblems().size() == 0) {
			retrieveMoreProblems(collection);
		}
		
		Random random = new Random();
		List<Problem> problems = Lists.newArrayList(collection.getProblems());
		int index = random.nextInt(problems.size());
		Problem problem = problems.get(index);
		collectionService.removeProblemFromCollection(collection, problem);
		return problem;
	}
	
	private void retrieveMoreProblems(ProblemCollection collection) {
		TaskInput[] tasks = template.getForObject(PROBLEM_URL, TaskInput[].class);
		for (TaskInput task : tasks) {
			Problem problem = new Problem(task);
			collection.addProblem(problem);
		}
		
		collectionService.saveCollection(collection);
	}
	
}
