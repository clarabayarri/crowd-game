package com.crowdgame.model;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class ExecutionInfo {

	private Integer batchId;
	
	private Integer taskId;
	
	private Long projectUid;
	
	private Map<String, Object> contents;
	
	private Integer userId;
	
	public ExecutionInfo(ExecutionResults results) {
		this.batchId = results.getBatchId();
		this.taskId = results.getTaskId();
		this.contents = encodeContents(results);
	}

	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}
	
	private Map<String, Object> encodeContents(ExecutionResults results) {
		Map<String, Object> result = Maps.newHashMap();
		result.put("failedAttempts", results.getFailedAttempts());
		result.put("timeSpent", results.getTimeSpent());
		List<String> answers = Lists.newArrayList(results.getWrongAnswers());
		result.put("wrongAnswers", answers);
		return result;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Long getProjectUid() {
		return projectUid;
	}

	public void setProjectUid(Long projectUid) {
		this.projectUid = projectUid;
	}

	public Map<String, Object> getContents() {
		return contents;
	}

	public void setContents(Map<String, Object> contents) {
		this.contents = contents;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
}
