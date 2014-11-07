package com.owl.baselib.net;


/**
 * 发送数据到服务器，并处理 返回结果的类
 * 
 * @author qiushunming 2014年7月28日
 */
public class SendDataTask {
	//
	// /**
	// * 任务请求回调
	// * @author qiushunming
	// * 2014年7月31日
	// */
	// public interface OnRequestCallBack{
	// /**
	// * 请求连接开始
	// */
	// void onConnecting(int cmdId);
	//
	// /**
	// * 正在读取数据
	// * @param total
	// * @param curLen
	// */
	// void onDataReading(int cmdId, long total, long curLen);
	//
	// /**
	// * 数据读取完毕
	// * @param data
	// */
	// void onDataReadComplete(int cmdId, Header[] headers, byte[] data);
	//
	// /**
	// * 网络任务取消
	// */
	// void onTaskCancel(int cmdId);
	//
	// /**
	// * 网络任务出错
	// * @param code 错误码
	// * @param msg 错误信息
	// */
	// void onNetError(int cmdId, int code, String msg);
	// }
	//
	// private OnRequestCallBack mOnRequestCallBack;
	//
	// //发送数据任务的最大尝试次数
	// private final int MAX_RETRY_COUNT_DEFAULT = 5;
	// //每次连接的有效时间，超时后，会尝试新的连接
	// private final int MAX_REQUEST_TIME_DEFAULT = 20000;
	//
	// private int mCmdId;
	//
	// private HttpUtils mHttpUtils;
	//
	// public M_SendDataTask(Context context,int cmdId, String url, M_PostParams
	// postParams, int requestMethod) {
	// // super(context, url, null, postParams, requestMethod);
	// init(cmdId);
	// }
	//
	// public M_SendDataTask(Context context,int cmdId, String url,
	// M_RequestHeader requestHeader, M_PostParams postParams, int
	// requestMethod) {
	// // super(context, url, requestHeader, postParams, requestMethod);
	// init(cmdId);
	// }
	//
	// private void init(int cmdId) {
	// mCmdId = cmdId;
	// // setMaxRetryCount(MAX_RETRY_COUNT_DEFAULT);
	// // setMaxRequestTime(MAX_REQUEST_TIME_DEFAULT);
	//
	// mHttpUtils = new HttpUtils();
	// }
	//
	// /**
	// * 设置数据返回监听类
	// * @param onRequestCallBack
	// */
	// public void setmOnDataBackListener(OnRequestCallBack onRequestCallBack) {
	// this.mOnRequestCallBack = onRequestCallBack;
	// }
	//
	// @Override
	// public void onDataReadComplete(Header[] headers, byte[] data) {
	// if (mOnRequestCallBack != null) {
	// mOnRequestCallBack.onDataReadComplete(mCmdId, headers, data);
	// }
	// }
	//
	// @Override
	// public void onError(int code, String msg) {
	// if (mOnRequestCallBack != null) {
	// mOnRequestCallBack.onNetError(mCmdId, code, msg);
	// }
	// }
	//
	// @Override
	// public void onConnecting() {
	// if (mOnRequestCallBack != null) {
	// mOnRequestCallBack.onConnecting(mCmdId);
	// }
	// }
	//
	// @Override
	// public void onDataReading(long total, long curLen) {
	// if (mOnRequestCallBack != null) {
	// mOnRequestCallBack.onDataReading(mCmdId, total, curLen);
	// }
	// }
	//
	// @Override
	// public void onTaskCancel() {
	// if (mOnRequestCallBack != null) {
	// mOnRequestCallBack.onTaskCancel(mCmdId);
	// }
	// }
}
