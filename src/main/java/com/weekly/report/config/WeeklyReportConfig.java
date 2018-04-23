package com.weekly.report.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.weekly.report.filters.ErrorFilter;
import com.weekly.report.filters.PostFilter;
import com.weekly.report.filters.PreFilter;
import com.weekly.report.filters.RouteFilter;

@Configuration
public class WeeklyReportConfig {
	@Bean
	public PreFilter preFilter() {
		return new PreFilter();
	}
	@Bean
	public PostFilter postFilter() {
		return new PostFilter();
	}
	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}
	@Bean
	public RouteFilter routeFilter() {
		return new RouteFilter();
	}
}
