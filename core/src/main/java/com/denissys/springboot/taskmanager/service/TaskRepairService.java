package com.denissys.springboot.taskmanager.service;

import java.util.Calendar;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.denissys.springboot.taskmanager.monitor.task.TaskInfo;
import com.denissys.springboot.taskmanager.monitor.task.TaskMonitor;
import com.denissys.springboot.taskmanager.scheduler.TaskScheduler;
import com.denissys.springboot.taskmanager.yml.settings.Settings;
import com.denissys.springboot.taskmanager.yml.settings.Task;

@Service
public class TaskRepairService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskRepairService.class);
	
	@Autowired
	private Settings settings;
	
	@Autowired
	private TaskScheduler taskScheduler;
	
	public void repair() {
		final long now = Calendar.getInstance().getTimeInMillis();
		final Map<String, TaskInfo> tasks = TaskMonitor.getTasks();
		
		if (tasks != null && tasks.size() > 0) {
			tasks.forEach((taskName, taskInfo) -> {
				final long taskInfoTimeInMs = Optional.ofNullable(taskInfo.getLastExecutionDateInMs()).orElse(taskInfo.getCreationDateInMs());
				final long taskIdleTimeInMs = now - taskInfoTimeInMs;
					
				if (taskIdleTimeInMs >= settings.getTaskMaxIdleTimeInMs()) {
					final Optional<Task> task = settings.getTask(taskName);
					if (task.isPresent()) {
						LOGGER.info("Recreating task: {}", taskName);
						taskScheduler.scheduled(task.get());			
					} else {
						LOGGER.error("Task not found in Setting Yaml: {}", taskName);
					}
				}
			});			
		}
	}

}
