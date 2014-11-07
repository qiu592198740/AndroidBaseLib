package com.owl.baselib.net.handler;

import org.apache.http.Header;

public interface IRespond{
	/**
	 * 错误代码，解析错误
	 */
	public static final int ERROR_CODE_PARSE_ERROR = 3;
	public static final String ERROR_INFO_PARSE_ERROR = "数据解析错误！";
	
//	public void parserData(int cmdId, byte[] data); 
	
	
	/**
	 * 请求连接开始
	 */
	void onConnecting(int cmdId);
	
	/**
	 * 正在读取数据
	 * @param total
	 * @param curLen
	 */
	void onDataReading(int cmdId, long total, long curLen);
	
	/**
	 * 数据读取完毕
	 * @param data
	 */
	<T> void onDataReadComplete(int cmdId, Header[] headers, T data);
	
	/**
	 * 网络任务取消
	 */
	void onTaskCancel(int cmdId);
	
	/**
	 * 网络任务出错
	 * @param code	错误码
	 * @param msg 错误信息
	 */
	void onNetError(int cmdId, int code, String msg);
}
