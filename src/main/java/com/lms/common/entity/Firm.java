package com.lms.common.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(value = { "addresses", "caseDocket", "firmUsers", "caseStatus", "caseType" })
@Entity
public class Firm extends BaseEntity {
	
	@Column(name = "Firm_ID", insertable = false, updatable = false)
	private Integer id;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FirmSequence")
	@SequenceGenerator(name="FirmSequence", sequenceName="firm_sequence", initialValue=100, allocationSize=1) 
	@Column(name = "Firm_ID")
	private Integer firmId;
	
	@Column(name = "Name", length = 50, nullable = false)
	private String name;
	
	@Column(name = "Description", length = 2000)
	private String description;
	
	@Column(name = "Firm_Domain", length = 50)
	private String firmDomain;
	
	@ElementCollection 
	private Collection<Address> addresses;
	
	@Column(name = "Primary_Phone", length = 15)
	private String phone1;
	
	@Column(name = "Secondary_Phone", length = 15)
	private String phone2;
	
	@Column(name = "Other_Phone", length = 15)
	private String phone3;
	
	@Column(name = "Primary_Fax", length = 15)
	private String fax1;
	
	@Column(name = "Secondary_Fax", length = 15)
	private String fax2;
	
	@Column(name = "Other_Fax", length = 15)
	private String fax3;
	
	@Column(name = "Email_Address", length = 70)
	private String emailAddress;
	
	@Column(name = "Web_Site_Url", length = 100)
	private String webSiteUrl;
	
	@Column(name = "Document_Path", length = 255)
	private String documentPath;
	
	@Lob
	@Column(name = "Company_Logo")
	private byte[] logo;
	
	@OneToMany(mappedBy = "firmId", cascade = {CascadeType.ALL })
	private Collection<CaseDocket> caseDocket;
	
	@OneToMany(mappedBy = "firmId", cascade = {CascadeType.ALL })
	private Collection<FirmUser> firmUsers;
	
	@OneToMany(mappedBy = "firmId", cascade = {CascadeType.ALL })
	private Collection<CaseStatus> caseStatus;
	
	@OneToMany(mappedBy = "firmId", cascade = {CascadeType.ALL })
	private Collection<CaseType> caseType;
	
	@Column(name = "Email_Config_ID")
	protected Integer emailConfigId;
	
	@ManyToOne
	@JoinColumn(name = "Email_Config_ID", insertable = false, updatable = false)
	private EmailConfig emailConfig;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFirmId() {
		return firmId;
	}

	public void setFirmId(Integer firmId) {
		this.firmId = firmId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Collection<Address> addresses) {
		this.addresses = addresses;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public String getFax1() {
		return fax1;
	}

	public void setFax1(String fax1) {
		this.fax1 = fax1;
	}

	public String getFax2() {
		return fax2;
	}

	public void setFax2(String fax2) {
		this.fax2 = fax2;
	}

	public String getFax3() {
		return fax3;
	}

	public void setFax3(String fax3) {
		this.fax3 = fax3;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getWebSiteUrl() {
		return webSiteUrl;
	}

	public void setWebSiteUrl(String webSiteUrl) {
		this.webSiteUrl = webSiteUrl;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public Collection<CaseDocket> getCaseDocket() {
		return caseDocket;
	}

	public void setCaseDocket(Collection<CaseDocket> caseDocket) {
		this.caseDocket = caseDocket;
	}

	public Collection<FirmUser> getFirmUsers() {
		return firmUsers;
	}

	public void setFirmUsers(Collection<FirmUser> firmUsers) {
		this.firmUsers = firmUsers;
	}

	public EmailConfig getEmailConfig() {
		return emailConfig;
	}

	public void setEmailConfig(EmailConfig emailConfig) {
		this.emailConfig = emailConfig;
	}

	public Integer getEmailConfigId() {
		return emailConfigId;
	}

	public void setEmailConfigId(Integer emailConfigId) {
		this.emailConfigId = emailConfigId;
	}

	public String getDocumentPath() {
		return documentPath;
	}

	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}

	public String getFirmDomain() {
		return firmDomain;
	}

	public void setFirmDomain(String firmDomain) {
		this.firmDomain = firmDomain;
	}

	public Collection<CaseStatus> getCaseStatus() {
		return caseStatus;
	}

	public void setCaseStatus(Collection<CaseStatus> caseStatus) {
		this.caseStatus = caseStatus;
	}

	public Collection<CaseType> getCaseType() {
		return caseType;
	}

	public void setCaseType(Collection<CaseType> caseType) {
		this.caseType = caseType;
	}
}
