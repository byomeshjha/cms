package com.lms.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lms.util.JSONUtil;

public class ScheduleController extends BaseController {
	
	private Logger logger = Logger.getLogger(ScheduleController.class.getName());
	
	
	@RequestMapping(value = "/schedules/all", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String schedules(HttpServletRequest request) throws IOException {
		
		String json2 = "";
		try {
			//@SuppressWarnings("rawtypes")
			//JSONUtil<GridJson> jsonUtil = new JSONUtil<>(GridJson.class);
			//json2 = jsonUtil.createJsonFromObject();
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
			logger.severe(e.getLocalizedMessage());
		}
		
		System.out.println("JSON: " + json2);
	
		return json2;
		
	}

}
