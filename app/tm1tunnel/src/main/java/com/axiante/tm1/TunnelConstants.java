package com.axiante.tm1;

import lombok.NonNull;

public class TunnelConstants {
	// ACCESS
	// SOME INTERROGATIONS
	public static final String ExecuteMDX = "ExecuteMDX?";
	public static final String CUBES = "Cubes";
	public static final String METADATA = "$metadata";
	public static final String VERSION = "Configuration/ProductVersion/$value";
	public static final String SESSION_CLOSE = "ActiveSession/tm1.Close";
	public static final String THREADS = "Threads";
	// AUTHENTICATION PARAMETERS
	public static final String AUTH_HEADER = "WWW-Authenticate";
	public static final String AUTH_ACTION = "?b_action=xts.run&m=portal/close.xts&h_CAM_action=logon";

	public static final String UNAUTHORIZED = "UNAUTHORIZED";
	public static final String FREE_ACCESS = "OPEN";
	public static final String SUCCESS = "SUCCESS";

	public static final String APPLICATION_JSON = "application/json";
	public static final String HTTP_METHOD_GET = "GET";
	public static final String HTTP_METHOD_PUT = "PUT";
	public static final String HTTP_METHOD_POST = "POST";
	public static final String HTTP_HEADER_CONTENT_TYPE = "Content-Type";
	public static final String HTTP_HEADER_ACCEPT = "Accept";
	public static final String SEPARATOR = "/";
	public static final String APPLICATION_XML = "application/xml";
	public static final String APPLICATION_ATOM_XML = "application/atom+xml";
	private static final String PROCESS = "Processes('%s')/tm1.Execute";
	private static final String PROCESS_WITH_RETURN = "Processes('%s')/tm1.ExecuteWithReturn";

	public static final String CAMPASSPORT = "CAMPassPort";
	public static final String TM1CONNECTIONCOOKIE = "TM1SessionId";
	public static synchronized String executeProcess(@NonNull final String name, final Boolean withReturn) {
		return String.format(withReturn ? PROCESS_WITH_RETURN : PROCESS, name);
	}

}
