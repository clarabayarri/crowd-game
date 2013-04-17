package com.crowdgame.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crowdgame.model.PlatformData;

@Service
public class PlatformDataServiceImpl implements PlatformDataService {

	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	public PlatformData getPlatformData() {
		PlatformData result = em.find(PlatformData.class, 1);
		if (result == null) {
			result = new PlatformData();
		}
		return result;
	}

	@Transactional
	public void savePlatformData(PlatformData data) {
		data.setId(1);
		em.merge(data);
	}

}
