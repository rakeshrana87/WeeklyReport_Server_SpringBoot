package com.weekly.report.db.repo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Document(collection="WeeklyReport")
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeeklyReport {

	
	/* public Exam() {
	}*/
	@Id
	private int teamId;
	
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int examId) {
		this.teamId = examId;
	}
	
	// enables indexing feature

	
	public List<Slides> getSlides() {
		return slides;
	}
	public void setSlides(List<Slides> slides) {
		this.slides = slides;
	}

	private List<Slides> slides;
	
}
