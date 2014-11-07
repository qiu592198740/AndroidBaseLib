package com.owl.baselib.net.request;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.owl.baselib.net.HttpConstants;
import com.owl.baselib.net.RequestHeader;
import com.owl.baselib.net.handler.IRequest;
import com.owl.baselib.net.handler.IRespond;
import com.owl.baselib.utils.ImageUtils;
import com.owl.baselib.utils.asert.AppAssert;
import com.owl.baselib.utils.network.PostParams;

/**
 * 具体网络请求基类
 * @author qiushunming
 * 2014年8月4日
 */
public class CommonRequest implements IRequest{

	
	/**
	 * 默认超时时间
	 */
	public static final int TIME_OUT = 1000 * 45;

	/**
	 * xutils http工具
	 */
	private static HttpUtils mHttpUtils = new HttpUtils();
	private HttpHandler<String> mHttpHandler;

	private int mCmdId;
	
	private Context mContext;
	
	private IRespond mResponder;
	
	private RequestHeader mRequestHeader;

	public CommonRequest(Context context,int cmdId, IRespond responder) {
		mContext = context;
		mCmdId = cmdId;
		mResponder = responder;
		setTimeout(TIME_OUT);
	}
	
	/**
	 * 获取请求头
	 * @return
	 */
	public RequestHeader getRequestHeader() {
		return mRequestHeader;
	}

	/**
	 * 设置请求头
	 * @param mRequestHeader
	 */
	public void setRequestHeader(RequestHeader mRequestHeader) {
		this.mRequestHeader = mRequestHeader;
	}
	
	/**
	 * 设置http缓存大小，默认是1024*100个字符长度
	 * @param httpCacheSize
	 */
	public void setHttpCacheSize(int httpCacheSize){
		mHttpUtils.configHttpCacheSize(httpCacheSize);
	}
	
	/**
	 * 设置cookie
	 * @param cookieStore
	 */
	public void setCookieStore(CookieStore cookieStore){
		mHttpUtils.configCookieStore(cookieStore);
	}
	
	public CookieStore getCookieStore(){
        return ((DefaultHttpClient)mHttpUtils.getHttpClient()).getCookieStore();
	}
	
	/**
	 * 设置重连次数
	 * @param count
	 */
	public void setRequestRetryCount(int count){
		mHttpUtils.configRequestRetryCount(count);
	}
	
	/**
	 * 设置超时时间
	 * @param timeout
	 */
	public void setTimeout(int timeout){
		mHttpUtils.configTimeout(timeout);
	}
	
	/**
	 * post 封装
	 * @param url
	 * @param params
	 */
	public void postRequest(String url, PostParams params){
		startRequest(REQUEST_TYPE_POST, url, params);
	}
	
	@Override
	public void startRequest(int requestType, String url, PostParams params) {
		AppAssert.assertTrue("url can not be blank!", url != null && url.length() > 0);
		
        RequestParams requestParams = new RequestParams();
        //默认设置是post请求
        HttpMethod method = HttpMethod.POST;;
        RequestHeader headers = getRequestHeader();
        
        //添加http头
        if (headers != null) {
        	requestParams.addHeaders(headers.getHeader());
        	
        	//清空请求头，避免下次用同样的http头请求
        	setRequestHeader(null);
		}
        
        if (requestType == REQUEST_TYPE_POST) {
        	
        	//设置参数
        	if (params != null) {
        		if (params.hasTextPostParams()) {
    				List<NameValuePair> textParams = params.getTextParams();
    				requestParams.addBodyParameter(textParams);
    			}

    			if (params.hasFilePostParams()) {
    				//暂时仅作图片上传
    				List<NameValuePair> fileParams = params.getFileParams();
    				
    				for (int index = 0; index < fileParams.size(); index++) {
    					NameValuePair pair = fileParams.get(index);
    					String path = pair.getValue();
    					byte[] imgBuf = ImageUtils.compressImageFromFile(path,
    							mContext);
    					ByteArrayInputStream bais = new ByteArrayInputStream(imgBuf);
    					requestParams.addBodyParameter(pair.getName(), bais, bais.available());
    				}
    			}
    			
    			if (params.hasBytesPostParams()) {
    				HashMap<String, byte[]> bytesParams = params.getByteParams();
    				HashMap<String, String> bytesParamsFileName = params.getByteParamsFileName();
    				for (String name : bytesParams.keySet()) {
    					byte[] data = bytesParams.get(name);
    					ByteArrayInputStream bais = new ByteArrayInputStream(data);
//    					requestParams.addBodyParameter(name, bais, bais.available());
    					requestParams.addBodyParameter(name, bais, bais.available(), bytesParamsFileName.get(name));
    				}
    			}
			}
        	
		}else if (requestType == REQUEST_TYPE_GET) {
			method = HttpMethod.GET;
		}
		
		mHttpHandler = mHttpUtils.send(method, url, requestParams, mNormalRequestCallBack);
	}
	
	@Override
	public void cancleRequest() {
		if (mHttpHandler != null) {
			mHttpHandler.cancel();
		}
	}
	
	private RequestCallBack<String> mNormalRequestCallBack = new RequestCallBack<String>() {

		@Override
		public void onStart() {
			super.onStart();
			if (mResponder != null) {
				mResponder.onConnecting(mCmdId);
			}
		}

		@Override
		public void onCancelled() {
			super.onCancelled();
			if (mResponder != null) {
				mResponder.onTaskCancel(mCmdId);
			}
		}

		@Override
		public void onLoading(long total, long current, boolean isUploading) {
			super.onLoading(total, current, isUploading);
			if (mResponder != null) {
				mResponder.onDataReading(mCmdId, total, current);
			}
		}

		@Override
		public void onSuccess(ResponseInfo<String> responseInfo) {
			
			if (mResponder != null) {
				mResponder.onDataReadComplete(mCmdId, responseInfo.getAllHeaders(), responseInfo.result);
			}
		}

		@Override
		public void onFailure(HttpException error, String msg) {
			if (mResponder != null) {
				mResponder.onNetError(mCmdId, error.getExceptionCode(), HttpConstants.NETWORK_ERROT_MSG);
			}
		}
	};
}
