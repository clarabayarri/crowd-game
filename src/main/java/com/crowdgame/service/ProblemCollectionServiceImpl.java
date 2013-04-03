package com.crowdgame.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crowdgame.model.Problem;
import com.crowdgame.model.ProblemCollection;

@Service
public class ProblemCollectionServiceImpl implements ProblemCollectionService {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public ProblemCollection getCollection() {
		Query query = em.createQuery("FROM ProblemCollection");
		if (query.getResultList().isEmpty()) {
			return createCollection();
		}
		return (ProblemCollection) query.getResultList().get(0);
	}
	
	@Transactional
	private ProblemCollection createCollection() {
		ProblemCollection collection = new ProblemCollection();
		em.persist(collection);
		return collection;
	}

	@Override
	@Transactional
	public void saveCollection(ProblemCollection collection) {
		em.merge(collection);
	}
	
	@Transactional
	public void removeProblemFromCollection(ProblemCollection collection, Problem problem) {
		collection.removeProblem(problem);
		em.merge(collection);
		Problem retrievedProblem = em.find(Problem.class, problem.getId());
		em.remove(retrievedProblem);
	}

}
