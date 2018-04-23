package com.weekly.report.pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "properties")
public class Properties {
	
	@XmlElement(name="property")
	private List<Property> property;

	public List<Property> getProperty() {
		return property;
	}

	public void setProperty(List<Property> property) {
		this.property = property;
	}
	public String toString() {
		String string=null;
		for(Property prop : property) {
			string.concat(prop.toString());
		}
		
		return string;
	}
}
