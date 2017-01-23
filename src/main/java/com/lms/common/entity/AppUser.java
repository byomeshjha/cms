package com.lms.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;


@Entity
public class AppUser extends BaseUser {
	
	@Column(name = "User_Type")
	@Enumerated
	private UserType userType = UserType.APP_USER;

	@Override
	public UserType getUserType() {
		return userType;
	}

	@Override
	public void setUserType(UserType userType) {
		this.userType = UserType.APP_USER;
	}
}
