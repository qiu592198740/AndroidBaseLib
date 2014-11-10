package com.owl.baselib.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.xutils.ViewUtils;
import com.owl.baselib.R;
import com.owl.baselib.app.event.HttpEvent;
import com.owl.baselib.utils.log.LogUtils;

public abstract class BaseFragment extends Fragment{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LogUtils.d(this.getClass().getSimpleName() + "--->" + "onCreate");
	}

	@Override
	public void onPause() {
		super.onPause();
		// imageLoader.pause();
		LogUtils.d(this.getClass().getSimpleName() + "--->" + "onPause");
	}

	@Override
	public void startActivity(Intent intent) {
		super.startActivity(intent);
		setStartAnimation();
	}

	@Override
	public void startActivityForResult(Intent intent, int requestCode) {
		super.startActivityForResult(intent, requestCode);
		setStartAnimation();
	}

	/**
	 * 设置activity的启动动画，默认是新的activity从右边进入，旧的activity从左边退出，如果需要其它动画效果，需要重写该方法
	 */
	protected void setStartAnimation() {
		getActivity().overridePendingTransition(R.anim.push_right_in,
				R.anim.push_left_out);
	}

	@Override
	public void onResume() {
		super.onResume();
		// imageLoader.resume();
		LogUtils.d(this.getClass().getSimpleName() + "--->" + "onResume");
	}

	@Override
	public void onStop() {
		super.onStop();
		// imageLoader.stop();
		LogUtils.d(this.getClass().getSimpleName() + "--->" + "onStop");
	}
	
	@Override
	public void onDetach() {
		super.onDetach();
		LogUtils.d(this.getClass().getSimpleName() + "--->" + "onDetach");

	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		LogUtils.d(this.getClass().getSimpleName() + "--->" + "onAttach");

	}

	/**
	 * 处理fragment按键事件
	 * 
	 * @param keyCode
	 * @param event
	 * @return
	 */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return false;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(getFragmentViewResId(), container, false);

		ViewUtils.inject(this, view);
		
		initializeViews(view, inflater);

		LogUtils.d(this.getClass().getSimpleName() + "--->" + "onCreateView");

		return view;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		LogUtils.d(this.getClass().getSimpleName() + "--->" + "onDestroy");

	}

	/**
	 * 获取设置fragment的视图布局文件ID
	 * 
	 * @return 视图布局文件ID
	 */
	protected abstract int getFragmentViewResId();

	/**
	 * 初始化视图其它相关操作，例如适配器
	 * 
	 * @param fragmentView
	 *            该fragment的视图
	 * @param inflater
	 */
	protected abstract void initializeViews(View rootView,
			LayoutInflater inflater);

	@Override
	public void onDestroyView() {
		super.onDestroyView();

		LogUtils.d(this.getClass().getSimpleName() + "--->" + "onDestroyView");
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		LogUtils.d(this.getClass().getSimpleName() + "--->"
				+ "onActivityCreated");
	}

	/**
	 * 转到下一个指定的activity，并不finish当前activity
	 * 
	 * @param intent
	 */
	public void gotoNextActivity(Intent intent) {
		gotoNextActivity(intent, false);
	}

	public void gotoNextActivity(Intent intent, boolean shouldFinish) {
		startActivity(intent);
		if (shouldFinish) {
			AppActivityManager.getInstance().finishActivity();
		}
	}

	/**
	 * 跳转到指定的activity，不结束当前activity
	 * 
	 * @param clazz
	 */
	public void gotoNextActivity(Class<? extends Activity> clazz) {
		gotoNextActivity(clazz, false);
	}

	/**
	 * 
	 * @param clazz
	 *            需要跳转到的activity的类
	 * @param shouldFinish
	 *            是否finish当前activity
	 */
	public void gotoNextActivity(Class<? extends Activity> clazz,
			boolean shouldFinish) {
		Intent intent = new Intent(getActivity(), clazz);
		gotoNextActivity(intent, shouldFinish);
	}
	
	/**
	 * 由关联的Activity调用的EventBus事件处理
	 * @param event
	 */
	public void onFragmentHttpEvent(HttpEvent event){
		
		int requestStatus = event.getStatus();
		switch (requestStatus) {
		case HttpEvent.STATUS_CONNECTING:
			onConnecting(event);
			break;
		case HttpEvent.STATUS_DATA_READING:
			onDataReading(event);
			break;
		case HttpEvent.STATUS_TASK_CANCEL:
			onTaskCancel(event);
			break;
		case HttpEvent.STATUS_SUC:
			onSuccess(event);
			break;
		case HttpEvent.STATUS_ERROR:
			onError(event);
			break;

		default:
			LogUtils.e("unknow status:" + requestStatus + "cmdId:" + event.getCmdId());
			break;
		}
	}
	
	//处理网络事件
	protected abstract void onConnecting(HttpEvent event);

	protected abstract void onDataReading(HttpEvent event);

	protected abstract void onTaskCancel(HttpEvent event);

	protected abstract void onSuccess(HttpEvent event);

	protected abstract void onError(HttpEvent event);
}
