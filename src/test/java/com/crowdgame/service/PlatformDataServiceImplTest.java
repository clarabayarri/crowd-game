package com.crowdgame.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.crowdgame.model.PlatformData;

@RunWith(MockitoJUnitRunner.class)
public class PlatformDataServiceImplTest {

	@InjectMocks
	private PlatformDataServiceImpl service = new PlatformDataServiceImpl();
	
	@Mock
	private EntityManager em;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetPlatformDataRetrievesData() {
		Mockito.when(em.find(PlatformData.class, PlatformDataServiceImpl.platformDataId))
			.thenReturn(new PlatformData());
		
		service.getPlatformData();
		
		Mockito.verify(em).find(PlatformData.class, PlatformDataServiceImpl.platformDataId);
	}
	
	@Test
	public void testGetPlatformDataReturnsExistingInstanceIfFound() {
		PlatformData data = new PlatformData();
		Mockito.when(em.find(PlatformData.class, PlatformDataServiceImpl.platformDataId))
			.thenReturn(data);
		
		PlatformData result = service.getPlatformData();
		
		assertEquals(data, result);
	}
	
	@Test
	public void testGetPlatformDataReturnsNewInstanceIfNotFound() {
		PlatformData result = service.getPlatformData();
		
		assertNotNull(result);
		assertEquals(PlatformDataServiceImpl.platformDataId, result.getId());
	}
	
	@Test
	public void testSavePlatformDataSavesData() {
		PlatformData data = new PlatformData();
		
		service.savePlatformData(data);
		
		Mockito.verify(em).merge(data);
	}
	
	@Test
	public void testSavePlatformDataAssignsId() {
		PlatformData data = new PlatformData();
		
		service.savePlatformData(data);
		
		assertEquals(PlatformDataServiceImpl.platformDataId, data.getId());
	}
}
