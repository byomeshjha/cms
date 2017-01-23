package com.lms.common.entity;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "caseNotes", "reminders", "documents" })
@Entity
public class CaseDocket extends BaseEntity {
	
	@Column(name = "Case_Docket_ID", insertable = false, updatable = false)
	private Integer id;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CaseSequence")
	@SequenceGenerator(name="CaseSequence", sequenceName="case_sequence", initialValue=100, allocationSize=1) 
	@Column(name = "Case_Docket_ID")
	private Integer caseDocketId;
	
	@Column(name = "Case_Name", length = 50)
	private String caseName;
	
	@Column(name = "Case_Type_ID")
	private Integer caseTypeId;
	
	@ManyToOne
	@JoinColumn(name = "Case_Type_ID", insertable = false, updatable = false)
	private CaseType caseType;
	
	@Transient
	private List<CaseType> caseTypeList;
	
	@Column(name = "Case_Description", length = 1000)
	private String caseDescription;
	
	@Column(name = "Case_Status_ID")
	private Integer caseStatusId;
	
	@ManyToOne
	@JoinColumn(name = "Case_Status_ID", insertable = false, updatable = false)
	private CaseStatus caseStatus;
	
	@Transient
	private List<CaseStatus> caseStatusList;
	
	@Column(name = "Firm_ID")
	private Integer firmId;
	
	@ManyToOne
	@JoinColumn(name = "Firm_ID", insertable = false, updatable = false)
	private Firm firm;
	
	@Column(name = "User_ID")
	private Integer caseManagerId;
	
	@ManyToOne
	@JoinColumn(name = "User_ID", insertable = false, updatable = false)
	private FirmUser caseManager;
	
	@Column(name = "Client_ID")
	private Integer clientId;
	
	@ManyToOne
	@JoinColumn(name = "Client_ID", insertable = false, updatable = false)
	private Client client;
	
	@OneToMany(mappedBy = "caseDocketId", cascade = {CascadeType.ALL})
	private Collection<CaseNote> caseNotes;
	
	@OneToMany(mappedBy = "caseDocketId", cascade = {CascadeType.ALL})
	private Collection<CaseReminder> reminders;
	
	@OneToMany(mappedBy = "caseDocketId", cascade = {CascadeType.ALL})
	private Collection<CaseDocument> documents;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCaseDocketId() {
		return caseDocketId;
	}

	public void setCaseDocketId(Integer caseDocketId) {
		this.caseDocketId = caseDocketId;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getCaseDescription() {
		return caseDescription;
	}

	public void setCaseDescription(String caseDescription) {
		this.caseDescription = caseDescription;
	}

	public CaseStatus getCaseStatus() {
		return caseStatus;
	}

	public void setCaseStatus(CaseStatus caseStatus) {
		this.caseStatus = caseStatus;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Collection<CaseNote> getCaseNotes() {
		return caseNotes;
	}

	public void setCaseNotes(Collection<CaseNote> caseNotes) {
		this.caseNotes = caseNotes;
	}

	public Collection<CaseReminder> getReminders() {
		return reminders;
	}

	public void setReminders(Collection<CaseReminder> reminders) {
		this.reminders = reminders;
	}

	public Collection<CaseDocument> getDocuments() {
		return documents;
	}

	public void setDocuments(Collection<CaseDocument> documents) {
		this.documents = documents;
	}

	public Integer getCaseStatusId() {
		return caseStatusId;
	}

	public void setCaseStatusId(Integer caseStatusId) {
		this.caseStatusId = caseStatusId;
	}

	public Integer getFirmId() {
		return firmId;
	}

	public void setFirmId(Integer firmId) {
		this.firmId = firmId;
	}

	public Firm getFirm() {
		return firm;
	}

	public void setFirm(Firm firm) {
		this.firm = firm;
	}

	public FirmUser getCaseManager() {
		return caseManager;
	}

	public void setCaseManager(FirmUser caseManager) {
		this.caseManager = caseManager;
	}

	public CaseType getCaseType() {
		return caseType;
	}

	public void setCaseType(CaseType caseType) {
		this.caseType = caseType;
	}

	public Integer getCaseTypeId() {
		return caseTypeId;
	}

	public void setCaseTypeId(Integer caseTypeId) {
		this.caseTypeId = caseTypeId;
	}

	public Integer getCaseManagerId() {
		return caseManagerId;
	}

	public void setCaseManagerId(Integer caseManagerId) {
		this.caseManagerId = caseManagerId;
	}

	public List<CaseType> getCaseTypeList() {
		return caseTypeList;
	}

	public void setCaseTypeList(List<CaseType> caseTypeList) {
		this.caseTypeList = caseTypeList;
	}

	public List<CaseStatus> getCaseStatusList() {
		return caseStatusList;
	}

	public void setCaseStatusList(List<CaseStatus> caseStatusList) {
		this.caseStatusList = caseStatusList;
	}
}
