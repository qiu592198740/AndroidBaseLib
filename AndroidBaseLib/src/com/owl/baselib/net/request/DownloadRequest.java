package com.owl.baselib.net.request;

import java.io.File;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.owl.baselib.net.RequestHeader;
import com.owl.baselib.net.handler.IRequest;
import com.owl.baselib.net.handler.IRespond;
import com.owl.baselib.utils.asert.AppAssert;
import com.owl.baselib.utils.network.PostParams;

public class DownloadRequest implements IRequest {

	/**
	 * 默认超时时间
	 */
	public static final int TIME_OUT = 20000;

	/**
	 * xutils http工具
	 */
	private static HttpUtils mHttpUtils = new HttpUtils();;
	private int mTimeOut = TIME_OUT;
	private HttpHandler<File> mHttpHandler;

	// 文件路径
	private String mSavePath;

	private int mCmdId;

	private IRespond mResponder;

	private RequestHeader mRequestHeader;

	public DownloadRequest(int cmdId,
			IRespond responder) {
		mCmdId = cmdId;
		mResponder = responder;
		mHttpUtils.configTimeout(mTimeOut);
	}

	public RequestHeader getRequestHeader() {
		return mRequestHeader;
	}

	public void setRequestHeader(RequestHeader equestHeader) {
		this.mRequestHeader = equestHeader;
	}

	public void postRequest(String url, PostParams params) {
		startRequest(REQUEST_TYPE_POST, url, params);
	}

	public void download(String url, String savePath,
			RequestHeader requestHeader) {
		setSavePath(savePath);
		setRequestHeader(requestHeader);
		startRequest(REQUEST_TYPE_GET, url, null);
	}

	@Override
	public void startRequest(int requestType, String url, PostParams params) {
		AppAssert.assertTrue("url can not be blank!",
				url != null && url.length() > 0);

		RequestParams requestParams = new RequestParams();
		// 默认设置是post请求
		RequestHeader headers = getRequestHeader();

		// 添加http头
		if (headers != null) {
			requestParams.addHeaders(headers.getHeader());

			// 清空请求头，避免下次用同样的http头请求
			setRequestHeader(null);
		}

		
		// mHttpHandler = mHttpUtils.send(method, url, requestParams,
		// mNormalRequestCallBack);
		mHttpHandler = mHttpUtils.download(url, mSavePath,
				mDownloadRequestCallBack);

	}

	@Override
	public void cancleRequest() {
		if (mHttpHandler == null) {
		} else {
			mHttpHandler.cancel();
		}
	}

	public String getSavePath() {
		return mSavePath;
	}

	public void setSavePath(String savePath) {
		this.mSavePath = savePath;
	}

	private RequestCallBack<File> mDownloadRequestCallBack = new RequestCallBack<File>() {

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
		public void onSuccess(ResponseInfo<File> responseInfo) {

			if (mResponder != null) {
				mResponder.onDataReadComplete(mCmdId,
						responseInfo.getAllHeaders(), responseInfo.result);
			}
		}

		@Override
		public void onFailure(HttpException error, String msg) {
			if (mResponder != null) {
				mResponder.onNetError(mCmdId, error.getExceptionCode(), msg);
			}
		}
	};
}
