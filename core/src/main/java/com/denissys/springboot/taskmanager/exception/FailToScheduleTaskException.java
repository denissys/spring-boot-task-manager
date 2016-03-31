package com.denissys.springboot.taskmanager.exception;

public class FailToScheduleTaskException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FailToScheduleTaskException(final String message) {
        super(message);
    }

}
