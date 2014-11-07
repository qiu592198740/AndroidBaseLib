package com.owl.baselib.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class BaseService extends Service {

	private EventBusWrapper mEventBus;

	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mEventBus = EventBusWrapper.getInstance();
		mEventBus.register(this);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onLowMemory() {
		super.onLowMemory();
		//清除优先级低的缓存
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		
		mEventBus.unregister(this);
	}
	
}
