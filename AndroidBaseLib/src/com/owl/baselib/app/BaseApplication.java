package com.owl.baselib.app;

import android.app.Application;
import android.content.Context;

public class BaseApplication extends Application {
	public static final String APP_START_ACTION = "app_start_action";
	public static final String APP_FINISH_ACTION = "app_finish_action";

	public static final int APP_STATE_UNKOWN = -1;

	public static final int APP_STATE_RUNNING = 1;
	public static final int APP_STATE_PAUSE = 2;
	public static final int APP_STATE_DESTROY = 3;

	private static int appState;

	private static Context sAppContext;

//	private static RequesterProxy mRequestProxy;

	@Override
	public void onCreate() {
		super.onCreate();
		sAppContext = getApplicationContext();
		
		setAppState(APP_STATE_UNKOWN);

		ImageLoaderWrapper.getInstance().initializeImageLoader(sAppContext);
	}

	public static Context getAppContext() {
		return sAppContext;
	}


	public synchronized static void setAppState(int state) {
		appState = state;
	}
	
	public synchronized static int getAppState() {
		return appState;
	}	
}
