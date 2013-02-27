package com.crowdgame.factory;

import com.crowdgame.model.DerivationProblem;
import com.crowdgame.model.InsertionProblem;
import com.crowdgame.model.OmissionProblem;
import com.crowdgame.model.Problem;
import com.crowdgame.model.SeparationProblem;
import com.crowdgame.model.SubstitutionProblem;
import com.crowdgame.model.TaskInput;

public class ProblemFactory {

	public Problem createProblem(TaskInput info) {
		Problem problem;
		if ("insertion".equals(info.getType())) {
			problem = new InsertionProblem(info);
		} else if ("omission".equals(info.getType())) {
			problem = new OmissionProblem(info);
		} else if ("substitution".equals(info.getType())) {
			problem = new SubstitutionProblem(info);
		} else if ("derivation".equals(info.getType())) {
			problem = new DerivationProblem(info);
		} else if ("separation".equals(info.getType())) {
			problem = new SeparationProblem(info);
		} else {
			return null;
		}
		problem.generateProblem();
		return problem;
	}
}
