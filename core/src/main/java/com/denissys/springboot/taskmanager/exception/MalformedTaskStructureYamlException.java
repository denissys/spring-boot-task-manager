package com.denissys.springboot.taskmanager.exception;

public class MalformedTaskStructureYamlException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MalformedTaskStructureYamlException(final String message) {
        super(message);
    }

}
