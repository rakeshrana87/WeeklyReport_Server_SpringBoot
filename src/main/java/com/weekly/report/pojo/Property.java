package com.weekly.report.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@Component

@XmlRootElement(name ="property")
public class Property {

@XmlElement
private String name;
@XmlElement
private String value;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getValue() {
	return value;
}
public void setValue(String value) {
	this.value = value;
}

public String toString() {
	return "name: " + name  + ".... value :" +value;
}
}
