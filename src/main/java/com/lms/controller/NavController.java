package com.lms.controller;

import java.util.ArrayList;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lms.common.entity.Address;
import com.lms.common.entity.CaseDocket;
import com.lms.common.entity.Client;
import com.lms.common.entity.FirmUser;
import com.lms.common.entity.Member;
import com.lms.ejb.JpaServer;


@Controller
public class NavController extends BaseController {
	
	private Logger logger = Logger.getLogger(NavController.class.getName());
	
	@EJB(mappedName = "java:app/lms/JpaServer")
	private JpaServer jpaServer;
	
	//@EJB(mappedName = "java:app/lms/ConfigEjb")
	//private ConfigEjb configEjb;
	
	
	@RequestMapping(value = {"login"}, method = { RequestMethod.GET })
	public String login(Model model) {
		FirmUser o = new FirmUser();    
        model.addAttribute("userForm", o);
		return "login";
	}
	
	@RequestMapping(value = {"", "home"}, method = { RequestMethod.GET })
	public String home(Model model) {
		model.addAttribute("NAV_SELECTED", "home");
		return "home";
	}
	
	@RequestMapping(value = "case/list", method = { RequestMethod.GET })
	public String caseList(Model model) {
		model.addAttribute("NAV_SELECTED", "case/list");
		
		return "caseList";
	}
	
	@RequestMapping(value = "case/register", method = { RequestMethod.GET })
	public String registerCase(Model model, HttpServletRequest request) {
		try {
			CaseDocket cd = initializeFormObject(CaseDocket.class, null, request);
			model.addAttribute("caseRegistrationForm", cd);
		}
		catch(IllegalAccessException|InstantiationException e) {
			logger.severe(e.getLocalizedMessage());
			e.printStackTrace();
		}
        model.addAttribute("NAV_SELECTED", "case/register");
        
		return "registerCase";
	}
	
	@RequestMapping(value = "client/list", method = { RequestMethod.GET })
	public String clientList(Model model) {
		model.addAttribute("NAV_SELECTED", "client/list");
		return "clientList";
	}
	
	@RequestMapping(value = "calendar/schedules", method = { RequestMethod.GET })
	public String calendarSchedules(Model model) {
		model.addAttribute("NAV_SELECTED", "calendar/schedules");
		return "schedules";
	}
	
	@RequestMapping(value = "client/register", method = { RequestMethod.GET })
	public String registerClient(Model model) {
		Client o = new Client();
		o.setMembers(new ArrayList<>());
		Member m = new Member();
		o.getMembers().add(m);
		Address a = new Address();
		o.setAddresses(new ArrayList<>());
		o.getAddresses().add(a);
        model.addAttribute("clientRegistrationForm", o);
        model.addAttribute("NAV_SELECTED", "client/register");
        
		return "registerClient";
	}
	
	@RequestMapping(value = {"{firm}/login"}, method = { RequestMethod.GET })
	public String firmLogin(Model model, @PathVariable String firm) {
		
		model.addAttribute("FIRM", firm);
		
		return "firmlogin";
	}
	

	
	@RequestMapping(value = "org", method = { RequestMethod.GET })
	public String org(Model model) {
		return "org";
	}
	
	@RequestMapping(value = "user", method = { RequestMethod.GET })
	public String users(Model model) {
		return "user";
	}
	
	@RequestMapping(value = "setup", method = { RequestMethod.GET })
	public String setup(Model model) {
		return "reportsetup";
	}
	
	@RequestMapping(value = "maint", method = { RequestMethod.GET })
	public String maint(Model model) {
		return "maintenance";
	}
	
	@RequestMapping(value = "replog", method = { RequestMethod.GET })
	public String repLog(Model model) {
		return "reportlog";
	}
	
	@RequestMapping(value = "samples/emp", method = { RequestMethod.GET })
	public String samplesEmp(Model model) {
		return "emp";
	}

	@RequestMapping(value = "samples/sal", method = { RequestMethod.GET })
	public String samplesSal(Model model) {
		return "sal";
	}
}
