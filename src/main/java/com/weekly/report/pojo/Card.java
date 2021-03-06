package com.weekly.report.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "card")
@XmlAccessorType(XmlAccessType.FIELD)
public class Card {
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCard_type() {
		return card_type;
	}
	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public int getProject_card_rank() {
		return project_card_rank;
	}
	public void setProject_card_rank(int project_card_rank) {
		this.project_card_rank = project_card_rank;
	}
	public String getCreated_on() {
		return created_on;
	}
	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}
	public String getModified_on() {
		return modified_on;
	}
	public void setModified_on(String modified_on) {
		this.modified_on = modified_on;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Properties getProperties() {
		return properties;
	}
	public void setProperties(Properties properties2) {
		this.properties = properties2;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getRendered_description() {
		return rendered_description;
	}
	public void setRendered_description(String rendered_description) {
		this.rendered_description = rendered_description;
	}
	@XmlElement
	private String name;
	@XmlElement
	private String	description;
	@XmlElement
	private String card_type;
	@XmlElement
	private int id;
	@XmlElement
	private int number;
	@XmlElement
	private String project;
	@XmlElement
	private int version;
	@XmlElement
	private int project_card_rank;
	@XmlElement
	private String created_on;
	@XmlElement
	private String modified_on;
	@XmlElement
	private String modified_by;
	@XmlElement
	private String created_by;
	@XmlElement
	private Properties properties;
	@XmlElement
	private String tags;
	@XmlElement
	private String rendered_description;

	
}
