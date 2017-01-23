package com.lms.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lms.common.bean.ClientRequestBean;
import com.lms.common.bean.ClientRequestParameter;
import com.lms.util.JSONUtil;

public class ClientCallTest {
	
	
	public static String buildFromBaseUrl(String baseUrl, String uri) {
		if(baseUrl.endsWith("/") && !uri.startsWith("/")) {
			baseUrl += uri;
		} else if(!baseUrl.endsWith("/") && uri.startsWith("/")) {
			baseUrl += uri;
		} else if(baseUrl.endsWith("/") && uri.startsWith("/")) {
			baseUrl += uri.substring(1);
		} else {
			baseUrl += "/" + uri;
		}
		
		return baseUrl;
	}

	public static void main(String[] args) throws IOException {
		
		//case 1
		String case1 = ClientCallTest.buildFromBaseUrl("hello/world", "how/are/you/case1");
		String case2 = ClientCallTest.buildFromBaseUrl("hello/world/", "/how/are/you/case2");
		String case3 = ClientCallTest.buildFromBaseUrl("hello/world/", "how/are/you/case3");
		String case4 = ClientCallTest.buildFromBaseUrl("hello/world", "/how/are/you/case4");
		System.out.println(case1);
		System.out.println(case2);
		System.out.println(case3);
		System.out.println(case4);
		
		System.out.println();
		
		ClientRequestBean crb = new ClientRequestBean();
		List<ClientRequestParameter> reportParameters = new ArrayList<>();
		ClientRequestParameter crp = new ClientRequestParameter();
		crp.setName("BEGIN_DATE");
		crp.setType("java.util.Date");
		crp.setValue(new Date());
		reportParameters.add(crp);
		
		crb.setApiVersion("1");
		crb.setAsynchronous(Boolean.TRUE);
		crb.setCustomId("abcdefgh1234");
		crb.setNotificationEmail("byomesh.jha@eproxim.com");
		crb.setOutputFormat("pdf");
		crb.setReportName("EMPLOYEE_REPORT");
		crb.setReportParameters(reportParameters);
		
		JSONUtil<ClientRequestBean> ju = new JSONUtil<>(ClientRequestBean.class);
		System.out.println(ju.createJsonFromObject(crb));
		
		
		

	}

}
