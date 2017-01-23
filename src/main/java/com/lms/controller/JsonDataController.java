package com.lms.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lms.util.JSONUtil;

@Controller
public class JsonDataController {

	@RequestMapping(value = "sample/reports/data/{name}", method = { RequestMethod.GET })
	public @ResponseBody String jsonData(@PathVariable String name,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String dataFileName = getDataFileName(name);
		File dataFile = getDataFile(dataFileName);
		String json = getStringData(dataFile);

		return json;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "sample/reports/data/{name}/{sd}/{ed}", method = { RequestMethod.GET })
	public @ResponseBody Map<String, List> jsonDataWithParameters(@PathVariable String name,
			@PathVariable String sd, @PathVariable String ed,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ParseException {
		String dataFileName = getDataFileName(name);
		File dataFile = getDataFile(dataFileName);
		String jsonData = getStringData(dataFile);
		
		final DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
		Date startDate = df.parse(sd);
		Date endDate = df.parse(ed);
		
		Map<String, List> result = new HashMap<>();
		List<Map<String, Object>> filteredEmployeeList = new ArrayList<>();
		JSONUtil<Map> util = new JSONUtil<>(Map.class);
		Map raw = util.createObjectFromJson(jsonData);
		List<Map> employees = (List)raw.get("employees");
		for(Map map : employees) {
			String dateOfHire = (String)map.get("dateOfHire");
			dateOfHire = dateOfHire.replaceAll("\\/", "-");
			Date doh = df.parse(dateOfHire);
			if(((doh.after(startDate) && doh.before(endDate))) || (startDate.compareTo(doh) <= 0 && endDate.compareTo(doh) >= 1)) {
				filteredEmployeeList.add(map);
			}
		}
		final Comparator<Map> HIRE_ORDER = new Comparator<Map>() {
			public int compare(Map e1, Map e2) {
				try {
					String s1 = ((String)e2.get("dateOfHire")).replaceAll("\\/", "-");
					String s2 = ((String)e1.get("dateOfHire")).replaceAll("\\/", "-");
					Date d1 = df.parse(s1);
					Date d2 = df.parse(s2);
					return d2.compareTo(d1);
				}
				catch(Exception e) {e.printStackTrace();}
				return 0;
			}
		};
		Collections.sort(filteredEmployeeList, HIRE_ORDER);
		result.put("employees", filteredEmployeeList);

		return result;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "sample/reports/data/{name}/employees", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody List<Map<String, String>> employeeList(@PathVariable String name,
			HttpServletRequest request, HttpServletResponse response)
			throws JRException, IOException {
		
		String dataFileName = getDataFileName(name);
		File dataFile = getDataFile(dataFileName);
		String jsonData = getStringData(dataFile);
		
		List<Map<String, String>> empList = new ArrayList<>();
		JSONUtil<Map> util = new JSONUtil<>(Map.class);
		Map raw = util.createObjectFromJson(jsonData);
		List<Map> employees = (List)raw.get("employees");
		for(Map map : employees) {
			Map<String, String> empMap = new HashMap<>();
	    	String id = ((Integer)map.get("id")).toString();
	    	String empName = (String)map.get("name");
	        System.out.println("id: " + id + ", name: " + empName);
	        empMap.put("id", id);
	        empMap.put("text", empName);
	        empList.add(empMap);
		}
		
		return empList;
	}
	
	private String getDataFileName(String name) {
		String dataFileName = "/sample/data/";
		switch (name) {
			case "emp":
				dataFileName += "emp.json";
				break;
			case "sal":
				dataFileName += "sal.json";
				break;
			default:
				break;
		}
		
		return dataFileName;
	}
	
	private File getDataFile(String dataFileName) {
		try {
			ClassPathResource cpr = new ClassPathResource(dataFileName);
			return cpr.getFile();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String getStringData(File dataFile) throws IOException {
		String json = "{}";
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(dataFile));) {
			StringBuilder stringBuilder = new StringBuilder();
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}
			json = stringBuilder.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException(e.getMessage());
		}
		
		return json;
	}

}
