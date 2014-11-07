package com.owl.baselib.net.request;

import android.content.Context;

import com.owl.baselib.utils.asert.AppAssert;
import com.owl.baselib.utils.log.LogUtils;

/**
 * 网络请求代理类
 * @author qiushunming
 * 2014年8月13日
 */
public class RequestProxy{
	private Context mContext;
	private RequestConfig mConfig;

	/**
	 * 基本json数据请求
	 */
	private CommonRequest mCommonRequest;
	
	/**
	 * 下载请求
	 */
	private DownloadRequest mDownloadRequest;
	
	public RequestProxy(Context context) {
		mContext = context;
	}
	
	public void startRequest(RequestConfig config){
		AppAssert.assertTrue("config can not be null!", config != null);
		
		LogUtils.i("Current Request CmdId:" + Integer.toHexString(config.getCmdId()));
		
		setRequestConfig(config);
		
		doRequest(config);
	}
	
	private void doRequest(RequestConfig config) {
		LogUtils.d("======>doRequest cmdId:" + Integer.toHexString(config.getCmdId()));
		mCommonRequest = new CommonRequest(mContext, config.getCmdId(), config.getResponser());

		if (config.getHttpCacheSize() > 0) {
			mCommonRequest.setHttpCacheSize(config.getHttpCacheSize());
		}
		
		if (config.getCookieStore() != null) {
			mCommonRequest.setCookieStore(config.getCookieStore());
		}
		
		if (config.getRetryCount() > 0) {
			mCommonRequest.setRequestRetryCount(config.getRetryCount());
		}
		
		if (config.getTimeout() > 0) {
			mCommonRequest.setTimeout(config.getTimeout());
		}
		
		mCommonRequest.startRequest(config.getRequestType(), config.getUrl(), config.getPostParams());
	}
	
	/**
	 * 取消前景任务
	 */
	public void cancleForegroundRequest(){
		if (mCommonRequest != null) {
			mCommonRequest.cancleRequest();
		}
	}
	
	/**
	 * 获取网络请求参数
	 * @return
	 */
	public RequestConfig getRequestConfig() {
		return mConfig;
	}

	/**
	 * 设置网络请求参数
	 * @param config
	 */
	public void setRequestConfig(RequestConfig config) {
		this.mConfig = config;
	}
	
	/**
	 * 下载方法
	 * @param config
	 * @param savePath
	 */
	public void download(RequestConfig config){
		AppAssert.assertTrue("config can not be null!", config != null);
		mDownloadRequest = new DownloadRequest(config.getCmdId(), config.getResponser());
		mDownloadRequest.download(config.getUrl(), config.getFileSavePath(), config.getHeaders());
	}
	
	/**
	 * 取消当前下载
	 */
	public void cancleDownload(){
		if (mDownloadRequest != null) {
			mDownloadRequest.cancleRequest();
		}
	}
}
