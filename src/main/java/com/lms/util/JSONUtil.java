package com.lms.util;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



public class JSONUtil<T> {
	
	protected final Logger log = Logger.getLogger(JSONUtil.class);
	private Class<T> clazz;
	
	public JSONUtil(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	public T createObjectFromJson(String jsonData) 
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper om = new ObjectMapper();
		T t = om.readValue(jsonData, clazz);
		
		return t;
	}
	
	public String createJsonFromObject(T t) 
			throws JsonMappingException, IOException{
		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(t);
		
		return json;
	}
	
	public static String prettifyJson(String json) 
			throws JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
		
		return json;
	}


	public Class<T> getClazz() {
		return clazz;
	}


	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

}
