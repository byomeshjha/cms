package com.lms.common.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class CaseReminder extends BaseEntity {
	
	@Column(name = "Case_Reminder_ID", insertable = false, updatable = false)
	private Integer id;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ReminderSequence")
	@SequenceGenerator(name="ReminderSequence", sequenceName="reminder_sequence", initialValue=100, allocationSize=1) 
	@Column(name = "Case_Reminder_ID")
	private Integer caseReminderId;
	
	/**
	 * hearing date, payment, deadline approaching, 
	 * client contact, official communication, submit document
	 */
	@Column(name="Reminder_Type", length = 20)
	private String reminderType;
	
	@Column(name="Reminder_Mode", length = 10)
	private String reminderMode;
	
	@Column(name = "Firm_User_ID")
	protected Integer reminderForId;
	
	@ManyToOne
	@JoinColumn(name = "Firm_User_ID", insertable = false, updatable = false)
	private FirmUser reminderFor;
	
	@Column(name="Reminder_Date")
	private Timestamp reminderDate;
	
	@Column(name="Reminder_Days_Ahead")
	private Integer daysAhead;
	
	@Column(name="Reminder_Note", length = 255)
	private String reminderNote;
	
	@Column(name="Reminder_Active")
	private Boolean reminderActive;
	
	@Column(name = "Case_Docet_ID")
	protected Integer caseDocketId;

	@ManyToOne
	@JoinColumn(name = "Case_Docet_ID", insertable = false, updatable = false)
	private CaseDocket caseDocket;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCaseReminderId() {
		return caseReminderId;
	}

	public void setCaseReminderId(Integer caseReminderId) {
		this.caseReminderId = caseReminderId;
	}

	public String getReminderType() {
		return reminderType;
	}

	public void setReminderType(String reminderType) {
		this.reminderType = reminderType;
	}

	public String getReminderMode() {
		return reminderMode;
	}

	public void setReminderMode(String reminderMode) {
		this.reminderMode = reminderMode;
	}

	public Integer getReminderForId() {
		return reminderForId;
	}

	public void setReminderForId(Integer reminderForId) {
		this.reminderForId = reminderForId;
	}

	public FirmUser getReminderFor() {
		return reminderFor;
	}

	public void setReminderFor(FirmUser reminderFor) {
		this.reminderFor = reminderFor;
	}

	public Timestamp getReminderDate() {
		return reminderDate;
	}

	public void setReminderDate(Timestamp reminderDate) {
		this.reminderDate = reminderDate;
	}

	public Integer getDaysAhead() {
		return daysAhead;
	}

	public void setDaysAhead(Integer daysAhead) {
		this.daysAhead = daysAhead;
	}

	public String getReminderNote() {
		return reminderNote;
	}

	public void setReminderNote(String reminderNote) {
		this.reminderNote = reminderNote;
	}

	public Boolean getReminderActive() {
		return reminderActive;
	}

	public void setReminderActive(Boolean reminderActive) {
		this.reminderActive = reminderActive;
	}

	public Integer getCaseDocketId() {
		return caseDocketId;
	}

	public void setCaseDocketId(Integer caseDocketId) {
		this.caseDocketId = caseDocketId;
	}

	public CaseDocket getCaseDocket() {
		return caseDocket;
	}

	public void setCaseDocket(CaseDocket caseDocket) {
		this.caseDocket = caseDocket;
	}
	
	

}
