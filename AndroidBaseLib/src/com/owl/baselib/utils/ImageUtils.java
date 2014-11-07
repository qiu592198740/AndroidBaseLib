package com.owl.baselib.utils;

import java.io.ByteArrayOutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;

import com.owl.baselib.utils.network.NetUtils;

/**
 * 图片处理工具
 * 
 * @author qiushunming 2014年7月28日
 */
public class ImageUtils {

	/**
	 * 图片压缩方法
	 * 
	 * @param srcPath
	 * @param context
	 * @return
	 */
	public static byte[] compressImageFromFile(String srcPath, Context context) {

		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		newOpts.inJustDecodeBounds = true;// 只读边,不读内容
		Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);

		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
//		int h = newOpts.outHeight;
//		float hh = 800f;//
		float ww = 480f;//
		int be = 1;

		if (w > ww) {
			be = (int) (newOpts.outWidth / ww);
		}

		// if (w > h && w > ww) {
		// be = (int) (newOpts.outWidth / ww);
		// } else if (w < h && h > hh) {
		// be = (int) (newOpts.outHeight / hh);
		// }
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;// 设置采样率

		newOpts.inPreferredConfig = Config.ARGB_8888;// 该模式是默认的,可不设
		newOpts.inPurgeable = true;// 同时设置才会有效
		newOpts.inInputShareable = true;// 。当系统内存不够时候图片自动被回收

		bitmap = BitmapFactory.decodeFile(srcPath, newOpts);

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		if (NetUtils.isWifi(context)) {
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
		} else {
			bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
		}

		byte[] byteArray = stream.toByteArray();

		return byteArray;
	}

}
