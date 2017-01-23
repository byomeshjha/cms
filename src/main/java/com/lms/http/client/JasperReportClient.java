package com.lms.http.client;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.lms.common.bean.JasperResponse;
import com.lms.common.bean.ReportExecution;
import com.lms.common.bean.ReportExecutionContainer;
import com.lms.common.bean.ReportExecutionRequest;
import com.lms.common.bean.ReportParameter;
import com.lms.common.bean.ReportParameterContainer;
import com.lms.common.bean.ReportServiceException;
import com.lms.common.bean.Status;
import com.lms.util.XMLUtil;


public class JasperReportClient {

	private static Logger log = Logger.getLogger(JasperReportClient.class);
	
	final static Charset ENCODING = StandardCharsets.UTF_8;
	final static String CONST_JASPER_USER = "j_username";
	final static String CONST_JASPER_PASS = "j_password";
	final static String SYNC_URI = "rest_v2/reports";
	final static String LOGIN_URI = "rest/login";
	final static String ASYNC_URI = "rest_v2/reportExecutions";
	final static String DETAIL_URI = "rest_v2/reportExecutions/%s";
	final static String STATUS_URI = "rest_v2/reportExecutions/%s/status";
	final static String OUTPUT_URI = "rest_v2/reportExecutions/%s/exports/%s/outputResource";
	
	/**
	 * request a report synchronously
	 * http://<host>:<port>/jasperserver[-pro]/rest_v2/reports/path/to/report.<format>?<arguments>
	 * @param url
	 * @param reportExecutionContainer
	 * @param cookie
	 * @return
	 * @throws ReportServiceException
	 */
	public static byte[] sendJasperSyncRequest(String url, 
			ReportExecutionContainer reportExecutionContainer, String cookie) 
					throws ReportServiceException {
		
		ReportExecutionRequest reportExecutionRequest = 
				reportExecutionContainer.getReportExecutionRequest();
		url = buildFromBaseUrl(url, SYNC_URI);
		url = buildFromBaseUrl(url, reportExecutionRequest.getReportUnitUri());
		log.debug("send status request to url: " + url);
		
		String queryString = buildQueryString(reportExecutionRequest.getParameters());
		if(queryString != null) url += "?" + queryString;
		HttpGet getMethod = null;
		byte[] fileBytes = null;
		try {
			getMethod = new HttpGet(url);
			getMethod.addHeader("Content-Type", "application/xml");
			getMethod.addHeader("Cookie", cookie);
		    CloseableHttpClient  httpClient = HttpClients.createDefault();
		    CloseableHttpResponse httpResponse = httpClient.execute(getMethod);
			log.info("Jasper report exec http status: " + 
					httpResponse.getStatusLine().getStatusCode());
			if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				try {
					fileBytes = readBody(httpResponse);
					EntityUtils.consume(httpResponse.getEntity());
					log.info("Jasper sync report exec response read: " + 
							((fileBytes != null) ? fileBytes.length:0) + " bytes.");
				} finally {
					httpResponse.close();
				}
			}
			else {
				String response = null;
				try {
					response = readResponse(httpResponse);
					EntityUtils.consume(httpResponse.getEntity());
				} finally {
					httpResponse.close();
				}
				log.error("ReportExecution Status Report Failure Response: " + response);
				throw new ReportServiceException(response);
			}
		}
		catch(IOException e) {
			throw new ReportServiceException("Jasper report status request io exception", e);
		}
		finally {
			if(getMethod != null)
				getMethod.releaseConnection();
		}
		
