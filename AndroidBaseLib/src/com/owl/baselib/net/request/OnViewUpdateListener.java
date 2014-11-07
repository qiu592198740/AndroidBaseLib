package com.owl.baselib.net.request;

public interface OnViewUpdateListener {

	public void onConnecting(int cmdId);

	public void onDataReading(int cmdId, long total, long curLen);

	public void onTaskCancel(int cmdId);

	public <T> void onSuccess(int cmdId, T t);

	public void onError(int cmdId, int code, String msg);
}
