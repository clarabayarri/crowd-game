package com.crowdgame.service;

import com.crowdgame.model.GameUser;


public interface GameUserService {

	public void addGameUser(GameUser user);
	
	public GameUser getUser(String username);
	
	public boolean usernameExists(String username);

}
