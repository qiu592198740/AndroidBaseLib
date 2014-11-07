package com.owl.baselib.net.handler;

import com.owl.baselib.utils.network.PostParams;



/**
 * 常用网络网络请求动作接口封装
 * @author qiushunming
 * 2014年7月31日
 */
public interface IRequest {
	
	public static final int REQUEST_TYPE_POST = 1;
	public static final int REQUEST_TYPE_GET = 2;
	
	/**
	 * 开始请求任务
	 * @param requestType 请求类型，REQUEST_TYPE_POST or REQUEST_TYPE_GET
	 * @param url 请求地址
	 * @param params 请求参数，REQUEST_TYPE_POST类型使用，REQUEST_TYPE_GET类型可传null
	 */
	public void startRequest(int requestType, String url, PostParams params);
	
	
	/**
	 * 取消任务
	 */
	public void cancleRequest();
}
	
