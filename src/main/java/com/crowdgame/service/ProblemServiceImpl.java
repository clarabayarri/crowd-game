package com.crowdgame.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crowdgame.model.Problem;
import com.crowdgame.model.ProblemCollection;
import com.crowdgame.model.TaskInput;
import com.crowdgame.util.RemoteCommunicationService;
import com.google.common.collect.Lists;

@Service
public class ProblemServiceImpl implements ProblemService {

	@Autowired
	private ProblemCollectionService collectionService;

	@Autowired
	private RemoteCommunicationService remoteService;
	
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
		TaskInput[] tasks = remoteService.getTasks();
		for (TaskInput task : tasks) {
			Problem problem = new Problem(task);
			collection.addProblem(problem);
		}
		
		collectionService.saveCollection(collection);
	}
	
}
