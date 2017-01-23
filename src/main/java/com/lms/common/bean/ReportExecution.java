package com.lms.common.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReportExecution {

	private String currentPage;
	private String reportURI;
	private String requestId;
	private String status;
	private List<Export> exports;
	
	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}
	public String getReportURI() {
		return reportURI;
	}
	public void setReportURI(String reportURI) {
		this.reportURI = reportURI;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<Export> getExports() {
		return exports;
	}
	public void setExports(List<Export> exports) {
		this.exports = exports;
	}
	@Override
	public String toString() {
		return "ReportExecution [currentPage=" + currentPage + ", reportURI="
				+ reportURI + ", requestId=" + requestId + ", status=" + status
				+ ", exports=" + exports + "]";
	}
	
}