		return fileBytes;
	}


	/**
	 * request a report asynchronously
	 * @param url
	 * @param reportExecutionContainer
	 * @param cookie
	 * @return
	 * @throws ReportServiceException
	 */
	public static JasperResponse sendJasperAsyncRequest(String url, 
			ReportExecutionContainer reportExecutionContainer, String cookie) 
					throws ReportServiceException {
		
		url = buildFromBaseUrl(url, ASYNC_URI);
		log.debug("send request to url: " + url);
		
		HttpPost postMethod = null;
		JasperResponse jasperResponse = new JasperResponse();
		try {
			XMLUtil<ReportExecutionRequest> xu = new XMLUtil<>(ReportExecutionRequest.class);
			String xml = xu.objectToXml(reportExecutionContainer.getReportExecutionRequest());
			log.debug("ReportExecutionRequest Object to XML: " + xml);
			postMethod = new HttpPost(url);
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		    nameValuePairs.add(new BasicNameValuePair("Content-Type", "application/xml"));
		    nameValuePairs.add(new BasicNameValuePair("Cookie", cookie));
		    postMethod.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		    postMethod.setEntity(new StringEntity(xml, ContentType.APPLICATION_XML));
		    CloseableHttpClient httpClient = HttpClients.createDefault();
		    jasperResponse.setTimeExecuted(new Timestamp(System.currentTimeMillis()));
		    CloseableHttpResponse httpResponse = httpClient.execute(postMethod);
			int httpStatusCode = httpResponse.getStatusLine().getStatusCode();
			String response = null;
			try {
				response = readResponse(httpResponse);
				EntityUtils.consume(httpResponse.getEntity());
			} finally {
				httpResponse.close();
			}
			log.info("Jasper status report http status: " + httpStatusCode);
			log.info("Jasper status report response: " + response);
			if(httpStatusCode == HttpStatus.SC_OK) {
				log.info("Jasper report response: " + response);
				XMLUtil<ReportExecution> xu1 = new XMLUtil<>(ReportExecution.class);
				ReportExecution reportExecution = xu1.xmlToObject(response);
				jasperResponse.setSuccessful(true);
				jasperResponse.setReportExecution(reportExecution);
				jasperResponse.setResponseRaw(response);
				log.info("ReportExecution Response XML to Class: " + reportExecution.toString());
			}
			else {
				jasperResponse.setSuccessful(false);
				jasperResponse.setResponseRaw(response);
			}
		}
		catch(IOException e) {
			throw new ReportServiceException("Jasper report request io exception", e);
		}
		catch(JAXBException e) {
			throw new ReportServiceException("Jasper report request java xml binding exception", e);
		}
		finally {
			if(postMethod != null)
				postMethod.releaseConnection();
		}
		
		return jasperResponse;
	}
	
	
	/**
	 * get the status of a report request
	 * @param reportId
	 * @param cookie
	 * @return
	 * @throws ReportServiceException
	 */
	public static Status sendJasperStatusRequest(String url, String reportId,
			String cookie) throws ReportServiceException {
		
		url = buildFromBaseUrl(url, String.format(STATUS_URI, reportId));
		
		log.debug("send status request to url: " + url);
		
		HttpGet getMethod = null;
		Status reportStatus = null;
		try {
			getMethod = new HttpGet(url);
			getMethod.addHeader("Content-Type", "application/xml");
			getMethod.addHeader("Cookie", cookie);
		    CloseableHttpClient  httpClient = HttpClients.createDefault();
		    CloseableHttpResponse httpResponse = httpClient.execute(getMethod);
		    int httpStatusCode = httpResponse.getStatusLine().getStatusCode();
			String response = null;
			try {
				response = readResponse(httpResponse);
				EntityUtils.consume(httpResponse.getEntity());
			} finally {
				httpResponse.close();
			}
			log.info("Jasper status report http status: " + httpStatusCode);
			log.info("Jasper status report response: " + response);
			if(httpStatusCode == HttpStatus.SC_OK) {
				if(response != null) {
					if(response.indexOf("<status>") >= 0) {
						int statusBegin = response.indexOf("<status>") + 8;
						int statusEnd = response.indexOf("</status>");
						reportStatus = new Status();
						String s = response.substring(statusBegin, statusEnd);
						reportStatus.setStatus(s);
						log.info("Jasper status request response: " + reportStatus.toString());
					}
				}
			}
			else {
				throw new IOException(response);
			}
		}
		catch(IOException e) {
			throw new ReportServiceException("Jasper report status request io exception", e);
		}
		finally {
			if(getMethod != null)
				getMethod.releaseConnection();
		}
		
		return reportStatus;
	}
	
	
	/**
	 * report detail request to get the status and other reporting statuses
	 * for a given report id being requested.
	 * @param url
	 * @param reportId
	 * @param cookie
	 * @return
	 * @throws ReportServiceException
	 */
	public static ReportExecution sendJasperReportDetailsRequest(String url, 
			String reportId, String cookie) throws ReportServiceException {
		
		url = buildFromBaseUrl(url, String.format(DETAIL_URI, reportId));
		
		log.debug("send report detail request to url: " + url);
		
		HttpGet getMethod = null;
		ReportExecution reportExecution = null;
		try {
			getMethod = new HttpGet(url);
			getMethod.addHeader("Content-Type", "application/xml");
			getMethod.addHeader("Cookie", cookie);
		    CloseableHttpClient  httpClient = HttpClients.createDefault();
		    CloseableHttpResponse httpResponse = httpClient.execute(getMethod);
		    int httpStatusCode = httpResponse.getStatusLine().getStatusCode();
			String response = null;
			try {
				response = readResponse(httpResponse);
				EntityUtils.consume(httpResponse.getEntity());
			} finally {
				httpResponse.close();
			}
			log.info("Jasper detail request http status: " + httpStatusCode);
			log.info("Jasper detail request response: " + response);
			if(httpStatusCode == HttpStatus.SC_OK) {
				
				XMLUtil<ReportExecution> xu1 = new XMLUtil<>(ReportExecution.class);
				reportExecution = xu1.xmlToObject(response);
			}
			else {
				throw new IOException("Jasper detail request error: " + response);
			}
		}
		catch(IOException e) {
			throw new ReportServiceException("IO Exception for report details request", e);
		}
		catch(JAXBException e) {
			throw new ReportServiceException("JAXB Exception for report details request", e);
		}
		finally {
			if(getMethod != null)
				getMethod.releaseConnection();
		}
		
		return reportExecution;
	}
	
	/**
	 * get the exported report from jasper as a result of an 
	 * asynchronous request for report
	 * @param reportId
	 * @param cookie
	 * @return
	 * @throws ReportServiceException
	 */
	public static byte[] sendJasperReportOutput(String url, String reportId, 
			String cookie, String fileFormat) throws ReportServiceException {
		
		url = buildFromBaseUrl(url, String.format(OUTPUT_URI, reportId, fileFormat));
		
		log.debug("send status request to url: " + url);
		
		HttpGet getMethod = null;
		byte[] fileBytes = null;
		try {
			getMethod = new HttpGet(url);
			getMethod.addHeader("Cookie", cookie);
			CloseableHttpClient  httpClient = HttpClients.createDefault();
		    CloseableHttpResponse httpResponse = httpClient.execute(getMethod);
			log.info("Jasper report output http status: " + httpResponse.getStatusLine().getStatusCode());
			if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				fileBytes = readBody(httpResponse);
				log.info("Jasper report output successful response string: " + fileBytes);
			}
			else {
				String response = null;
				try {
					response = readResponse(httpResponse);
					EntityUtils.consume(httpResponse.getEntity());
				} finally {
					httpResponse.close();
				}
				log.error("ReportExecution Report Output Failure Response: " + response);
			}
		}
		catch(IOException e) {
			throw new ReportServiceException("Jasper report output request io exception", e);
		}
		finally {
			if(getMethod != null)
				getMethod.releaseConnection();
		}
		
		return fileBytes;
	}
	
	/**
	 * login request to jasper reporting system to get a valid cookie
	 * for subsequent calls.
	 * @return
	 * @throws ReportServiceException
	 */
	public static String sendJasperLoginRequest(String url, String user, String password) 
			throws ReportServiceException {
		
		url = buildFromBaseUrl(url, LOGIN_URI);
		
		HttpPost loginPost = null;
		String cookie = null;
		System.out.println("send jasper login request base url: " + url);
		try {
			loginPost = new HttpPost(url);
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		    nameValuePairs.add(new BasicNameValuePair("Content-Type", "application/x-www-form-urlencoded"));
		    nameValuePairs.add(new BasicNameValuePair(CONST_JASPER_USER, user));
		    nameValuePairs.add(new BasicNameValuePair(CONST_JASPER_PASS, password));
		    loginPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		    CloseableHttpClient httpClient = HttpClients.createDefault();
		    CloseableHttpResponse httpResponse = httpClient.execute(loginPost);
			log.info("Jasper report http status: " + httpResponse.getStatusLine().getStatusCode());
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
			throw new ReportServiceException("Jasper Login error", e);
		} 
		finally {
			if(loginPost != null)
				loginPost.releaseConnection();
		}
		return cookie;
	}
	
	/**
	 * read data bytes as string from response body
	 * @param httpResponse
	 * @return
	 * @throws IOException
	 */
	protected static String readResponse(HttpResponse httpResponse) throws IOException {
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
	
	/**
	 * read data bytes in the response body
	 * @param httpResponse
	 * @return
	 * @throws IOException
	 */
	protected static byte[] readBody(HttpResponse httpResponse) throws IOException {
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
	 * synchronous report request get method parameters are sent as
	 * query string name value pairs. This method builds the query
	 * for the url.
	 * @param container
	 * @return
	 */
	protected static String buildQueryString(List<ReportParameterContainer> container) {
		if(container == null) return null;
		ReportParameterContainer rpc = container.get(0);
		if(rpc == null) return null;
		List<ReportParameter> params = rpc.getReportParameter();
		StringBuilder sb = new StringBuilder();
		for(ReportParameter p : params) {
			if(params.indexOf(p) > 0) sb.append("&");
			String paramName = p.getName();
			Object obj = p.getValue();
			if(obj instanceof List) {
				@SuppressWarnings({ "unchecked", "rawtypes" })
				List<String> values = (List)obj;
				for(String s : values) {
					if(values.indexOf(s) > 0) sb.append("&");
					sb.append(paramName).append("=").append(s);
				}
			} else {
				sb.append(paramName).append("=").append((String)obj);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * @param baseUrl
	 * @param uri
	 * @return
	 */
	private static String buildFromBaseUrl(String baseUrl, String uri) {
		if(baseUrl.endsWith("/") && !uri.startsWith("/")) {
			baseUrl += uri;
		} else if(!baseUrl.endsWith("/") && uri.startsWith("/")) {
			baseUrl += uri;
		} else if(baseUrl.endsWith("/") && uri.startsWith("/")) {
			baseUrl += uri.substring(1);
		} else {
			baseUrl += "/" + uri;
		}
		
		return baseUrl;
	}
}
