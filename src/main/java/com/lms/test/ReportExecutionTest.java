package com.lms.test;

import java.util.ArrayList;
import java.util.List;

import com.lms.common.bean.ReportExecutionContainer;
import com.lms.common.bean.ReportExecutionRequest;
import com.lms.common.bean.ReportParameter;
import com.lms.common.bean.ReportParameterContainer;
import com.lms.common.bean.Status;
import com.lms.util.JSONUtil;
import com.lms.util.XMLUtil;

public class ReportExecutionTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ReportExecutionRequest req = new ReportExecutionRequest();
		req.setReportUnitUri("/some/report/uri");
		req.setAsync("true");
		req.setFreshData("fasle");
		req.setSaveDataSnapshot("false");
		req.setOutputFormat("pdf");
		req.setInteractive("false");
		req.setIgnorePagination("false");
		req.setPages("1-5");
		
		List<ReportParameterContainer> parameters = new ArrayList<>();
		ReportParameterContainer rpc = new ReportParameterContainer();
		ReportParameter parameter = new ReportParameter();
		parameter.setName("SOME_PARAMETER_NAME");
		List<String> value = new ArrayList<>();
		value.add("SOME_VALUE");
		parameter.setValue(value);
		List<ReportParameter> reportParameter = new ArrayList<>();
		reportParameter.add(parameter);
		rpc.setReportParameter(reportParameter);
		parameters.add(rpc);
		req.setParameters(parameters);
		
		ReportExecutionContainer rec = new ReportExecutionContainer();
		rec.setReportExecutionRequest(req);
		
		JSONUtil<ReportExecutionContainer> ju = new JSONUtil<>(ReportExecutionContainer.class);
		String json = ju.createJsonFromObject(rec);
		System.out.println("ReportExecutionRequest Object to JSON: " + json);
		
		XMLUtil<ReportExecutionRequest> xu = new XMLUtil<>(ReportExecutionRequest.class);
		String xml = xu.objectToXml(req);
		System.out.println("ReportExecutionRequest Object to XML: " + xml);
		
		//ReportExecutionContainer rec1 = xu.xmlToObject(xml);
		//System.out.println("ReportExecutionRequest XML to Object: " + rec1.toString());
		
		XMLUtil<Status> xu1 = new XMLUtil<>(Status.class);
		String xml1 = "<status>ready</status>";
		Status status = xu1.xmlToObject(xml1);
		status.setStatus("ready");
		System.out.println("Status Object: " + status);
		System.out.println("Status XML: " + xu1.objectToXml(status));
		
		String s = "ready, failed";
		if(s.contains("ready")) {
			System.out.println("found");
		} else {
			System.out.println("not found");
		}

	}

}
