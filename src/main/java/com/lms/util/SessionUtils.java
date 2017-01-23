package com.lms.util;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.lms.common.entity.Firm;
import com.lms.common.entity.FirmUser;

public class SessionUtils {
	
	private static Logger logger = Logger.getLogger(SessionUtils.class.getName());
	
	
	public static String getDocumentPathForFirm(HttpServletRequest request) {
		String firmDocumentPath = System.getProperty("user.home");
		HttpSession session = (HttpSession) request.getSession();
		Object lu = session.getAttribute("LOGGEDIN_USER");
		if(lu instanceof FirmUser) {
			FirmUser fu = (FirmUser)lu;
			Firm firm = fu.getFirm();
			firmDocumentPath = firm.getDocumentPath();
			if(!firmDocumentPath.endsWith("/"))
				firmDocumentPath += "/";
			firmDocumentPath += fu.getLastName();
			firmDocumentPath += ", ";
			firmDocumentPath += fu.getFirstName();
			if(!firmDocumentPath.endsWith("/"))
				firmDocumentPath += "/";
			logger.info(String.format("Document folder is: %s", firmDocumentPath));
		}
		
		return firmDocumentPath;
	}
	
	
	public static Firm getFirmForLoggedinUser(HttpServletRequest request) {
		Firm firm = null;
		HttpSession session = (HttpSession) request.getSession();
		Object lu = session.getAttribute("LOGGEDIN_USER");
		if(lu instanceof FirmUser) {
			FirmUser fu = (FirmUser)lu;
			firm = fu.getFirm();
			
		}
		
		return firm;
	}
	
	public static FirmUser getSessionUser(HttpServletRequest request) {
		FirmUser firmUser = null;
		HttpSession session = (HttpSession) request.getSession();
		Object lu = session.getAttribute("LOGGEDIN_USER");
		if(lu instanceof FirmUser) {
			firmUser = (FirmUser)lu;
		}
		
		return firmUser;
	}

}
