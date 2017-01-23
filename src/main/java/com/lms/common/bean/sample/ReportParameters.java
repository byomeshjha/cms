package com.lms.common.bean.sample;

import java.util.HashMap;
import java.util.Map;

public class ReportParameters {
	
	private Map<String, String> params;
	
	
	public ReportParameters() {
		params = new HashMap<>();
	}
	
	public ReportParameters(Map<String, String> params) {
		this.params = params;
	}
	
	public Map<String, String> getParams() {
		return params;
	}
	
	public void setParams(String key, String value) {
		params.put(key, value);
	}

}
