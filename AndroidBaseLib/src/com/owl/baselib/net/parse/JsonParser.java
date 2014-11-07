package com.owl.baselib.net.parse;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import com.owl.baselib.net.handler.IDataParser;
import com.owl.baselib.utils.asert.AppAssert;

/**
 * json数据解析基类
 * 
 * @author qiushunming 2014年8月14日
 */
public abstract class JsonParser implements IDataParser {

	public static final int PARSER_ERROR_CODE_NULL_JSONOBJECT = 0;
	public static final int PARSER_ERROR_CODE_THROW_EXCEPTION = 1;

	public static final String PARSER_ERROR_MSG_THROW_EXCEPTION = "数据解析异常";
	public static final String PARSER_ERROR_MSG_NULL_JSONOBJECT = "服务器无数据返回，请稍后重试！";

	public static final int ERROR_NONE = 10000;

	/**
	 * 接口返回状态码
	 */
	public static final String STATUS_CODE = "status_code";
	/**
	 * 接口返回提示信息
	 */
	public static final String MSG = "msg";

	/**
	 * 资料字段
	 */
	public static final String PROFILE = "profile";

	public static final String DATA = "data";

	public String mJsonString;
	public JSONObject mJsonObj;

	/**
	 * 错误回调
	 */
	protected OnParseResultListener mOnParserResultListener;

	/**
	 * 状态码
	 */
	private int mStatusCode;
	/**
	 * 提示信息
	 */
	private String mMsg;

	private int mCmdId;

	/**
	 * 构造方法
	 * 
	 * @param onDataParserError
	 *            此处传递错误回调，是为了实现把错误丢出去给fragment处理，基础LY_BaseFragment默认是实现了此接口的
	 */
	public JsonParser(int cmdId, OnParseResultListener onParseResultListener) {
		mCmdId = cmdId;
		setOnParseResultListener(onParseResultListener);
	}

	public int getStatusCode() {
		return mStatusCode;
	}

	public void setStatusCode(int statusCode) {
		this.mStatusCode = statusCode;
	}

	public String getMsg() {
		return mMsg;
	}

	public void setMsg(String msg) {
		this.mMsg = msg;
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

	@Override
	public <T> void parserData(Header[] headers, T data) {
		// mJsonString = M_Tools.byteArrayToUtf8(data);
		AppAssert.assertTrue("data is not a String!", data instanceof String);
		mJsonString = (String) data;
//		M_Log.d(mJsonString);
		try {
			mJsonObj = new JSONObject(mJsonString);
			if (mJsonObj != null) {

				// 解析公共数据
				setStatusCode(mJsonObj.getInt(STATUS_CODE));
				setMsg(mJsonObj.getString(MSG));

				// 解析子类自己数据
				if (getStatusCode() == ERROR_NONE) {

					boolean isSuc = parserOwnData(headers, mJsonObj);

					if (isSuc) {

						dealParseSuc();
						return;
					} else {

						dealLogicError();
					}
				} else {

					dealLogicError();
				}

			} else {

				dealDataError();
			}

		} catch (JSONException e) {
			e.printStackTrace();
			dealDataError();
		}
	}

	private void dealParseSuc() {
		if (mOnParserResultListener != null) {
			mOnParserResultListener.onParseSuccess(mCmdId, getData());
		}

	}

	private void dealLogicError() {
		if (mOnParserResultListener != null) {
			mOnParserResultListener.onLogicError(mCmdId, getStatusCode(),
					getMsg());
		}
	}

	private void dealDataError() {
		if (mOnParserResultListener != null) {
			mOnParserResultListener.onDataError(mCmdId,
					PARSER_ERROR_CODE_NULL_JSONOBJECT,
					PARSER_ERROR_MSG_NULL_JSONOBJECT);
		}
	}

	/**
	 * 子类数据解析
	 * 
	 * @param headers
	 * @param data
	 * @return
	 * @throws Exception
	 */
	abstract boolean parserOwnData(Header[] headers, JSONObject jsonObject)
			throws JSONException;

}
