package com.owl.baselib.net;

import java.util.HashMap;
import java.util.Map;

public class HttpConstants 
{
	//分隔符
	public static final String HTTP_RESPONSE_SPLIT = ";";
	public static final String HTTP_URL_SPLIT = "/";
	
	//Http Status Code
	public static final int HTTP_STATUS_OK = 200;
	
	public static final String NETWORK_ERROT_MSG = "网络错误，请稍后重试！";
	
	//Http Response Header
	public static final String HEADER_CONTENT_TYPE = "Content-Type";
	public static final String HEADER_CONTENT_ENCODING = "Content-Encoding";
	public static final String HEADER_CONTENT_LENGTH = "Content-Length";
	public static final String HEADER_EXPIRES = "Expires";
	public static final String HEADER_CACHE_CONTROL = "Cache-Control";
	public static final String HEADER_LAST_MODIFIED = "Last-Modified";
	public static final String HEADER_ETAG = "Etag";
	public static final String HEADER_LOCATION = "Location";
	
//	public static String strCookie;
	public static Map<String,String> mapCookie = new HashMap<String, String>();
}
