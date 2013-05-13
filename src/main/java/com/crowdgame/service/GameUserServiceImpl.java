package com.crowdgame.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crowdgame.model.GameUser;
import com.crowdgame.util.RemoteCommunicationService;

@Service
public class GameUserServiceImpl implements GameUserService {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private RemoteCommunicationService remoteService;
	
	@Transactional
	public void addGameUser(GameUser user) {
		em.persist(user);
		remoteService.postGameUser(user);
	}
	
	@Transactional
	public void saveGameUser(GameUser user) {
		em.merge(user);
	}
	
	@Transactional
	public GameUser getUser(String username) {
		GameUser user = em.find(GameUser.class, username);
		return user;
	}
	
	@Transactional
	public GameUser getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null) {
	    	String username = auth.getName();
		    return getUser(username);
	    }
	    return null;
	}
	
	@Transactional
	public boolean usernameExists(String username) {
		GameUser user = em.find(GameUser.class, username);
		return user != null;
	}

}
