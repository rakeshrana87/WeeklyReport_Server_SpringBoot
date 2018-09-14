package com.weekly.report.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.weekly.report.db.repo.Slide;
import com.weekly.report.db.repo.Slides;
import com.weekly.report.db.repo.WeeklyReport;
import com.weekly.report.db.repo.WeeklyReportRepo;
import com.weekly.report.pojo.Card;

@RestController
public class WeeklyReportDbController {

	@Autowired
	private WeeklyReportRepo weeklyReportRepo;
	
	@RequestMapping(path = "get/card", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String  getCardDetails() {
	
		WeeklyReport  weeklyReport = new WeeklyReport();
		List<Slide> slides = new ArrayList<Slide>();
		List<Slides> slidesObjs = new ArrayList<Slides>();
		Slide slide = new Slide();
		Slides slidesObj = new Slides();
		slide.setContent("abcd \n + sdsd \n +ssds \n + ssd \n + ssdsd \n");
		slide.setTitle("abcd...");
		slide.setOrder(0);
		slides.add(slide);
		slide.setOrder(1);
		slides.add(slide);
		slidesObj.setSlide(slides);
		slidesObjs.add(slidesObj);
		weeklyReport.setTeamId(1);
		weeklyReport.setSlides(slidesObjs);
		weeklyReportRepo.save(weeklyReport);
		return "get is working successfully";
	}

	@RequestMapping(path = "/create/card", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Card postCardDetails(@RequestBody Card card) {

		return card;
	}

	@RequestMapping(path = "/update/card", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Card updateCardDetails(@RequestBody Card card) {

		return card;
	}

	@RequestMapping(path = "/delete/card", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String deleteCardDetails(@RequestBody Card card) {

		return " delete weekly demo";
	}
}
