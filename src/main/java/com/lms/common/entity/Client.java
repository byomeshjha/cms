package com.lms.common.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "members", "addresses", "caseDockets" })
@Entity
public class Client extends Biographic {
	
	@Column(name = "Client_ID", insertable = false, updatable = false)
	private Integer id;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ClientSequence")
	@SequenceGenerator(name="ClientSequence", sequenceName="client_sequence", initialValue=100, allocationSize=1) 
	@Column(name = "Client_ID")
	private Integer clientId;
	
	@OneToMany(mappedBy = "clientId")
	private Collection<CaseDocket> caseDockets;
	
	@Column(name = "Fact_Sheet", length = 2000)
	private String factSheet;
	
	@Column(name = "Existing_Client")
	private Boolean existingClient;
	
	@Column(name = "Referred_By", length = 55)
	private String referredBy;

	@OneToMany(mappedBy = "clientId", cascade = {CascadeType.ALL})
	private Collection<Member> members;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	@JsonIgnore
	public Collection<CaseDocket> getCaseDocket() {
		return caseDockets;
	}

	public void setCaseDocket(Collection<CaseDocket> caseDockets) {
		this.caseDockets = caseDockets;
	}

	public String getFactSheet() {
		return factSheet;
	}

	public void setFactSheet(String factSheet) {
		this.factSheet = factSheet;
	}

	public Boolean getExistingClient() {
		return existingClient;
	}

	public void setExistingClient(Boolean existingClient) {
		this.existingClient = existingClient;
	}

	public String getReferredBy() {
		return referredBy;
	}

	public void setReferredBy(String referredBy) {
		this.referredBy = referredBy;
	}

	public Collection<Member> getMembers() {
		return members;
	}

	public void setMembers(Collection<Member> members) {
		this.members = members;
	}

	
}
