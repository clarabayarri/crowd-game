package com.example.factory;

import com.example.model.InsertionProblem;
import com.example.model.Problem;
import com.example.model.TaskInfo;

public class ProblemFactory {

	public Problem createProblem(TaskInfo info) {
		Problem problem = new InsertionProblem(info);
		problem.generateProblem();
		return problem;
	}
}
