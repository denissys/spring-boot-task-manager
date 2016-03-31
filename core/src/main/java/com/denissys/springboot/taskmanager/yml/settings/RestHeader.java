package com.denissys.springboot.taskmanager.yml.settings;

import java.io.Serializable;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class RestHeader implements Serializable {

	private static final long serialVersionUID = 1L;
	private String key;
	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Header [key=" + key + ", value=" + value + "]";
	}

}
