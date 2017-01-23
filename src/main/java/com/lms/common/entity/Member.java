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
public class Member extends Biographic {
	
	@Column(name = "Member_ID", insertable = false, updatable = false)
	private Integer id;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MemberSequence")
	@SequenceGenerator(name="MemberSequence", sequenceName="member_sequence", initialValue=100, allocationSize=1) 
	@Column(name = "Member_ID")
	private Integer memberId;
	
	@Column(name = "Member_Client_ID")
	protected Integer clientId;
	
	@ManyToOne
	@JoinColumn(name = "Member_Client_ID", insertable = false, updatable = false)
	private Client client;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
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
	
	

}
