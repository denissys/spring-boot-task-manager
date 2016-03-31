package com.denissys.springboot.taskmanager.yml.settings;

import java.io.Serializable;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.denissys.springboot.taskmanager.enums.TaskType;

@EnableConfigurationProperties
@ConfigurationProperties(prefix = "settings.task")
public class Task implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String description;
	private Long periodInMs;
	private TaskType type;
	private String path;
	private List<RestHeader> restHeaders;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPeriodInMs() {
		return periodInMs;
	}
	
	public void setPeriodInMs(Long periodInMs) {
		this.periodInMs = periodInMs;
	}

	public TaskType getType() {
		return type;
	}

	public void setType(TaskType type) {
		this.type = type;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<RestHeader> getHeaders() {
		return restHeaders;
	}

	public void setHeaders(List<RestHeader> restHeaders) {
		this.restHeaders = restHeaders;
	}

	@Override
	public String toString() {
		return "Task [name=" + name + ", description=" + description + ", periodInMs=" + periodInMs + ", type="
				+ type + ", path=" + path + ", headers=" + restHeaders + "]";
	}

}
