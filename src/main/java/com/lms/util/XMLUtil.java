package com.lms.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class XMLUtil<T> {
	
	private Class<T> clazz;
	
	public XMLUtil(Class<T> clazz) {
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

}
