package com.owl.baselib.net.parse;

import java.io.File;

import org.apache.http.Header;

import com.owl.baselib.net.handler.IDataParser;
import com.owl.baselib.utils.asert.AppAssert;

/**
 * 数据下载的解析类
 * 
 * @author qiushunming 2014年8月14日
 */
public abstract class DownloadParser implements IDataParser {

	/**
	 * 数据解析回调
	 */
	protected OnParseResultListener mOnParserResultListener;

	private int mCmdId;

	public DownloadParser(int cmdId, OnParseResultListener onParseResultListener) {
		mCmdId = cmdId;
		setOnParseResultListener(onParseResultListener);
	}

	/**
	 * 设置数据解析错误接口
	 * 
	 * @param onParseResultListener
	 */
	public void setOnParseResultListener(
			OnParseResultListener onParseResultListener) {
		mOnParserResultListener = onParseResultListener;
	}

	public <T> void parserData(Header[] headers, T data) {
		AppAssert.assertTrue("data is not a File!", data instanceof File);
		boolean isSuc = parseOwnData(headers, (File) data);
		if (isSuc) {
			dealParseSuc();
		} else {
			dealError();
		}
	}

	abstract boolean parseOwnData(Header[] headers, File data);

	public abstract <T> T getData();

	private void dealParseSuc() {
		if (mOnParserResultListener != null) {
			mOnParserResultListener.onParseSuccess(mCmdId, getData());
		}
	}

	private void dealError() {
		if (mOnParserResultListener != null) {
			mOnParserResultListener.onDataError(mCmdId, 0, "下载数据解析错误");
		}
	}
}
