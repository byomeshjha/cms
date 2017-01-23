package com.lms.common.bean;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Status {

	private String status;

	//@XmlTransient
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	} 

	@Override
	public String toString() {
		return "Status [status=" + status + "]";
	}
}
