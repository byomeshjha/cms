package com.lms.common.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReportExecutionRequest {

	private String reportUnitUri;
	private String async;
	private String freshData;
	private String saveDataSnapshot;
	private String outputFormat;
	private String interactive;
	private String ignorePagination;
	private String pages;
	private List<ReportParameterContainer> parameters;
	
	
	public String getReportUnitUri() {
		return reportUnitUri;
	}
	public void setReportUnitUri(String reportUnitUri) {
		this.reportUnitUri = reportUnitUri;
	}
	public String getAsync() {
		return async;
	}
	public void setAsync(String async) {
		this.async = async;
	}
	public String getFreshData() {
		return freshData;
	}
	public void setFreshData(String freshData) {
		this.freshData = freshData;
	}
	public String getSaveDataSnapshot() {
		return saveDataSnapshot;
	}
	public void setSaveDataSnapshot(String saveDataSnapshot) {
		this.saveDataSnapshot = saveDataSnapshot;
	}
	public String getOutputFormat() {
		return outputFormat;
	}
	public void setOutputFormat(String outputFormat) {
		this.outputFormat = outputFormat;
	}
	public String getInteractive() {
		return interactive;
	}
	public void setInteractive(String interactive) {
		this.interactive = interactive;
	}
	public String getIgnorePagination() {
		return ignorePagination;
	}
	public void setIgnorePagination(String ignorePagination) {
		this.ignorePagination = ignorePagination;
	}
	public String getPages() {
		return pages;
	}
	public void setPages(String pages) {
		this.pages = pages;
	}
	public List<ReportParameterContainer> getParameters() {
		return parameters;
	}
	public void setParameters(List<ReportParameterContainer> parameters) {
		this.parameters = parameters;
	}
	@Override
	public String toString() {
		return "ReportExecutionRequest [reportUnitUri=" + reportUnitUri
				+ ", async=" + async + ", freshData=" + freshData
				+ ", saveDataSnapshot=" + saveDataSnapshot + ", outputFormat="
				+ outputFormat + ", interactive=" + interactive
				+ ", ignorePagination=" + ignorePagination + ", pages=" + pages
				+ ", parameters=" + parameters + "]";
	}

}
