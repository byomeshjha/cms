package com.lms.services;



public interface EmailService {

	void sendNotification(String from, String[] to, String subject, String mailText);
	void sendSuccessNotification(String[] to, String reportName);
	void sendFailureNotification(String[] to, String reportName);
}
