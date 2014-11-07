package com.owl.baselib.net.request;

import org.apache.http.client.CookieStore;

import com.owl.baselib.net.RequestHeader;
import com.owl.baselib.net.handler.IRespond;
import com.owl.baselib.utils.network.PostParams;

/**
 * 请求时的参数配置类
 * @author qiushunming
 * 2014年8月13日
 */
public class RequestConfig {
	private int mCmdId;
	private IRespond mResponser;
	private int mRequestType;
	private String mUrl;
	private PostParams mPostParams;
	private RequestHeader mHeaders;
	
	/**
	 * 下载时使用，把文件保存至本地的目录
	 */
	private String fileSavePath;
	
	/**
	 * 设置缓存请求大小
	 */
	private int httpCacheSize;
	
	/**
	 * 设置是否需要内存缓存
	 */
	private boolean isCacheMemory;
	
	/**
	 * 设置cookie
	 */
	private CookieStore cookieStore;
	
	/**
	 * 设置重连数
	 */
	private int retryCount;
	
	/**
	 * 设置超时时间
	 */
	private int timeout;
	
	public RequestConfig() {
	}
	
	public RequestConfig setRequestType(int requestType){
		mRequestType = requestType;
		return this;
	}

	public String getUrl() {
		return mUrl;
	}

	public RequestConfig setUrl(String url) {
		this.mUrl = url;
		return this;
	}

	public int getRequestType() {
		return mRequestType;
	}

	public PostParams getPostParams() {
		return mPostParams;
	}

	public RequestConfig setPostParams(PostParams postParams) {
		this.mPostParams = postParams;
		return this;
	}

	public int getCmdId() {
		return mCmdId;
	}

	public RequestConfig setCmdId(int mCmdId) {
		this.mCmdId = mCmdId;
		return this;
	}

	public IRespond getResponser() {
		return mResponser;
	}

	public RequestConfig setResponser(IRespond mRespons) {
		this.mResponser = mRespons;
		return this;
	}

	public RequestHeader getHeaders() {
		return mHeaders;
	}

	public RequestConfig setHeaders(RequestHeader headers) {
		this.mHeaders = headers;
		return this;
	}

	public String getFileSavePath() {
		return fileSavePath;
	}

	public RequestConfig setFileSavePath(String filePath) {
		this.fileSavePath = filePath;
		return this;
	}

	public int getHttpCacheSize() {
		return httpCacheSize;
	}

	public void setHttpCacheSize(int httpCacheSize) {
		this.httpCacheSize = httpCacheSize;
	}

	public boolean isCacheMemory() {
		return isCacheMemory;
	}

	public void setCacheMemory(boolean isCacheMemory) {
		this.isCacheMemory = isCacheMemory;
	}

	public CookieStore getCookieStore() {
		return cookieStore;
	}

	public void setCookieStore(CookieStore cookieStore) {
		this.cookieStore = cookieStore;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
}
