package com.crowdgame.service;

import com.crowdgame.model.Problem;
import com.crowdgame.model.ProblemCollection;

public interface ProblemCollectionService {

	public ProblemCollection getCollection();
	
	public ProblemCollection getBackupCollection();
	
	public void saveCollection(ProblemCollection collection);
	
	public void removeProblemFromCollection(ProblemCollection collection, Problem problem);
	
}
