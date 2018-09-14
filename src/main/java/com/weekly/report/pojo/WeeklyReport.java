package com.weekly.report.pojo;

import org.springframework.stereotype.Component;

@Component
public class WeeklyReport {
private String estimatedhours ;
private String remainingHours;
private String assigneTeam;
private String progress_of_US;
private String totalETremaininghours;
private String totalETactualhours;
private String summary;
private String title;
private String impediments;

/*public WeeklyReport() {
	
}*/
/**
 * @return the summary
 */
public String getSummary() {
	return summary;
}
/**
 * @param summary the summary to set
 */
public void setSummary(String summary) {
	this.summary = summary;
}
/**
 * @return the title
 */
public String getTitle() {
	return title;
}
/**
 * @param title the title to set
 */
public void setTitle(String title) {
	this.title = title;
}
/**
 * @return the estimatedhours
 */
public String getEstimatedhours() {
	return estimatedhours;
}
/**
 * @param estimatedhours the estimatedhours to set
 */
public void setEstimatedhours(String estimatedhours) {
	this.estimatedhours = estimatedhours;
}
/**
 * @return the assigneTeam
 */
public String getAssigneTeam() {
	return assigneTeam;
}
/**
 * @param assigneTeam the assigneTeam to set
 */
public void setAssigneTeam(String assigneTeam) {
	this.assigneTeam = assigneTeam;
}
/**
 * @return the progress_of_US
 */
public String getProgress_of_US() {
	return progress_of_US;
}
/**
 * @param progress_of_US the progress_of_US to set
 */
public void setProgress_of_US(String progress_of_US) {
	this.progress_of_US = progress_of_US;
}
/**
 * @return the totalETremaininghours
 */
public String getTotalETremaininghours() {
	return totalETremaininghours;
}
/**
 * @param totalETremaininghours the totalETremaininghours to set
 */
public void setTotalETremaininghours(String totalETremaininghours) {
	this.totalETremaininghours = totalETremaininghours;
}
/**
 * @return the totalETactualhours
 */
public String getTotalETactualhours() {
	return totalETactualhours;
}
/**
 * @param totalETactualhours the totalETactualhours to set
 */
public void setTotalETactualhours(String totalETactualhours) {
	this.totalETactualhours = totalETactualhours;
}
/**
 * @return the remainingHours
 */
public String getRemainingHours() {
	return remainingHours;
}
/**
 * @param remainingHours the remainingHours to set
 */
public void setRemainingHours(String remainingHours) {
	this.remainingHours = remainingHours;
}
public String getImpediments() {
	return impediments;
}
public void setImpediments(String impediments) {
	this.impediments = impediments;
}
}
