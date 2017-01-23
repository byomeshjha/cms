package com.lms.common.bean;

import java.util.List;


public class ReportParameterContainer {

	private List<ReportParameter> reportParameter;
	
	

	public List<ReportParameter> getReportParameter() {
		return reportParameter;
	}

	public void setReportParameter(List<ReportParameter> reportParameter) {
		this.reportParameter = reportParameter;
	}

	@Override
	public String toString() {
		return "ReportParameterContainer [reportParameter=" + reportParameter
				+ "]";
	}

}
