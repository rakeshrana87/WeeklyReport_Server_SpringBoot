package com.weekly.report.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties // no prefix, find root level values.
public class ConfigProperties {
	
	//@Value("${username}")
    private String username;

   // @Value("${password}")
    private String password;

	/**
	 * @return the userName
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
