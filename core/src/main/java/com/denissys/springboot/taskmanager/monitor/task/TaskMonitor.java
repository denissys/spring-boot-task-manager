package com.denissys.springboot.taskmanager.monitor.task;

import java.util.HashMap;
import java.util.Map;

public abstract class TaskMonitor {
	
	public static Map<String, TaskInfo> tasks = new HashMap<>();
	
	public static void addTask(final String taskName) {
		tasks.put(taskName, new TaskInfo());
	}
	
	public static Map<String, TaskInfo> getTasks() {
		return tasks;
	}
	
	public static void newExecution(final String taskName) {
		final TaskInfo taskInfo = getTasks().get(taskName);
		if (taskInfo != null) {
			taskInfo.newExecution();
		}
	}
	
	public static void newFail(final String taskName) {
		getTasks().get(taskName).newFail();;
	}

}
