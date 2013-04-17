package com.crowdgame.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PlatformData {

	@Id
	private Integer id;
	
	private Long projectId;
	
	private Long UID;
	
	public PlatformData() {
		id = 1;
		projectId = new Long(1);
		UID = new Long(1);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getUID() {
		return UID;
	}

	public void setUID(Long uID) {
		UID = uID;
	}
	
	
}
