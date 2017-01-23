package com.lms.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

@Entity
public class CaseDocument extends BaseEntity {
	
	@Column(name = "Case_Document_ID", insertable = false, updatable = false)
	private Integer id;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DocumentSequence")
	@SequenceGenerator(name="DocumentSequence", sequenceName="document_sequence", initialValue=100, allocationSize=1) 
	@Column(name = "Case_Document_ID")
	private Integer caseDocumentId;
	
	@Column(name="Document_Type", length = 125)
	private String documentType;
	
	@Transient
	private String mappedType;
	
	@Column(name="Document_Name", length = 255)
	private String documentName;
	
	@Column(name="Document_Path", length = 255)
	private String documentPath;
	
	@Column(name="Document_Size")
	private Long documentSize;
	
	@Column(name = "Case_Docet_ID")
	private Integer caseDocketId;

	@ManyToOne
	@JoinColumn(name = "Case_Docet_ID", insertable = false, updatable = false)
	private CaseDocket caseDocket;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCaseDocumentId() {
		return caseDocumentId;
	}

	public void setCaseDocumentId(Integer caseDocumentId) {
		this.caseDocumentId = caseDocumentId;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentPath() {
		return documentPath;
	}

	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
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

	public Long getDocumentSize() {
		return documentSize;
	}

	public void setDocumentSize(Long documentSize) {
		this.documentSize = documentSize;
	}

	public String getMappedType() {
		return mappedType;
	}

	public void setMappedType(String mappedType) {
		this.mappedType = mappedType;
	}
}
