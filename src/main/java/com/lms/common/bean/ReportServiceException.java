package com.lms.common.bean;



public class ReportServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ReportServiceException() {
		super();
	}
	
	public ReportServiceException(String message) {
		super(message);
	}
	
	public ReportServiceException(String message, Throwable t) {
		super(message, t);
	}
}
