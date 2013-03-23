package com.crowdgame.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.crowdgame.model.GameUser;
import com.crowdgame.util.RemoteCommunicationService;

public class GameUserServiceImpl implements GameUserService {

	private EntityManager em;
	 
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
	        this.em = entityManager;
	}
	
	@Autowired
	RemoteCommunicationService remoteService;
	
	@Transactional
	public void addGameUser(GameUser user) {
		em.persist(user);
		remoteService.postGameUser(user);
	}	

}
