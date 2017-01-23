package com.lms.common.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
	
	/**
	 * Home, Office etc.
	 */
	@Column(name = "Address_Type", length = 10)
	private String addressType;
	
	@Column(name = "Address1", length = 50)
	private String line1;
	
	@Column(name = "Address2", length = 50)
	private String line2;
	
	@Column(name = "City", length = 20)
	private String city;
	
	@Column(name = "State", length = 20)
	private String state;
	
	@Column(name = "Zip", length = 10)
	private String zip;
	
	@Column(name = "Country", length = 30)
	private String country;

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	@Override
	public String toString() {
		return "Address [addressType=" + addressType + ", line1=" + line1 + ", line2=" + line2 + ", city=" + city
				+ ", state=" + state + ", zip=" + zip + ", country=" + country + "]";
	}
}
