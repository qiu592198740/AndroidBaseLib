package com.owl.baselib.net;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

/**
 * http请求头封装
 * @author qiushunming
 * 2014年8月12日
 */
public class RequestHeader {

	private List<Header> mListHeader;
	
	public RequestHeader() {
		mListHeader = new ArrayList<Header>();
	}
	
	public void addHeade(String name, String value){
		Header header = new BasicHeader(name, value);
		mListHeader.add(header);
	}
	
	public void replaceHeader(String name, String value){
		for (int index = 0; index < mListHeader.size(); index++) {
			Header header = mListHeader.get(index);
			if(header.getName().equals(name)){
				mListHeader.add(index, new BasicHeader(name, value));
				return;
			}
		}
		
		//如果没有，直接加上
		addHeade(name, value);
	}
	
	public List<Header> getHeader(){
		return mListHeader;
	}
	
}
