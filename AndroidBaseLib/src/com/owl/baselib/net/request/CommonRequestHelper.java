package com.owl.baselib.net.request;

import com.owl.baselib.app.EventBusWrapper;
import com.owl.baselib.app.event.HttpEvent;
import com.owl.baselib.net.handler.IDataParser;
import com.owl.baselib.net.handler.IRespond;
import com.owl.baselib.net.parse.OnParseResultListener;

import de.greenrobot.event.EventBus;
/**
 * 普通请求帮助类，用于做基础请求
 * @author qiushunming
 *
 */
public abstract class CommonRequestHelper extends BaseRequestHelper {

	protected EventBusWrapper mEventBus = EventBusWrapper.getInstance();
	
	@Override
	public void onConnecting(int cmdId) {
		//do nothing
	}

	@Override
	public void onDataReading(int cmdId, long total, long curLen) {
		//do nothing
	}

	@Override
	public void onTaskCancel(int cmdId) {
		HttpEvent event = new HttpEvent();
		event.setCmdId(cmdId);
		mEventBus.postEvent(event);
	}

	@Override
	public <T> void onParseSuccess(int cmdId, T t) {
		HttpEvent<T> event = new HttpEvent<T>();
		event.setCmdId(cmdId);
		event.setData(t);
		mEventBus.postEvent(event);
	}

	@Override
	public void onError(int cmdId, int code, String msg) {
		HttpEvent event = new HttpEvent();
		event.setCmdId(cmdId);
		event.setErrorCode(code);
		event.setErrorMsg(msg);
		mEventBus.postEvent(event);
	}

	@Override
	protected abstract IDataParser initParser(OnParseResultListener parseResultListener);

	@Override
	protected abstract RequestConfig initRequestConfig(IRespond respond);

}
