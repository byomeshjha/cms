package com.lms.common.bean;

import java.sql.Timestamp;


public class JasperResponse {
	
	private boolean successful = false;
	private ReportExecution reportExecution;
	private String responseRaw;
	private Timestamp timeExecuted;
	
	public boolean isSuccessful() {
		return successful;
	}
	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}
	public ReportExecution getReportExecution() {
		return reportExecution;
	}
	public void setReportExecution(ReportExecution reportExecution) {
		this.reportExecution = reportExecution;
	}
	public String getResponseRaw() {
		return responseRaw;
	}
	public void setResponseRaw(String responseRaw) {
		this.responseRaw = responseRaw;
	}
	public Timestamp getTimeExecuted() {
		return timeExecuted;
	}
	public void setTimeExecuted(Timestamp timeExecuted) {
		this.timeExecuted = timeExecuted;
	}
}
