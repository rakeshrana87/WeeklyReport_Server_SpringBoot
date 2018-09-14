package com.weekly.report.db.repo;

import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;

public class Slides {

	@Indexed
	private List<Slide> slide;
	
	public List<Slide> getSlide() {
		return slide;
	}
	public void setSlide(List<Slide> slide) {
		this.slide = slide;
	}
	
}
