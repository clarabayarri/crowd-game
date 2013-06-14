package com.crowdgame.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crowdgame.model.PlatformData;

@Service
public class PlatformDataServiceImpl implements PlatformDataService {

	public static final Integer platformDataId = 1;
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public PlatformData getPlatformData() {
		PlatformData result = em.find(PlatformData.class, platformDataId);
		if (result == null) {
			result = new PlatformData();
			result.setId(platformDataId);
		}
		return result;
	}

	@Transactional
	public void savePlatformData(PlatformData data) {
		data.setId(platformDataId);
		em.merge(data);
	}

}
