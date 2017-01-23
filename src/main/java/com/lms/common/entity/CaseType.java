package com.lms.common.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(value = { "caseDocket" })
@Entity
public class CaseType extends BaseEntity {
	
	@Column(name = "Case_Type_ID", insertable = false, updatable = false)
	private Integer id;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CaseTypeSequence")
	@SequenceGenerator(name="CaseTypeSequence", sequenceName="case_type_sequence", initialValue=100, allocationSize=1) 
	@Column(name = "Case_Type_ID")
	private Integer caseTypeId;
	
	@Column(name = "Firm_ID")
	protected Integer firmId;
	
	@ManyToOne
	@JoinColumn(name = "Firm_ID", insertable = false, updatable = false)
	private Firm firm;
	
	@Column(name = "Case_Type_Description", length = 100)
	private String caseTypeDescription;
	
	@Column(name = "Case_Type_Folder", length = 100)
	private String caseTypeFolder;
	
	@OneToMany(mappedBy = "caseTypeId")
	private Collection<CaseDocket> caseDocket;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCaseTypeId() {
		return caseTypeId;
	}

	public void setCaseTypeId(Integer caseTypeId) {
		this.caseTypeId = caseTypeId;
	}

	public String getCaseTypeDescription() {
		return caseTypeDescription;
	}

	public void setCaseTypeDescription(String caseTypeDescription) {
		this.caseTypeDescription = caseTypeDescription;
	}

	public String getCaseTypeFolder() {
		return caseTypeFolder;
	}

	public void setCaseTypeFolder(String caseTypeFolder) {
		this.caseTypeFolder = caseTypeFolder;
	}
}
