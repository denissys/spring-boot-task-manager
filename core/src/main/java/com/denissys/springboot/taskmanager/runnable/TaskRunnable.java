package com.denissys.springboot.taskmanager.runnable;

import static java.lang.String.format;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.denissys.springboot.taskmanager.exception.ConnectivityFailException;
import com.denissys.springboot.taskmanager.exception.HttpRestRequestException;
import com.denissys.springboot.taskmanager.monitor.task.TaskMonitor;
import com.denissys.springboot.taskmanager.validate.ConnectivityValidate;
import com.denissys.springboot.taskmanager.yml.settings.RestHeader;
import com.denissys.springboot.taskmanager.yml.settings.Task;

public class TaskRunnable implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskRunnable.class);

	private Task task;

	public TaskRunnable(final Task task) {
		this.task = task;
	}

	@Async
	@Override
	public void run() {
		try {
			TaskMonitor.newExecution(task.getName());
			if (task.getType().isRest()) {
				validateConnectivity();
				new RestTemplate().postForLocation(task.getPath(), getHttpEntity(), String.class);
				LOGGER.info("Task: {} - Rest service invoked with success: url {}", task.getName(), task.getPath());
			} else {
				TaskMonitor.newFail(task.getName());
				final String message = String.format("Type task not supported: %s", task.getType()); 
				LOGGER.error(message);
				throw new HttpRestRequestException(message);
			}
		} catch (RestClientException e) {
			TaskMonitor.newFail(task.getName());
			final String message = String.format("Call job failed: [job.name: %s, job.path: %s, messageError: %s]", task.getName(), task.getPath(), e.getMessage());
			LOGGER.error(message);
		}
	}

	private void validateConnectivity() {
		final boolean isConnectivityValid = new ConnectivityValidate().isValid(task.getPath());
		if (!isConnectivityValid) {
			TaskMonitor.newFail(task.getName());
			final String message = format("Connectivity problem to call [%s]", task.getPath());
			LOGGER.info(message);
			throw new ConnectivityFailException(message);
		}
	}
	
	private HttpEntity<String> getHttpEntity() {
		final HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = null;
		if(task.getHeaders() != null) {
			for(RestHeader entry : task.getHeaders()) {
				headers.add(entry.getKey(), entry.getValue());
			}
			entity = new HttpEntity<String>("parameters", headers);
		}
		return entity;
	}

}
