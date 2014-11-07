package com.owl.baselib.net.request;

import org.apache.http.Header;

import com.owl.baselib.net.handler.IDataParser;
import com.owl.baselib.net.handler.IRespond;
import com.owl.baselib.net.parse.OnParseResultListener;
import com.owl.baselib.utils.log.LogUtils;

/**
 * 请求帮助基类
 * 
 * @author qiushunming 2014年8月22日
 */
public abstract class BaseRequestHelper implements IRespond, OnParseResultListener{

	/**
	 * 解析器
	 */
	private IDataParser parser;
	
	/**
	 * 外部调用获取请求配置
	 * @return
	 */
	public RequestConfig getRequestConfig(){
		return initRequestConfig(this);
	}
	
	@Override
	public <T> void onDataReadComplete(int cmdId, Header[] headers, T data) {

		parser = initParser(this);
		
		if (parser != null) {
			parser.parserData(headers, data);
		} else {
			LogUtils.e("parser is null!");
		}
	}

	@Override
	public void onNetError(int cmdId, int code, String msg) {
		onError(cmdId, code, msg);
	}

	@Override
	public void onDataError(int cmdId, int code, String msg) {
		onError(cmdId, code, msg);
	}

	@Override
	public void onLogicError(int cmdId, int code, String msg) {
		onError(cmdId, code, msg);
	}

	// 网络回调接口
	@Override
	public abstract void onConnecting(int cmdId);

	@Override
	public abstract void onDataReading(int cmdId, long total, long curLen);

	@Override
	public abstract void onTaskCancel(int cmdId);
	
	// 数据解析成功接口
	@Override
	public abstract <T> void onParseSuccess(int cmdId, T t);
	
	public abstract void onError(int cmdId, int code, String msg);
	
	/**
	 * 构造解析器
	 * @param parseResultListener
	 * @return
	 */
	protected abstract IDataParser initParser(OnParseResultListener parseResultListener);
	
	/**
	 * 构造请求配置
	 * @param baseRequestHelper
	 * @return
	 */
	protected abstract RequestConfig initRequestConfig(IRespond respond);
}
