package com.lms.services.impl;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.lms.common.bean.Email;
import com.lms.common.bean.ReportServiceException;
import com.lms.common.entity.EmailConfig;
import com.lms.ejb.ConfigEjb;
import com.lms.services.EmailService;
import com.lms.util.DaemonThreadFactory;


@Repository(value = "EmailServiceImpl")
@Service
public class EmailServiceImpl implements EmailService {

	protected final Logger log = Logger.getLogger(EmailServiceImpl.class);
	private static final int NUM_THREADS = 5;
	private Executor executor;

	@EJB(mappedName="java:app/lms/ConfigEjb")
	ConfigEjb configEjb;

	private Email _email;

	private String successSubject;
	private String failureSubject;
	private String emailSuccessText;
	private String emailFailureText;
	private String emailFooterText;
	private boolean sendNotification = false;

	@PostConstruct
	public void init() {
		
		log.debug("EmailServiceImpl init called...");
		log.debug("@@ Creating email service thread pool with "  + NUM_THREADS + " threads.");
		
		executor = Executors.newFixedThreadPool(NUM_THREADS, new DaemonThreadFactory());
		EmailConfig es = configEjb.getEmailConfig();
		String smtpHost = es.getHost();
		Integer smtpPort = es.getPort();
		String userName = es.getUserName();
		String password = es.getPassword();
		String fromAddress = es.getFrom();
		Boolean smtps = es.getSmtps();
		successSubject = es.getSuccessSubject();
		failureSubject = es.getErrorSubject();
		emailSuccessText = es.getSuccessText();
		emailFailureText = es.getErrorText();
		emailFooterText = "";
		sendNotification = es.getOnOff();
		if(!sendNotification) log.info("@@ Email Notification has been turned off.");

		_email = new Email();
		_email.setHost(smtpHost);
		if(!"".equals(smtpPort) && smtpPort != null) {
			_email.setPort(smtpPort);
		}
		_email.setUserName(userName);
		_email.setPassword(password);
		_email.setSmtps(smtps);
		_email.setFrom(fromAddress);
	}

	/**
	 * Send notification with stock message format with report name replacement.
	 */
	public void sendSuccessNotification(String[] to, String reportName) {
		if(!sendNotification) return;
		log.info("@@ Sending Success Email Notification for generated report [" + reportName + "]");
		StringBuffer strBuff = new StringBuffer(emailSuccessText);
		strBuff.append(emailFooterText);
		String mailText = strBuff.toString();
		mailText = mailText.replaceAll("#generated.reportName#", reportName);

		Email email = new Email(_email);
		email.setSubject(successSubject);
		email.setTo(to);
		email.setMailText(mailText);
		sendMail(email);
	}

	public void sendFailureNotification(String[] to, String reportName) {
		log.info("@@ Sending Failure Email Notification for report [" + reportName + "]");
		if(!sendNotification) return;
		StringBuffer strBuff = new StringBuffer(emailFailureText);
		strBuff.append(emailFooterText);
		String mailText = strBuff.toString();
		mailText = mailText.replaceAll("#generated.reportName#", reportName);

		Email email = new Email(_email);
		email.setSubject(failureSubject);
		email.setTo(to);
		email.setMailText(mailText);
		sendMail(email);
	}

	/**
	 * Send notification with custom message, subject and from address
	 */
	public void sendNotification(String from, String[] to, String subject, String mailText) {
		if(!sendNotification) return;
		log.info("@@ Sending Email Notification generic.");
		Email email = new Email(_email);
		email.setFrom(from);
		email.setTo(to);
		email.setSubject(subject);
		email.setMailText(mailText);
		sendMail(email);
	}


	/**
	 * Single method to send report ready for download notification.
	 */
	private void sendMail(final Email email)
			throws ReportServiceException {
		executor.execute(new Runnable() {
			public void run() {
				try {

					log.debug("@@ Sending email to : " + email.getTo());
					log.debug("@@ Sending Text : " + email.getMailText());

				    MultiPartEmail mpe = new HtmlEmail();
				    mpe.setDebug(false);
				    if(email.getUserName() != null && !"".equals(email.getUserName())) {
				    	mpe.setAuthenticator(new DefaultAuthenticator(email.getUserName(), email.getPassword()));
				    }
				    mpe.setDebug(true);
				    mpe.setHostName(email.getHost());
				    if(email.getPort() > 10) {
				    	mpe.setSmtpPort(email.getPort());
				    }
				    mpe.addTo(email.getTo());
					mpe.setFrom(email.getFrom());
				    mpe.setSubject(email.getSubject());
				    mpe.setMsg(email.getMailText());
				    /*
				    if(email.getAttachment() != null) {
				    	InputStream is = new BufferedInputStream(
				    			new ByteArrayInputStream(email.getAttachment()));
				    	DataSource source = new ByteArrayDataSource(is, attachMimeType);
				    	mpe.attach(source, attachFileName, email.getSubject());
					}
					*/
				    //mpe.setStartTLSRequired(email.isSmtps() ? true: false);
				    mpe.setStartTLSEnabled(email.isSmtps() ? true: false);
				    mpe.setSSLOnConnect(email.isSmtps() ? true: false);

				    mpe.send();
				}
				catch(Exception e) {
					String[] addresses = email.getTo();
					if(addresses != null) {
						for(String address : addresses) {
							printEmailSendFailure(address, e);
						}
					}
					else {
						printEmailSendFailure("Email address null", e);
					}
					e.printStackTrace();
					throw new ReportServiceException(
							e.getMessage());
				}
			}
		});
	}

	/**
	 * Print alarm message in log
	 * @param reportRequestLog
	 */
	public void printEmailSendFailure(String toAddress, Exception e) {
		String alarmKey = "EP1004";
		String alarmString = "Email send failed for address ";
		String alarmMessage = alarmString  +
				toAddress + ((e != null) ? " - " + e.getMessage() : "");
		log.error(alarmKey + ": " + alarmString + " - " + alarmMessage);
	}

}
