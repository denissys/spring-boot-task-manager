package com.denissys.springboot.taskmanager.yml.settings;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "settings")
public class Settings implements Serializable {

	private static final long serialVersionUID = -6446348525987936637L;

	private Long taskMaxIdleTimeInMs = 0L;
	private List<Task> task = new ArrayList<Task>();

	public Long getTaskMaxIdleTimeInMs() {
		return taskMaxIdleTimeInMs;
	}
	
	public void setTaskMaxIdleTimeInMs(Long taskMaxIdleTimeInMs) {
		this.taskMaxIdleTimeInMs = taskMaxIdleTimeInMs;
	}
	
	public List<Task> getTask() {
		return task;
	}

	public void setTask(List<Task> task) {
		this.task = task;
	}
	
	public Optional<Task> getTask(final String taskName) {
		return getTask().stream().filter(t -> t.getName().equals(taskName)).findFirst();
	}

	@Override
	public String toString() {
		return "Settings [task=" + task + "]";
	}

}
