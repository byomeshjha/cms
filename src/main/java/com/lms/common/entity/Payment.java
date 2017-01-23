package com.lms.common.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Payment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PaymentSequence")
	@SequenceGenerator(name="PaymentSequence", sequenceName="payment_sequence", initialValue=100, allocationSize=1) 
	@Column(name = "ID")
	private Integer id;
	
	@Column(name="Payment_Amount")
	private Double paymentAmount;
	
	@Column(name="Date_Of_Payment")
	private Timestamp dateOfPayment;
	
	@Column(name="Payment_Mode")
	private String paymentMode;
	
	@Column(name="Payment_Note", length = 200)
	private String paymentNote;
	
	@Column(name="Payment_Received")
	private Boolean paymentReceived;
	
	@ManyToOne
	private FirmUser paymentReceivedBy;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public Timestamp getDateOfPayment() {
		return dateOfPayment;
	}

	public void setDateOfPayment(Timestamp dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getPaymentNote() {
		return paymentNote;
	}

	public void setPaymentNote(String paymentNote) {
		this.paymentNote = paymentNote;
	}

	public Boolean getPaymentReceived() {
		return paymentReceived;
	}

	public void setPaymentReceived(Boolean paymentReceived) {
		this.paymentReceived = paymentReceived;
	}

	public FirmUser getPaymentReceivedBy() {
		return paymentReceivedBy;
	}

	public void setPaymentReceivedBy(FirmUser paymentReceivedBy) {
		this.paymentReceivedBy = paymentReceivedBy;
	}
	

}
