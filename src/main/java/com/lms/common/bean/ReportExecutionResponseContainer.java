package com.lms.common.bean;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class ReportExecutionResponseContainer {

	private ReportExecution reportExecution;

	public ReportExecution getReportExecution() {
		return reportExecution;
	}

	public void setReportExecution(ReportExecution reportExecution) {
		this.reportExecution = reportExecution;
	}

	@Override
	public String toString() {
		return "ReportExecutionResponseContainer [reportExecution="
				+ reportExecution + "]";
	}

}
