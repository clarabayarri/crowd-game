package com.crowdgame.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PlatformData {

	@Id
	private Integer id;
	
	private String projectId;
	
	private Long UID;
	
	public PlatformData() {
		id = 1;
		projectId = "";
		UID = new Long(1);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Long getUID() {
		return UID;
	}

	public void setUID(Long uID) {
		UID = uID;
	}
	
	
}
