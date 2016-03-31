package com.denissys.springboot.taskmanager.scheduler;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;
import org.springframework.scheduling.concurrent.ScheduledExecutorTask;
import org.springframework.stereotype.Component;

import com.denissys.springboot.taskmanager.exception.FailToScheduleTaskException;
import com.denissys.springboot.taskmanager.monitor.task.TaskMonitor;
import com.denissys.springboot.taskmanager.runnable.TaskRunnable;
import com.denissys.springboot.taskmanager.yml.settings.Task;

@Component
public class TaskScheduler {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskScheduler.class);
	
	public void scheduled(final Task task) {
		try {
			final ScheduledExecutorTask scheduledExecutorTask = new ScheduledExecutorTask();
			
			scheduledExecutorTask.setTimeUnit(TimeUnit.MILLISECONDS);
			scheduledExecutorTask.setPeriod(task.getPeriodInMs());
			scheduledExecutorTask.setRunnable(new TaskRunnable(task));
			
			final ScheduledExecutorFactoryBean factory = new ScheduledExecutorFactoryBean();
			factory.setScheduledExecutorTasks(scheduledExecutorTask);
			factory.afterPropertiesSet();
			
			TaskMonitor.addTask(task.getName());
			
			logTaskScheduled(task, scheduledExecutorTask);
		} catch (Exception e) {
			throw new FailToScheduleTaskException(e.getMessage());
		}
	}

	private void logTaskScheduled(final Task task, final ScheduledExecutorTask scheduledExecutorTask) {
		LOGGER.info("Task: ", task.toString());
		LOGGER.info("ScheduledExecutorTask.timeUnit: ", scheduledExecutorTask.getTimeUnit());
		LOGGER.info("ScheduledExecutorTask.period: ", scheduledExecutorTask.getPeriod());
		LOGGER.info("Sucess!");
		LOGGER.info("-----------------------------------------------------------------------------");
	}
	
}
