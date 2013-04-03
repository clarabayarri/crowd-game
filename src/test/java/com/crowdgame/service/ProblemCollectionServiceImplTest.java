package com.crowdgame.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.crowdgame.model.Problem;
import com.crowdgame.model.ProblemCollection;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@RunWith(MockitoJUnitRunner.class)
public class ProblemCollectionServiceImplTest {

	@InjectMocks
	private ProblemCollectionServiceImpl service = new ProblemCollectionServiceImpl();
	
	@Mock
	private EntityManager em;
	
	private ProblemCollection collection = new ProblemCollection();
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetCollection() {
		Query query = Mockito.mock(Query.class);
		Mockito.when(query.getResultList()).thenReturn(Lists.newArrayList(collection));
		Mockito.when(em.createQuery("FROM ProblemCollection")).thenReturn(query);
		
		ProblemCollection result = service.getCollection();
		
		Mockito.verify(query, Mockito.times(2)).getResultList();
		assertSame(collection, result);
	}
	
	@Test
	public void testGetCollectionCreatesCollectionIfNone() {
		Query query = Mockito.mock(Query.class);
		Mockito.when(query.getResultList()).thenReturn(Lists.newArrayList());
		Mockito.when(em.createQuery("FROM ProblemCollection")).thenReturn(query);
		
		ProblemCollection result = service.getCollection();
		
		Mockito.verify(em).persist(Mockito.any(ProblemCollection.class));
		assertNotNull(result);
	}
	
	@Test
	public void testSaveCollection() {
		service.saveCollection(collection);
		
		Mockito.verify(em).merge(collection);
	}
	
	@Test
	public void testRemoveProblemFromCollection() {
		ProblemCollection collection = new ProblemCollection();
		Problem problem = new Problem();
		problem.setId(1);
		collection.setProblems(Sets.newHashSet(new Problem(), new Problem(), problem));
		Mockito.when(em.find(Problem.class, 1)).thenReturn(problem);
	
		service.removeProblemFromCollection(collection, problem);
		
		assertEquals(2, collection.getProblems().size());
		Mockito.verify(em).merge(collection);
		Mockito.verify(em).remove(problem);
	}
}
