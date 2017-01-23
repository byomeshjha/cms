package com.lms.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


@Entity
public class Maintenance {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AppMaintenance")
	@SequenceGenerator(name="AppMaintenance", sequenceName="app_maintenance_sequence", initialValue=100, allocationSize=1)
	@Column(name = "ID")
	private Integer id;
	@Column(name="Report_Retention_Days")
	private Integer retentionDays;
	@Column(name="Run_At_Hour")
	private Integer runAt;
	@Column(name="On_Off")
	private Boolean onOff;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRetentionDays() {
		return retentionDays;
	}
	public void setRetentionDays(Integer retentionDays) {
		this.retentionDays = retentionDays;
	}
	public Integer getRunAt() {
		return runAt;
	}
	public void setRunAt(Integer runAt) {
		this.runAt = runAt;
	}
	public Boolean getOnOff() {
		return onOff;
	}
	public void setOnOff(Boolean onOff) {
		this.onOff = onOff;
	}
	
	

}
