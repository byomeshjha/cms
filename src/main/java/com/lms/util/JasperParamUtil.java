package com.lms.util;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

@Repository(value = "JasperParamUtil")
public class JasperParamUtil {
	
	protected final Logger log = Logger.getLogger(JasperParamUtil.class);
	
	public static String REPORT_ASYNC;
	public static String REPORT_FRESH_DATA;
	public static String REPORT_SAVE_DATA_SNAPSHOT;
	public static String REPORT_INTERACTIVE;
	public static String REPORT_IGNORE_PAGINATION;
	public static String REPORT_PAGES;
	
	@Autowired
	Environment env;
	
	@PostConstruct
	public void init() {
		log.debug("JasperParamUtil init called...");
		
		REPORT_ASYNC = env.getProperty("report.async");
		REPORT_FRESH_DATA = env.getProperty("report.freshdata");
		REPORT_SAVE_DATA_SNAPSHOT = env.getProperty("report.savedatasnapshot");
		REPORT_INTERACTIVE = env.getProperty("report.interactive");
		REPORT_IGNORE_PAGINATION = env.getProperty("report.ignorepagination");
		REPORT_PAGES = env.getProperty("report.pages");
	}

}
