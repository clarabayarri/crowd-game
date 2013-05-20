package com.crowdgame.service;

import com.crowdgame.model.GameUser;


public interface GameUserService {

	public void addGameUser(GameUser user);
	
	public void saveGameUser(GameUser user);
	
	public GameUser getUser(String username);
	
	public GameUser getCurrentUser();
	
	public GameUser getUserByUsernameOrEmail(String username);
	
	public boolean usernameExists(String username);
	
	public void removeUser(String username);

}
