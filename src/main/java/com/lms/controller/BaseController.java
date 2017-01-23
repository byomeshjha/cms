package com.lms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.lms.common.entity.CaseDocket;
import com.lms.common.entity.CaseNote;
import com.lms.common.entity.CaseStatus;
import com.lms.common.entity.CaseType;
import com.lms.common.entity.Firm;
import com.lms.common.entity.FirmUser;
import com.lms.ejb.ConfigEjb;
import com.lms.util.JSONUtil;
import com.lms.util.SessionUtils;

public class BaseController {
	
	private Logger logger = Logger.getLogger(BaseController.class.getName());
	
	@EJB(mappedName = "java:app/lms/ConfigEjb")
	protected ConfigEjb configEjb;
	
	protected void handleResponse(HttpServletResponse response, int status, List<String> messages) {
		try
        {
			@SuppressWarnings("rawtypes")
			JSONUtil<List> util = new JSONUtil<>(List.class);
			String message = util.createJsonFromObject(messages);
			response.setStatus(status);
			response.getWriter().write(message);
			response.flushBuffer();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(HttpServletRequest req, List<String> errors, String viewName) {
		logger.info("Request: " + req.getRequestURL() + " errors: " + errors);
		ModelAndView mav = new ModelAndView();
		mav.addObject("error", errors);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName(viewName);
		
		return mav;
	}
	
	public ModelAndView handleSuccess(HttpServletRequest req, List<String> messages, String viewName) {
		logger.info("Request: " + req.getRequestURL() + " messages: " + messages);

		ModelAndView mav = new ModelAndView();
		mav.addObject("messages", messages);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName(viewName);
		
		return mav;
	}
	
	protected <T> T initializeFormObject(Class<T> c, T t, HttpServletRequest request) 
			throws IllegalAccessException, InstantiationException {
		boolean fullInitialize = false;
		if(t == null) {t = c.newInstance(); fullInitialize = true;}
		if(t instanceof CaseDocket) {
			initializeCaseDocket((CaseDocket)t, request, fullInitialize);
		}
		
		return t;
	}
	
	private void initializeCaseDocket(CaseDocket cd, HttpServletRequest request, boolean fullInitialize) {
		if(fullInitialize) {
			FirmUser caseManager = new FirmUser();
			cd.setCaseManager(caseManager);
			cd.setCaseNotes(new ArrayList<>());
			cd.getCaseNotes().add(new CaseNote());
		}
		
		Firm firm = SessionUtils.getFirmForLoggedinUser(request);
		List<CaseStatus> caseStatusList = configEjb.getCaseStatusList(firm);
		cd.setCaseStatusList(caseStatusList);
		List<CaseType> caseTypeList = configEjb.getCaseTypeList(firm);
		cd.setCaseTypeList(caseTypeList);
	}

}
