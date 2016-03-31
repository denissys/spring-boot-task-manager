package com.denissys.springboot.taskmanager.enums;

public enum TaskType {

	REST("rest"),
	UNDEFINED(null);
	
	private String type;

	private TaskType(final String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public boolean isRest() {
		return this.equals(REST);
	}
	
}