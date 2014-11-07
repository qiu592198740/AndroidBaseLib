package com.owl.baselib.net.parse;

/**
 * 数据解析错误回调
 * @author qiushunming
 * 2014年8月14日
 */
public interface OnParseResultListener{
	
	/**
	 * 数据解析成功
	 */
	public <T> void onParseSuccess(int cmdId, T t);

	/**
	 * 数据解析的错误处理
	 * @param code
	 * @param msg
	 */
	public void onDataError(int cmdId, int code, String msg);
	
	/**
	 * 服务器返回的错误码处理
	 * @param code
	 * @param msg
	 */
	public void onLogicError(int cmdId, int code, String msg);
}