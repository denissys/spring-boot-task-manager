package com.denissys.springboot.taskmanager.service;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.denissys.springboot.taskmanager.exception.FailToScheduleTaskException;
import com.denissys.springboot.taskmanager.exception.InvalidTaskException;
import com.denissys.springboot.taskmanager.exception.MalformedTaskStructureYamlException;
import com.denissys.springboot.taskmanager.exception.MalformedYamlException;
import com.denissys.springboot.taskmanager.exception.SystemException;
import com.denissys.springboot.taskmanager.exception.UnexpectedErrorException;
import com.denissys.springboot.taskmanager.scheduler.TaskScheduler;
import com.denissys.springboot.taskmanager.yml.settings.Settings;

@Service
public class SettingsService implements ApplicationListener<ContextRefreshedEvent> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SettingsService.class);
	
	@Autowired
	private Settings settings;
	
	@Autowired
	private TaskScheduler taskScheduler;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try {
			Assert.assertNotNull("Failure to load settings of [application.yml] file", settings);
			Assert.assertNotNull("Tasks not loaded or not found", settings.getTask());
			Assert.assertTrue("Tasks not found", settings.getTask().size() > 0);
			
			LOGGER.info("Amount of tasks to schedule: {}", settings.getTask().size());
			
			settings.getTask().forEach(task -> {
				try {
					Assert.assertNotNull("Task name can not be null", task.getName());
					Assert.assertNotNull("Task description can not be null", task.getDescription());
					Assert.assertNotNull("Task periodInSecond can not be null", task.getPeriodInMs());
					Assert.assertNotNull("Task type can not be null", task.getType());
					Assert.assertNotNull("Task path can not be null", task.getPath());

					taskScheduler.scheduled(task);
				} catch (AssertionError assertionError) {
					throw new InvalidTaskException(assertionError.getMessage());
				}
			});
		} catch (AssertionError assertionError) {
			LOGGER.error(assertionError.getMessage());
			throw new MalformedYamlException(assertionError.getMessage());
		} catch (InvalidTaskException invalidTaskException) {
			LOGGER.error(invalidTaskException.getMessage());
			throw new MalformedTaskStructureYamlException(invalidTaskException.getMessage());
		}  catch (FailToScheduleTaskException failToScheduleTaskException) {
			LOGGER.error(failToScheduleTaskException.getMessage());
			throw new SystemException(failToScheduleTaskException.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new UnexpectedErrorException(e.getMessage());
		}
	}

}