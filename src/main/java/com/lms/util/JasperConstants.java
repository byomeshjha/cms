package com.lms.util;

public class JasperConstants {

	static final boolean SHOW_SPEC_MODE = false;

	// SERVER PARAMETERS
	static final String SCHEME = "http";
	static final String HOST = "localhost";
	static final int PORT = 9000;
	static final String SERVER_HANDLE = "/jasperserver";//-pro";

	//Server paths
	static final String BASE_REST_URL = SERVER_HANDLE+"/rest";

	// login parameters
	static final String PARAMETER_USERNAME = "j_username";
	static final String PARAM_PASSWORD = "j_password";

	static final String USER_NAME = "jasperadmin";
	static final String PASSWORD = "jasperadmin";


	// SERVER ENTITIES
	static final String SERVICE_LOGIN = "/login";
	static final String RESOURCES_LOCAL_PATH = "resources/";
	static final String SAMPLE_FOLDER_RD = "folder_URI.SAMPLE_REST_FOLDER.xml";
	static final String SAMPLE_IMAGE_RD = "image_URI.JUNIT_NEW_FOLDER.JUNIT_IMAGE_FILE.xml";
	static final String SAMPLE_IMAGE_BIN = "jasperSoftLogo.jpg";
	static final String NEW_SAMPLE_IMAGE_BIN = "jasperSoftLogo_2.jpg";
	static final String REQUEST_PARAMENTER_RD = "ResourceDescriptor";
	static final String RESOURCE = "/resource";
	static final String LOG4J_PATH = "log4j.properties";



}
