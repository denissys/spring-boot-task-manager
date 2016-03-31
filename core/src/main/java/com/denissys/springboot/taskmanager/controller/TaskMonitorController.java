package com.denissys.springboot.taskmanager.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.denissys.springboot.taskmanager.monitor.task.TaskInfo;
import com.denissys.springboot.taskmanager.monitor.task.TaskMonitor;

@Controller
@RequestMapping(value = "/")
public class TaskMonitorController {

	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/task-monitor", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
	public Map<String, TaskInfo> healthcheck(final HttpServletResponse response) {
		return TaskMonitor.getTasks();
	}

}
