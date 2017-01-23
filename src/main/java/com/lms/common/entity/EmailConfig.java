package com.lms.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;


@Entity
public class EmailConfig extends BaseEntity {
	
	@Column(name = "Email_Config_ID", insertable = false, updatable = false)
	private Integer id;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EmailSetupSequence")
	@SequenceGenerator(name="EmailSetupSequence", sequenceName="email_setup_sequence", initialValue=100, allocationSize=1) 
	@Column(name = "Email_Config_ID")
	private Integer emailConfigId;
	
	@Column(name = "Host", length = 55)
	private String host;
	
	@Column(name = "Port")
	private Integer port;
	
	@Column(name = "Smtps")
	private Boolean smtps;
	
	@Column(name = "User_Name", length = 25)
	private String userName;
	
	@Column(name = "Password", length = 50)
	private String password;
	
	@Column(name = "Success_Subject", length = 255)
	private String successSubject; 
	
	@Column(name = "Success_Text", length = 2000)
	private String successText;
	
	@Column(name = "Error_Subject", length = 255)
	private String errorSubject;
	
	@Column(name = "Error_Text", length = 2000)
	private String errorText;
	
	@Column(name = "Email_From", length = 60)
	private String from;
	
	@Column(name = "Email_From_Friendly", length = 60)
	private String fromFriendly;
	
	@Column(name = "On_Off")
	private Boolean onOff;
	
	@Column(name = "Firm_ID")
	protected Integer firmId;

	@ManyToOne
	@JoinColumn(name = "Firm_ID", insertable = false, updatable = false)
	private Firm firm;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public Boolean getSmtps() {
		return smtps;
	}
	public void setSmtps(Boolean smtps) {
		this.smtps = smtps;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSuccessSubject() {
		return successSubject;
	}
	public void setSuccessSubject(String successSubject) {
		this.successSubject = successSubject;
	}
	public String getSuccessText() {
		return successText;
	}
	public void setSuccessText(String successText) {
		this.successText = successText;
	}
	public String getErrorSubject() {
		return errorSubject;
	}
	public void setErrorSubject(String errorSubject) {
		this.errorSubject = errorSubject;
	}
	public String getErrorText() {
		return errorText;
	}
	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public Boolean getOnOff() {
		return onOff;
	}
	public void setOnOff(Boolean onOff) {
		this.onOff = onOff;
	}
	public String getFromFriendly() {
		return fromFriendly;
	}
	public void setFromFriendly(String fromFriendly) {
		this.fromFriendly = fromFriendly;
	} 
	
	
	
	
}
