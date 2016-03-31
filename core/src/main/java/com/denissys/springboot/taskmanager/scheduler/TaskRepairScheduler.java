package com.denissys.springboot.taskmanager.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.denissys.springboot.taskmanager.service.TaskRepairService;

@Component
public class TaskRepairScheduler {

	@Autowired
	private TaskRepairService taskRepairService; 
	
	@Scheduled(cron="*/10 * * * * *")
	public void execute(){
		taskRepairService.repair();
	}
	
}
