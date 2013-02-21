package com.crowdgame.factory;

import com.crowdgame.model.InsertionProblem;
import com.crowdgame.model.Problem;
import com.crowdgame.model.TaskInfo;

public class ProblemFactory {

	public Problem createProblem(TaskInfo info) {
		Problem problem = new InsertionProblem(info);
		problem.generateProblem();
		return problem;
	}
}
