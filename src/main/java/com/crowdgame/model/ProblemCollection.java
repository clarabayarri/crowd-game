package com.crowdgame.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.google.common.collect.Sets;

@Entity
public class ProblemCollection {

	@Id
	@GeneratedValue
	private Integer internalId;
	
	@OneToMany(fetch=FetchType.EAGER)
	@Cascade({CascadeType.ALL})
	private Set<Problem> problems;
	
	public ProblemCollection() {
		problems = Sets.newHashSet();
	}

	public Integer getInternalId() {
		return internalId;
	}

	public void setInternalId(Integer internalId) {
		this.internalId = internalId;
	}

	public Set<Problem> getProblems() {
		return problems;
	}

	public void setProblems(Set<Problem> problems) {
		this.problems = problems;
	}
	
	public void addProblem(Problem problem) {
		this.problems.add(problem);
	}
	
	public void removeProblem(Problem problem) {
		this.problems.remove(problem);
	}
	
	
}
