package com.owl.baselib.net.request;

import com.owl.baselib.app.event.HttpEvent;

/**
 * 下载需传递状态出界面
 * @author Administrator
 *
 */
public abstract class DownloadRequestHelper extends CommonRequestHelper {

	@Override
	public void onConnecting(int cmdId) {
		HttpEvent event = new HttpEvent();
		event.setCmdId(cmdId);
		mEventBus.postEvent(event);
	}

	@Override
	public void onDataReading(int cmdId, long total, long curLen) {
		HttpEvent event = new HttpEvent();
		event.setCmdId(cmdId);
		event.setTotal(total);
		event.setCurLen(curLen);
		mEventBus.postEvent(event);
	}
}
