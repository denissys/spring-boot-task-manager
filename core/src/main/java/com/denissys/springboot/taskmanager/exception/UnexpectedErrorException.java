package com.denissys.springboot.taskmanager.exception;

public class UnexpectedErrorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnexpectedErrorException(final String message) {
        super(message);
    }

}
