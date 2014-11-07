package com.owl.baselib.utils.network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.text.TextUtils;

/**
 * http post 参数封装
 * @author qiushunming
 * 2014年7月28日
 */
public class PostParams {
	/**
	 * 字符串提交参数
	 */
	private List<NameValuePair> mTextParams;
	
	/**
	 * 文件提交参数
	 */
	private List<NameValuePair> mFileParams;
	
	/**
	 * 字节数据参数
	 */
	private HashMap<String, byte[]> mByteParams = new HashMap<String, byte[]>();
	/**
	 * 字节数据参数文件名
	 */
	private HashMap<String, String> mByteParamsFileName = new HashMap<String, String>();
	
	public List<NameValuePair> getTextParams() {
		return mTextParams;
	}
	
	public void setTextParams(List<NameValuePair> textParams) {
		this.mTextParams = textParams;
	}
	
	public List<NameValuePair> getFileParams() {
		return mFileParams;
	}
	
	public void setFileParams(List<NameValuePair> fileParams) {
		this.mFileParams = fileParams;
	}
	
	public HashMap<String, byte[]> getByteParams() {
		return mByteParams;
	}

	public void setByteParams(HashMap<String, byte[]> byteParams) {
		mByteParams = byteParams;
	}
	
	public HashMap<String, String> getByteParamsFileName() {
		return mByteParamsFileName;
	}

	public void setByteParamsFileName(HashMap<String, String> byteParamsFileName) {
		this.mByteParamsFileName = byteParamsFileName;
	}
	
	public boolean hasTextPostParams() {
		return getTextParams() != null && getTextParams().size() > 0;
	}
	
	public boolean hasFilePostParams() {
		return getFileParams() != null && getFileParams().size() > 0;
	}
	
	public boolean hasBytesPostParams() {
		return getByteParams() != null && getByteParams().size() > 0;
	}

	/**
	 * 参数创建类
	 * @author qiushunming
	 * 2014年7月28日
	 */
	public static class Builder{
		/**
		 * post数据参数
		 */
		private PostParams mPostParams;
		
		public Builder() {
			mPostParams = new PostParams(); 
			mPostParams.mTextParams = new ArrayList<NameValuePair>();
			mPostParams.mFileParams = new ArrayList<NameValuePair>();
		}
		
		/**
		 * 添加一般字符参数键值对
		 * @param name
		 * @param value
		 * @return
		 */
		public Builder addTextParam(String name, String value){
			mPostParams.mTextParams.add(new BasicNameValuePair(name, value));
			return this;
		}
		
		/**
		 * 添加文件类型参数键值对（暂时用于传图片）
		 * @param name
		 * @param filePath 文件绝对路径，如：mnt/sdcard/image.jpg
		 * @return
		 */
		public Builder addFileParam(String name, String filePath){
			mPostParams.mFileParams.add(new BasicNameValuePair(name, filePath));
			return this;
		}
		
		/**
		 * 添加字节数组类型的键值对
		 * @param name
		 * @param data
		 * @return
		 */
		public Builder addBytesParams(String name, byte[] data, String fileName){
			if (mPostParams.mByteParams != null) {
				mPostParams.mByteParams.put(name, data);
				if (!TextUtils.isEmpty(fileName)) {
					mPostParams.mByteParamsFileName.put(name, fileName);
				}
			}
			return this;
		}
		
		public PostParams getPostParams(){
			return mPostParams;
		}
	}
}
