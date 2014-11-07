package com.owl.baselib.data.cache;

import java.util.Collection;
/**
 * 简易数据缓存接口
 * @author qiushunming
 * 2014年8月6日
 */
public interface IDataCache {
	
	public  boolean pub(String key, Object value);
	
	public  Object get(String key);
	
	public  boolean update(String key, Object value);
	
	public boolean remove(String key);
	
	public boolean chear();
	
	public Collection<String> keys();
}
