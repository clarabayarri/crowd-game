package com.crowdgame.model;

public class TaskRequest {

	private Long projectUid;

	private Integer count;

	public Long getProjectUid() {
		return projectUid;
	}

	public void setProjectUid(Long projectUid) {
		this.projectUid = projectUid;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
