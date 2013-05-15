package com.crowdgame.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crowdgame.model.Problem;
import com.crowdgame.model.ProblemCollection;
import com.google.common.collect.Lists;

@Service
public class ProblemCollectionServiceImpl implements ProblemCollectionService {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional
	public ProblemCollection getCollection() {
		ProblemCollection result = em.find(ProblemCollection.class, 1);
		if (result == null) {
			result = new ProblemCollection();
			result.setInternalId(1);
		}
		return result;
	}
	
	@Override
	@Transactional
	public ProblemCollection getBackupCollection() {
		ProblemCollection result = em.find(ProblemCollection.class, 2);
		if (result == null) {
			result = createBackupCollection();
			result.setInternalId(2);
		}
		return result;
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
	
	private ProblemCollection createBackupCollection() {
		List<String> emptyList = Lists.newArrayList();
		ProblemCollection result = new ProblemCollection();
		result.setInternalId(2);
		result.addProblem(new Problem("insertion", "noche", "noce", Lists.newArrayList("h", "n", "i")));
		result.addProblem(new Problem("insertion", "ahora", "aora", Lists.newArrayList("h", "a")));
		result.addProblem(new Problem("insertion", "domingo", "domigo", Lists.newArrayList("n", "m", "r", "e", "s")));
		result.addProblem(new Problem("insertion", "trimestre", "timestre", Lists.newArrayList("r", "s", "n", "m", "p")));
		result.addProblem(new Problem("insertion", "contrarreloj", "contrareloj", Lists.newArrayList("r", "s", "l", "g", "j")));
		result.addProblem(new Problem("insertion1", "jueves", "ju_ves", Lists.newArrayList("e", "a", "o", "n")));
		result.addProblem(new Problem("insertion1", "estar", "e_tar", Lists.newArrayList("s", "r", "m", "n", "i")));
		result.addProblem(new Problem("omission", "jueves", "juerves", emptyList));
		result.addProblem(new Problem("omission", "humano", "hummano", emptyList));
		result.addProblem(new Problem("omission", "supuesto", "supuerto", emptyList));
		result.addProblem(new Problem("omission", "trabajado", "trasbajado", emptyList));
		result.addProblem(new Problem("separation", "de la", "dela", emptyList));
		result.addProblem(new Problem("separation", "cara bonita", "carabonita", emptyList));
		result.addProblem(new Problem("separation", "de la ley", "delaley", emptyList));
		result.addProblem(new Problem("substitution", "majestad", "majestat", Lists.newArrayList("d", "z", "s", "n")));
		result.addProblem(new Problem("substitution", "miércoles", "miéscoles", Lists.newArrayList("r", "n", "d", "s", "l")));
		result.addProblem(new Problem("substitution", "literales", "literates", Lists.newArrayList("g", "j", "l", "f", "r")));
		return result;
	}

}
