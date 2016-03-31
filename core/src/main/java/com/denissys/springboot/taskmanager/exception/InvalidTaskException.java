package com.denissys.springboot.taskmanager.exception;

public class InvalidTaskException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidTaskException(final String message) {
        super(message);
    }
	
}
