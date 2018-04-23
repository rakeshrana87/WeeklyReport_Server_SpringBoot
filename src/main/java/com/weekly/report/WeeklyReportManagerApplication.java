package com.weekly.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class WeeklyReportManagerApplication {

	public static void main(String[] args) {
		
	   
		SpringApplication.run(WeeklyReportManagerApplication.class, args);
	//	 ApplicationContext ctx = new SpringApplicationBuilder().bannerMode(Ba.Mode.CONSOLE).run(args);
	}
}
