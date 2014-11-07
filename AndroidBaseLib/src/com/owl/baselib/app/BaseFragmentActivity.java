package com.owl.baselib.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;

import com.lidroid.xutils.ViewUtils;
import com.owl.baselib.R;
import com.owl.baselib.utils.DialogUtils;
import com.owl.baselib.utils.log.LogUtils;
/**
 * 基础activity类
 * @author qiushunming
 *
 */
public abstract class BaseFragmentActivity extends FragmentActivity {
	public static final String RESUME_ACTION = "com.owl.android.baselib.action.app_resume";
	
	protected DialogUtils mDialogUtils;

	public DialogUtils getDialogUtils() {
		return mDialogUtils;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LogUtils.d(getClass().getSimpleName() + "---->" + "onCreate");
		
		//对话框工具
		mDialogUtils = new DialogUtils(this);

		//添加至activity管理器
		AppActivityManager.getInstance().addActivity(this);

		//子类实现
		setContentView();

		//支持注入
		ViewUtils.inject(this);

		//子类实现
		initializeData();
		initializeViews();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		LogUtils.d(getClass().getSimpleName() + "---->" + "onRestart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		LogUtils.d(getClass().getSimpleName() + "---->" + "onResume");
		
		if (BaseApplication.APP_STATE_PAUSE == BaseApplication.getAppState()) {
			//应用重新返回到最前
			Intent intent = new Intent(RESUME_ACTION);
			sendBroadcast(intent);
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onPause() {
		super.onPause();
		LogUtils.d(getClass().getSimpleName() + "---->" + "onPause");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
//		AppActivityManager.getInstance().finishActivity(this);
		AppActivityManager.getInstance().remove(this);

		LogUtils.d(getClass().getSimpleName() + "---->" + "onDestroy");
	}

	@Override
	public void startActivity(Intent intent) {
		super.startActivity(intent);
		LogUtils.d(getClass().getSimpleName() + "---->" + "startActivity");
		setStartAnimation();
	}

	@Override
	public void startActivityForResult(Intent intent, int requestCode) {
		super.startActivityForResult(intent, requestCode);
		LogUtils.d(getClass().getSimpleName() + "---->"
				+ "startActivityForResult");
		setStartAnimation();
	}

	@Override
	public void finish() {
		super.finish();
		LogUtils.d(getClass().getSimpleName() + "---->" + "finish");
		setFinishAnimation();
	}

	/**
	 * 设置activity的结束动画，默认是新的activity从右边进入，旧的activity从左边退出，如果需要其它动画效果，需要重写该方法
	 */
	protected void setFinishAnimation() {
		overridePendingTransition(R.anim.push_left_in, R.anim.push_right_out);
	}

	/**
	 * 设置activity的启动动画，默认是新的activity从右边进入，旧的activity从左边退出，如果需要其它动画效果，需要重写该方法
	 */
	protected void setStartAnimation() {
		overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			LogUtils.d(getClass().getSimpleName() + "---->"
					+ "fire KEYCODE_BACK event");
			finish();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		LogUtils.d(getClass().getSimpleName() + "---->" + "onSaveInstanceState");

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
			finish();
		}
	}

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
		Intent intent = new Intent(this, clazz);
		gotoNextActivity(intent, shouldFinish);
	}

	/**
	 * 当前显示的fragment
	 */
	private Fragment mCurrentFragment = null;
	
	/**
	 * 改变根节点的fragment，被替换的fragment将触发它的onPause方法，
	 * 目标fragment如果已经在之前加入到fragment管理器中进行管理
	 * ，则目标fragment只会触发onResume方法，不会重新走一遍生命周期.
	 * 
	 * @param targetResId
	 * @param targetFragment
	 */
	public void changeFragment(int targetResId,
			Class<? extends Fragment> targetFragment) {

		if (targetFragment == null) {
			throw new IllegalArgumentException(
					"targetFragment should not be null.");
		}

		try {
			FragmentTransaction ft = getSupportFragmentManager()
					.beginTransaction();

			Fragment target = null;
			if (mCurrentFragment == null) {
				target = targetFragment.newInstance();
				ft.add(targetResId, target, targetFragment.getName());
			} else {

				FragmentManager fm = getSupportFragmentManager();
				target = fm.findFragmentByTag(targetFragment.getName());

				if (mCurrentFragment.getClass() != targetFragment) {

					ft.hide(mCurrentFragment);
					mCurrentFragment.onPause();

					if (target == null) {
						target = targetFragment.newInstance();
						ft.add(targetResId, target, targetFragment.getName());
					} else {
						target.onResume();
					}
				}

			}

			ft.show(target);
			mCurrentFragment = target;
//			ft.commitAllowingStateLoss();
			ft.commit();

		} catch (Exception e) {
			LogUtils.e(targetFragment.getName() + " cannot new instance.", e);
		}

	}

	/**
	 * 关闭输入法
	 * 
	 * @param windowToken
	 */
	public void closeKeyBoard(IBinder windowToken) {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(windowToken, 0);
	}
	

	/**
	 * 设置内容视图
	 */
	protected abstract void setContentView();

	/**
	 * 初始化数据
	 */
	protected abstract void initializeData();
	

	/**
	 * 初始化其它view
	 */
	protected abstract void initializeViews();
}
