package com.denissys.springboot.taskmanager.monitor.task;

import java.io.Serializable;
import java.util.Calendar;

public class TaskInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long creationDateInMs;
	private Long lastExecutionDateInMs = null;
	private Long lastFailDateInMs = null;
	private Long executionsTimes = 0L;
	private Long failsTimes = 0L;
	
	public TaskInfo() {
		this.creationDateInMs = Calendar.getInstance().getTimeInMillis();
	}
	
	public Long getCreationDateInMs() {
		return creationDateInMs;
	}
	
	public Long getLastExecutionDateInMs() {
		return lastExecutionDateInMs;
	}
	
	public Long getLastFailDateInMs() {
		return lastFailDateInMs;
	}
	
	public Long getExecutionsTimes() {
		return executionsTimes;
	}
	
	public Long getFailsTimes() {
		return failsTimes;
	}
	
	public void newExecution() {
		this.lastExecutionDateInMs = Calendar.getInstance().getTimeInMillis();
		this.executionsTimes = this.executionsTimes+1;
	}
	
	public void newFail() {
		this.lastFailDateInMs = Calendar.getInstance().getTimeInMillis();
		this.failsTimes = this.failsTimes+1;
	}
	
}
