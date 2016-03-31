package com.denissys.springboot.taskmanager.exception;

public class HttpRestRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public HttpRestRequestException(final String message) {
        super(message);
    }

}
