package com.owl.baselib.utils;

import android.content.Context;
import android.util.DisplayMetrics;

public class ScreenUtils {

	/**
	 * 获取屏幕分辨率
	 * 
	 * @param context
	 *            当前内容
	 * @return 整型
	 */
	public static int getScreenMetrics(Context context) {
		byte byte0 = 0;
		new DisplayMetrics();
		DisplayMetrics displaymetrics = context.getApplicationContext()
				.getResources().getDisplayMetrics();
		int i = displaymetrics.widthPixels;
		int j = displaymetrics.heightPixels;
		if (i != 480 || j != 800) {
			if (i != 320 || j != 480) {
				if (i != 240 || j != 320) {
					if (i != 640 || j != 960) {
						if (i == 720)
							byte0 = 5;
					}
					else {
						byte0 = 4;
					}
				}
				else {
					byte0 = 1;
				}
			}
			else {
				byte0 = 2;
			}
		}
		else {
			byte0 = 3;
		}
		return byte0;
	}

	/**
	 * 获取手机屏幕宽
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenWidth(Context context) {
		new DisplayMetrics();
		return context.getApplicationContext().getResources()
				.getDisplayMetrics().widthPixels;
	}

	/**
	 * 获取手机屏幕高
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenHeight(Context context) {
		new DisplayMetrics();
		return context.getApplicationContext().getResources()
				.getDisplayMetrics().heightPixels;
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
}
