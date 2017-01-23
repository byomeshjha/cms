package com.lms.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lms.common.entity.FirmUser;

public class SecurityInterceptor extends HandlerInterceptorAdapter {
	
	private Logger logger = Logger.getLogger(SecurityInterceptor.class.getName());

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
    	
    	logger.info("intercepted");
    	
    	String uri = request.getRequestURI(); 
    	// Avoid a redirect loop for some urls
		if( !uri.equals("/login") &&
			!uri.equals("/firmlogin") &&
		    !uri.contains("/api/"))
		{
			HttpSession session = request.getSession();
	    	FirmUser loggedInUser = (FirmUser)session.getAttribute("LOGGEDIN_USER");
	    	if(loggedInUser == null) {
	    		logger.info("* not logged in");
	    		String ctx = request.getContextPath();
	    		response.setHeader("Cache-Control","no-cache");//HTTP 1.1 
	    		response.setHeader("Pragma","no-cache");//HTTP 1.0 
	    		response.setDateHeader("Expires", 0);//prevents caching at the proxy server 
	    		response.sendRedirect(ctx + "/login");
	    		return false;
	    	}
		}
		
		logger.info("** logged in");
        
        return true;
    }

}
