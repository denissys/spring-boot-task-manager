package com.denissys.springboot.taskmanager.exception;

public class ConnectivityFailException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConnectivityFailException(final String message) {
        super(message);
    }

}
