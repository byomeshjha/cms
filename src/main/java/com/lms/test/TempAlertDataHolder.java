package com.lms.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TempAlertDataHolder {
	
	private static Map<String, List<String>> alertMap = new HashMap<>();

	public static Map<String, List<String>> get() {
		return alertMap;
	}
	
	public static List<String> get(String id) {
		return alertMap.get(id);
	}

	public static void put(String key, List<String> value) {
		TempAlertDataHolder.alertMap.put(key, value);
	}
	
	

}
