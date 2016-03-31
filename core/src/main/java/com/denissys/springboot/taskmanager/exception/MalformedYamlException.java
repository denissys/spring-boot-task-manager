package com.denissys.springboot.taskmanager.exception;

public class MalformedYamlException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MalformedYamlException(final String message) {
        super(message);
    }
	
}
