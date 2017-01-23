package com.lms.common.entity;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;


@MappedSuperclass
public abstract class BaseUser extends BaseEntity {
	
	public enum UserType {
		APP_USER, FIRM_USER
	}
	
	@Column(name = "User_ID", insertable = false, updatable = false)
	private Integer id;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="UserSequence")
	@SequenceGenerator(name="UserSequence", sequenceName="user_sequence", initialValue=100, allocationSize=1) 
	@Column(name = "User_ID")
	private Integer userId;
	
	@Column(name="Email_Address", length = 35)
	private String emailAddress;
	
	@Column(name = "User_Password")
	private String password;
	
	@Transient
	private String passwordMatch;
	
	@Column(name="First_Name", length = 15)
	private String firstName;
	
	@Column(name="Middle_Name", length = 15)
	private String middleName;
	
	@Column(name="Last_Name", length = 20)
	private String lastName;
	
	@Column(name="Office_Phone", length = 15)
	private String officePhone;
	
	@Column(name="Cell_Phone", length = 15)
	private String cellPhone;
	
	@Column(name="Description", length = 500)
	private String description;
	
	@Column(name = "User_Role")
	@Enumerated(EnumType.STRING)
	protected UserRole userRole;
	
	@Column(name = "User_Active")
	protected Boolean userActive;
	
	public abstract UserType getUserType();
	public abstract void setUserType(UserType userType);

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public UserRole getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	public Boolean getUserActive() {
		return userActive;
	}
	public void setUserActive(Boolean userActive) {
		this.userActive = userActive;
	}
	public String getPasswordMatch() {
		return passwordMatch;
	}
	public void setPasswordMatch(String passwordMatch) {
		this.passwordMatch = passwordMatch;
	}
}
