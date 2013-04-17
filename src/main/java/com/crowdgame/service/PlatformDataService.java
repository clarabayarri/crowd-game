package com.crowdgame.service;

import org.springframework.stereotype.Service;

import com.crowdgame.model.PlatformData;

@Service
public interface PlatformDataService {

	public PlatformData getPlatformData();
	
	public void savePlatformData(PlatformData data);
	
}
