package com.owl.baselib.net.handler;

import org.apache.http.Header;

public interface IDataParser {
	<T> void parserData(Header[] headers, T data);
	
	<T> T getData();
	
}
