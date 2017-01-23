package com.lms.test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.lms.common.bean.ReportServiceException;
import com.lms.util.Crypto.Base64;

@Repository(value = "TestHttpClient")
public class TestHttpClient {
	
	protected final Logger log = Logger.getLogger(TestHttpClient.class);
	
	@Autowired
	private Environment env;
	
	/**
	 * Jasper login request to get cookie
	 * @return
	 * @throws Exception
	 */
	public String sendJasperLoginRequest(String user, String password) throws ReportServiceException {
		String url = baseUrl();
		System.out.println("send jasper login request base url: " + url);
		HttpGet getMethod = null;
		String cookie = null;
		try {
			String cookie_uri = "jasperserver/rest/login?j_username="+user+"&j_password="+password;
			System.out.println("send jasper report request cookie uri: " + cookie_uri);
			getMethod = new HttpGet(url + cookie_uri);
			getMethod.addHeader("Cookie", cookie);
			CloseableHttpClient  httpClient = HttpClients.createDefault();
		    CloseableHttpResponse httpResponse = httpClient.execute(getMethod);
			log.info("Jasper report output http status: " + httpResponse.getStatusLine().getStatusCode());
			if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				Header[] headers = httpResponse.getAllHeaders();
				for(Header header:headers) {
					System.out.println("Header Name: " + header.getName());
					System.out.println("Header Value: " + header.getValue());
					if(header.getName().equals("Set-Cookie")) {
						cookie = header.getValue();
						break;
					}
				}
				String response = null;
				try {
					response = readResponse(httpResponse);
					EntityUtils.consume(httpResponse.getEntity());
				} finally {
					httpResponse.close();
				}
				log.info("Jasper Login Request response: " + response);
			} else {
				String response = null;
				try {
					response = readResponse(httpResponse);
					EntityUtils.consume(httpResponse.getEntity());
				} finally {
					httpResponse.close();
				}
				log.info("Jasper Login Request response: " + response);
			}
		}
		catch(IOException e) {
			throw new ReportServiceException("Jasper Login Request io exception", e);
		} 
		
		return cookie;
	}
	
	/**
	 * Jasper published report request with cookie.
	 * @param cookie
	 * @return
	 * @throws Exception
	 */
	public String sendJasperReportRequest(String reportUri, String cookie, String user, String password) throws ReportServiceException {
		String url = baseUrl();
		System.out.println("send jasper report request base url: " + url);
		HttpGet getMethod = null;
		String retval = null;
		try {
			System.out.println("send jasper report request report uri: " + reportUri);
			getMethod = new HttpGet(reportUri);
			String encoded = Base64.encode(user + ":" + password);
			getMethod.addHeader("Authorization", "Basic "+encoded);
			getMethod.addHeader("Cookie", cookie);
			CloseableHttpClient  httpClient = HttpClients.createDefault();
		    CloseableHttpResponse httpResponse = httpClient.execute(getMethod);
			log.info("Jasper report output http status: " + httpResponse.getStatusLine().getStatusCode());
			if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String response = null;
				try {
					response = readResponse(httpResponse);
					EntityUtils.consume(httpResponse.getEntity());
				} finally {
					httpResponse.close();
				}
				log.info("Jasper Login Request response: " + response);
			} else {
				String response = null;
				try {
					response = readResponse(httpResponse);
					EntityUtils.consume(httpResponse.getEntity());
				} finally {
					httpResponse.close();
				}
				log.info("Jasper Login Request response: " + response);
			}
		}
		catch(IOException e) {
			throw new ReportServiceException("Jasper Report Request io exception", e);
		} 
		return retval;
	}

	
	private String baseUrl() {
		String scheme = "http";
		String host = "localhost";
		String port = "8080";//"7001";
		//String uri = "jasperserver/rest_v2/reports/root/Reports/Condo%20Data.pdf?page=1";
		if(env != null) {
			log.info("populating base url from env variables.");
			scheme = env.getProperty("jasper.service.scheme");
			host = env.getProperty("jasper.service.host");
			port = env.getProperty("jasper.service.port");
		}
		
		
		String url = scheme + "://" + host + ":" + port + "/";
		
		return url;
	}
	
	protected String readResponse(HttpResponse httpResponse) throws IOException {
		String response = null;
		HttpEntity entity = httpResponse.getEntity();
		BufferedReader rd = new BufferedReader(
				new InputStreamReader(entity.getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		response = result.toString();
		
		return response;
	}
	
	protected byte[] readBody(HttpResponse httpResponse) throws IOException {
		byte[] retval = null;
		HttpEntity entity = httpResponse.getEntity();
		InputStream in = entity.getContent();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buff = new byte[8192];
		int read = 0;
		while((read = in.read(buff, 0, 8192)) != -1) {
			baos.write(buff, 0, read);
		}
		retval = baos.toByteArray();

		return retval;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		String user = "jasperadmin";
		String password = "jasperadmin";
		String reportUri = "jasperserver/rest_v2/reports/reports/Condo_Data.pdf";
		TestHttpClient thc = new TestHttpClient();
		String cookie = thc.sendJasperLoginRequest(user, password);
		String response = thc.sendJasperReportRequest(reportUri, cookie, user, password);
		System.out.println("Main Response: " + response);
	}

}
