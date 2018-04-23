package com.weekly.report.pojo;

public class WeeklyError {
private String id;
private String message;
private String statusCode;


public WeeklyError(String id,String message,String statusCode) {
	this.id=id;
	this.message=message;
	this.statusCode=statusCode;
}
public String getStatusCode() {
	return statusCode;
}
public void setStatusCode(String statusCode) {
	this.statusCode = statusCode;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
}
