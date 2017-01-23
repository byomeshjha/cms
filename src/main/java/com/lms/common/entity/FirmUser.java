package com.lms.common.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(value = { "addresses", "caseDocket" })
@Entity
public class FirmUser extends BaseUser {
	
	@Column(name = "Firm_ID")
	protected Integer firmId;

	@ManyToOne
	@JoinColumn(name = "Firm_ID", insertable = false, updatable = false)
	private Firm firm;
	
	
	@OneToMany(mappedBy = "caseManagerId", cascade = {CascadeType.ALL })
	private Collection<CaseDocket> caseDocket;

	@Column(name = "User_type")
	@Enumerated
	private UserType userType = UserType.FIRM_USER;
	

	@Override
	public UserType getUserType() {
		return userType;
	}

	@Override
	public void setUserType(UserType userType) {
		this.userType = UserType.FIRM_USER;
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

	public Collection<CaseDocket> getCaseDocket() {
		return caseDocket;
	}

	public void setCaseDocket(Collection<CaseDocket> caseDocket) {
		this.caseDocket = caseDocket;
	}
}
