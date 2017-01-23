package com.lms.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lms.common.bean.DataGridBean;
import com.lms.common.bean.PropertyGridBean;
import com.lms.common.entity.EmailConfig;
import com.lms.common.entity.Maintenance;
import com.lms.ejb.ConfigEjb;


@Controller
public class ConfigController {
	
	private Logger logger = Logger.getLogger(ConfigController.class.getName());
	
	@EJB(mappedName = "java:app/lms/ConfigEjb")
	private ConfigEjb configEjb;
	
	
	@RequestMapping(value = "maintdata", method = { RequestMethod.GET })
	public @ResponseBody Maintenance maintData(
			HttpServletRequest request, HttpServletResponse response)
			throws JRException, IOException {
		
		logger.info("maintdata called");
		
		Maintenance maint = configEjb.getMaintenance();
		
		return maint;
	}
	
	@RequestMapping(value = "smtpdata", method = { RequestMethod.GET })
	public @ResponseBody EmailConfig smtpData(
			HttpServletRequest request, HttpServletResponse response)
			throws JRException, IOException {
		
		logger.info("smtpdata called");
		
		EmailConfig o = configEjb.getEmailConfig();
		
		return o;
	}
	
	@RequestMapping(value = "maintprops", method = { RequestMethod.GET })
	public @ResponseBody DataGridBean maintProps(
			HttpServletRequest request, HttpServletResponse response)
			throws JRException, IOException {
		
		List<PropertyGridBean> list = new ArrayList<>();
		logger.info("maintprops called");
		Maintenance maint = configEjb.getMaintenance();
		String group = "Maintenance";
		String onOff = (maint != null && maint.getOnOff() != null) ? maint.getOnOff().toString() : "Not Set";
		String scheduledTime = (maint != null && maint.getRunAt() != null) ? maint.getRunAt().toString() : "Not Set";
		String retentionDays = (maint != null && maint.getRetentionDays() != null) ? maint.getRetentionDays().toString() : "Not Set";
		addBean("Scheduler On/Off", onOff, group, list);
		addBean("Scheduler Time", scheduledTime, group, list);
		addBean("Data Retention Days", retentionDays, group, list);
		
		EmailConfig smtp = configEjb.getEmailConfig();
		String smtpGroup = "SMTP";
		String smtpOnOff = (smtp != null && smtp.getOnOff() != null) ? smtp.getOnOff().toString() : "Not Set";
		String smtps = (smtp != null && smtp.getSmtps() != null) ? smtp.getSmtps().toString() : "Not Set";
		String host = (smtp != null && smtp.getHost() != null) ? smtp.getHost() : "Not Set";
		String port = (smtp != null && smtp.getPort() != null) ? smtp.getPort().toString() : "Not Set";
		String from = (smtp != null && smtp.getFrom() != null) ? smtp.getFrom() : "Not Set";
		String friendly = (smtp != null && smtp.getFromFriendly() != null) ? smtp.getFromFriendly() : "Not Set";
		String user = (smtp != null && smtp.getUserName() != null) ? smtp.getUserName() : "Not Set";
		addBean("SMTP On/Off", smtpOnOff, smtpGroup, list);
		addBean("SMTP(S)", smtps, smtpGroup, list);
		addBean("Host", host, smtpGroup, list);
		addBean("Port", port, smtpGroup, list);
		addBean("Email From", from, smtpGroup, list);
		addBean("Friendly Name", friendly, smtpGroup, list);
		addBean("SMTP Connection User", user, smtpGroup, list);

		DataGridBean dgb = new DataGridBean();
		dgb.setTotal(list.size());
		dgb.setRows(list);
		
		return dgb;
	}
	

	@RequestMapping(value = "addmaint", method = { RequestMethod.POST })
	public @ResponseBody Maintenance repEdit(@RequestBody Maintenance maint,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		maint = configEjb.saveMaintenance(maint);
		
		return maint;
	}
	
	
	@RequestMapping(value = "addsmtp", method = { RequestMethod.POST })
	public @ResponseBody EmailConfig repEdit(@RequestBody EmailConfig o,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		o = configEjb.saveEmailConfig(o);
		
		return o;
	}
	
	private void addBean(String name, String value, String group, List<PropertyGridBean> list) {
		PropertyGridBean b = new PropertyGridBean();
		b.setName(name);
		b.setValue(value);
		b.setGroup(group);
		
		list.add(b);
	}
}