package com.lms.util;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "reports")
public class ReportsConfig {

    private List<Report> reportList;
	
    @XmlElement(name = "report")
	public List<Report> getReportList() {
		return reportList;
	}

	public void setReportList(List<Report> reports) {
		this.reportList = reports;
	}	
	
	public static class Report {
		
		private String id;
		private String displayName;
		private String mainReportJrxml;
		private String reportRelativePath;
		private SubReports subReports;
		
		@XmlAttribute
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		@XmlElement(name = "display-name")
		public String getDisplayName() {
			return displayName;
		}

		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}

		@XmlElement(name = "main-report-jrxml")
		public String getMainReportJrxml() {
			return mainReportJrxml;
		}

		public void setMainReportJrxml(String mainReportJrxml) {
			this.mainReportJrxml = mainReportJrxml;
		}

		@XmlElement(name = "report-relative-path")
		public String getReportRelativePath() {
			return reportRelativePath;
		}

		public void setReportRelativePath(String reportRelativePath) {
			this.reportRelativePath = reportRelativePath;
		}

		@XmlElement(name = "sub-reports")
		public SubReports getSubReports() {
			return subReports;
		}

		public void setSubReports(SubReports subReports) {
			this.subReports = subReports;
		}
	}
	
	public static class SubReports {

		private List<SubReport> subReport;
		
		@XmlElement(name = "sub-report")
		public List<SubReport> getSubReport() {
			return subReport;
		}

		public void setSubReport(List<SubReport> subReport) {
			this.subReport = subReport;
		}
		
	}
	
	public static class SubReport {
		
		private String id;
		private String subReportJrxml;
		private String reportRelativePath;
		private String subreportParameter;

		@XmlAttribute
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		@XmlElement(name = "sub-report-jrxml")
		public String getSubReportJrxml() {
			return subReportJrxml;
		}

		public void setSubReportJrxml(String subReportJrxml) {
			this.subReportJrxml = subReportJrxml;
		}

		@XmlElement(name = "report-relative-path")
		public String getReportRelativePath() {
			return reportRelativePath;
		}

		public void setReportRelativePath(String reportRelativePath) {
			this.reportRelativePath = reportRelativePath;
		}
		
		@XmlElement(name = "sub-report-parameter")
		public String getSubreportParameter() {
			return subreportParameter;
		}

		public void setSubreportParameter(String subreportParameter) {
			this.subreportParameter = subreportParameter;
		}
	}
	
	/**
	 * Test
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		ReportsConfig config = new ReportsConfig();
		config.reportList = new ArrayList<>();
		Report report = new Report();
		report.id = "1";
		report.displayName = "Batch Status";
		report.mainReportJrxml = "BatchStatus.jrxml";
		report.reportRelativePath = "/reports";
		config.reportList.add(report);
		
		SubReports subReports = new SubReports();
		subReports.subReport = new ArrayList<>();
		SubReport subReport = new SubReport();
		subReport.id = "1";
		subReport.subReportJrxml = "BatchStatusSub1.jrxml";
		subReport.reportRelativePath = "/reports";
		subReports.subReport.add(subReport);
		
		SubReport subReport1 = new SubReport();
		subReport1.id = "2";
		subReport1.subReportJrxml = "BatchStatusSub2.jrxml";
		subReport1.reportRelativePath = "/reports";
		subReports.subReport.add(subReport1);
		
		report.subReports = subReports;
		
		JAXBContext jaxbContext = JAXBContext.newInstance(ReportsConfig.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		// output pretty printed
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(config, System.out);
	}

}
