package com.crowdgame.service;

import com.crowdgame.model.ProblemCollection;

public interface ProblemCollectionService {

	public ProblemCollection getCollection();
	
	public void saveCollection(ProblemCollection collection);
	
}
