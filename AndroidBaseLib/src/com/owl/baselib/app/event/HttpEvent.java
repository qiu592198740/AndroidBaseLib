package com.owl.baselib.app.event;

import org.apache.http.Header;
/**
 * HTTP回调事件数据结构
 * @author Administrator
 *
 * @param <T>
 */
public class HttpEvent<T> {
	public static final int STATUS_CONNECTING = 1;
	public static final int STATUS_DATA_READING = STATUS_CONNECTING + 1;
	public static final int STATUS_TASK_CANCEL = STATUS_DATA_READING + 1;
	public static final int STATUS_SUC = STATUS_TASK_CANCEL + 1;
	public static final int STATUS_ERROR = STATUS_SUC + 1;
	
	/**
	 * http请求结果状态
	 */
	private int status;
	
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
