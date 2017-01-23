package com.lms.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XmlConfig<T> {
	
	private Class<T> clazz;
	
	public XmlConfig(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	public String objectToXml(T t) throws IOException, JAXBException, UnsupportedEncodingException {
		String xml = null; 
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
			JAXBContext context = JAXBContext.newInstance(clazz);
			Marshaller m = context.createMarshaller();
		    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(t, baos);
			xml = new String(baos.toByteArray(), "UTF-8");
		}
		
		return xml;
	}
	
	@SuppressWarnings("unchecked")
	public T xmlToObject(String xml) throws IOException, JAXBException, UnsupportedEncodingException {
		T t = null;
		try (StringReader reader = new StringReader(xml);) {
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller m = context.createUnmarshaller();
			t = (T) m.unmarshal(reader);
		}
		
		return t;
	}
	
	
	public T xmlToObject(File file) throws IOException, JAXBException, UnsupportedEncodingException {
		try(BufferedReader bufferedReader = new BufferedReader(
				new FileReader(file));)
		{
			StringBuilder stringBuilder = new StringBuilder();
			String line = null;
			while((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}
			
			return xmlToObject(stringBuilder.toString());
		}
		catch(JAXBException e) {
			throw e;
		}
		catch(UnsupportedEncodingException e) {
			throw e;
		}
		catch(IOException e) {
			throw e;
		}
	}

}
