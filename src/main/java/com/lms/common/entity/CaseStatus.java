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
public class CaseStatus extends BaseEntity {
	
	@Column(name = "Case_Status_ID", insertable = false, updatable = false)
	private Integer id;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CaseStatusSequence")
	@SequenceGenerator(name="CaseStatusSequence", sequenceName="case_status_sequence", initialValue=100, allocationSize=1) 
	@Column(name = "Case_Status_ID")
	private Integer caseStatusId;
	
	@Column(name = "Status", length = 15)
	private String status;
	
	@Column(name = "Case_Status_Desc", length = 255)
	private String CaseStatusDesc;
	
	@Column(name = "Firm_ID")
	protected Integer firmId;
	
	@ManyToOne
	@JoinColumn(name = "Firm_ID", insertable = false, updatable = false)
	private Firm firm;
	
	@OneToMany(mappedBy = "caseStatusId")
	private Collection<CaseDocket> caseDocket;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCaseStatusId() {
		return caseStatusId;
	}

	public void setCaseStatusId(Integer caseStatusId) {
		this.caseStatusId = caseStatusId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCaseStatusDesc() {
		return CaseStatusDesc;
	}

	public void setCaseStatusDesc(String caseStatusDesc) {
		CaseStatusDesc = caseStatusDesc;
	}

	public Collection<CaseDocket> getCaseDocket() {
		return caseDocket;
	}

	public void setCaseDocket(Collection<CaseDocket> caseDocket) {
		this.caseDocket = caseDocket;
	}
}
