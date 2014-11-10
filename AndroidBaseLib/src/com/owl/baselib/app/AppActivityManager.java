package com.owl.baselib.app;

import java.util.Stack;

import android.app.Activity;

import com.owl.baselib.utils.log.LogUtils;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 * @author owl
 * 2014年11月10日
 */
public class AppActivityManager {
	private static Stack<BaseFragmentActivity> sActivityStack = new Stack<BaseFragmentActivity>();

	private static final AppActivityManager sInstance = new AppActivityManager();

	private AppActivityManager() {
	}

	public static AppActivityManager getInstance() {
		return sInstance;
	}

	public int getCount() {
		return sActivityStack.size();
	}

	/**
	 * 添加Activity到栈
	 */
	public void addActivity(BaseFragmentActivity activity) {
		synchronized (sActivityStack) {
			sActivityStack.add(activity);
			LogUtils.d("push " + activity.getClass().getName()
					+ " into activity stack.");
		}
	}

	/**
	 * 获取当前Activity（栈顶Activity）
	 */
	public BaseFragmentActivity currentActivity() {
		if (sActivityStack == null || sActivityStack.isEmpty()) {
			return null;
		}
		BaseFragmentActivity activity = sActivityStack.lastElement();
		return activity;
	}

	/**
	 * 获取当前Activity（栈顶Activity） 没有找到则返回null
	 */
	public BaseFragmentActivity findActivity(Class<? extends Activity> cls) {
		BaseFragmentActivity activity = null;
		for (BaseFragmentActivity aty : sActivityStack) {
			if (aty.getClass().equals(cls)) {
				activity = aty;
				break;
			}
		}
		return activity;
	}

	/**
	 * 结束当前Activity（栈顶Activity）
	 */
	public void finishActivity() {
		BaseFragmentActivity activity = sActivityStack.lastElement();
		finishActivity(activity);
	}

	/**
	 * 结束指定的Activity
	 */
	public void finishActivity(Activity activity) {
		if (activity != null) {
			activity.finish();
		}
	}
	
	public void remove(Activity activity){
		if (sActivityStack.contains(activity)) {
			sActivityStack.remove(activity);
			LogUtils.d("pop " + activity.getClass().getName()
					+ " from activity stack.");
		}else{
			LogUtils.d(activity.getClass().getName()
					+ " has been remove from stack.");
		}
	}

	/**
	 * 结束指定的Activity
	 */
	public void finishActivity(Class<? extends Activity> cls) {
		for (BaseFragmentActivity activity : sActivityStack) {
			if (activity.getClass().equals(cls)) {
				finishActivity(activity);
			}
		}
	}

	/**
	 * 关闭除了指定activity以外的全部activity 如果cls不存在于栈中，则栈全部清空
	 * 
	 * @param cls
	 */
	public void finishOthersActivity(Class<? extends Activity> cls) {
		for (BaseFragmentActivity activity : sActivityStack) {
			if (!(activity.getClass().equals(cls))) {
				finishActivity(activity);
			}
		}
	}

	/**
	 * 结束所有Activity
	 */
	public void finishAllActivity() {

		synchronized (sActivityStack) {
			for (int i = 0, size = sActivityStack.size(); i < size; i++) {
				if (null != sActivityStack.get(i)) {
					sActivityStack.get(i).finish();
				}
			}
			sActivityStack.clear();
		}

	}
	
	public void printStack(){
		LogUtils.i(sActivityStack.toString());
	}

	/**
	 * 应用程序退出
	 */
	// public void AppExit(Context context) {
	// try {
	// finishAllActivity();
	// ActivityManager activityMgr = (ActivityManager) context
	// .getSystemService(Context.ACTIVITY_SERVICE);
	// activityMgr.killBackgroundProcesses(context.getPackageName());
	// System.exit(0);
	// }
	// catch (Exception e) {
	// System.exit(-1);
	// }
	// }
}