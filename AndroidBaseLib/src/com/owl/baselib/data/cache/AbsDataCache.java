package com.owl.baselib.data.cache;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public abstract class AbsDataCache implements IDataCache {

	private Map<String ,Object> cache = Collections.synchronizedMap(new HashMap<String, Object>()); 
	
	@Override
	public boolean pub(String key, Object value) {
		Object obj = cache.put(key, value);
		return obj != null;
	}

	@Override
	public Object get(String key) {
		return cache.get(key);
	}

	@Override
	public boolean update(String key, Object value) {
		return false;
	}

	@Override
	public boolean remove(String key) {
		return false;
	}

	@Override
	public boolean chear() {
		cache.clear();
		return true;
	}

	@Override
	public Collection<String> keys() {
		synchronized (cache) {
			return new HashSet<String>(cache.keySet());
		}
	}
}
