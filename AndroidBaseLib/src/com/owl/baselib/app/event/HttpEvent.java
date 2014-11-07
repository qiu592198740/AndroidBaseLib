package com.owl.baselib.app.event;

import org.apache.http.Header;

public class HttpEvent<T> {
	private String url;
	private int cmdId;

	private int errorCode;
	private String errorMsg;

	private Header[] headers;
	private T data;

	private long total;
	private long curLen;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getCmdId() {
		return cmdId;
	}

	public void setCmdId(int cmdId) {
		this.cmdId = cmdId;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Header[] getHeaders() {
		return headers;
	}

	public void setHeaders(Header[] headers) {
		this.headers = headers;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getCurLen() {
		return curLen;
	}

	public void setCurLen(long curLen) {
		this.curLen = curLen;
	}

}
