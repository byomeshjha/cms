package com.lms.common.bean;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties
public class ClientRequestBean {
	
	private String apiVersion;
	private String reportName;
	private Boolean asynchronous;
	private String notificationEmail;
	private String outputFormat;
	private String customId;
	private List<ClientRequestParameter> reportParameters;
	
	
	public String getApiVersion() {
		return apiVersion;
	}
	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public Boolean getAsynchronous() {
		return asynchronous;
	}
	public void setAsynchronous(Boolean asynchronous) {
		this.asynchronous = asynchronous;
	}
	public String getNotificationEmail() {
		return notificationEmail;
	}
	public void setNotificationEmail(String notificationEmail) {
		this.notificationEmail = notificationEmail;
	}
	public String getOutputFormat() {
		return outputFormat;
	}
	public void setOutputFormat(String outputFormat) {
		this.outputFormat = outputFormat;
	}
	public String getCustomId() {
		return customId;
	}
	public void setCustomId(String customId) {
		this.customId = customId;
	}
	public List<ClientRequestParameter> getReportParameters() {
		return reportParameters;
	}
	public void setReportParameters(List<ClientRequestParameter> reportParameters) {
		this.reportParameters = reportParameters;
	}
}
