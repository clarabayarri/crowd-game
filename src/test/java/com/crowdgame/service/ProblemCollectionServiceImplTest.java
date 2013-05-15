package com.crowdgame.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;

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
		Mockito.when(em.find(ProblemCollection.class, 1)).thenReturn(collection);
		
		ProblemCollection result = service.getCollection();
		
		assertSame(collection, result);
	}
	
	@Test
	public void testGetCollectionCreatesCollectionIfNone() {
		ProblemCollection result = service.getCollection();
		
		assertNotNull(result);
	}
	
	@Test
	public void testGetBackupCollection() {
		Mockito.when(em.find(ProblemCollection.class, 2)).thenReturn(collection);
		
		ProblemCollection result = service.getBackupCollection();
		
		assertSame(collection, result);
	}
	
	@Test
	public void testGetBackupCollectionCreatesCollectionIfNone() {
		ProblemCollection result = service.getBackupCollection();
		
		assertNotNull(result);
		assertTrue(!result.getProblems().isEmpty());
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
