package com.lms.common.bean;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.lms.common.entity.FirmUser;


@Named
@SessionScoped
public class SessionUserInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(SessionUserInfo.class.getName());
	private FirmUser firmUser;
	
	public FirmUser getSessionUser() {
		logger.info("** getting firmuser from session bean");
		return firmUser;
	}
	public void setSessionUser(FirmUser firmUser) {
		logger.info("** setting firmuser in session bean");
		this.firmUser = firmUser;
	}

}
