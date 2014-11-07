package com.owl.baselib.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.owl.baselib.R;

/**
 * 图片加载器管理类
 * @author qiushunming
 * 2014年9月22日
 */
public class ImageLoaderWrapper {
	private DisplayImageOptions mLoadHeadOption;
	private DisplayImageOptions mLoadUserBgOption;
	private DisplayImageOptions mLoadListPhotoOption;
	private DisplayImageOptions mLoadNormalPhotoOption;
	
	
	private static final ImageLoaderWrapper sInstance = new ImageLoaderWrapper();

	private ImageLoaderWrapper() {
	}

	public static ImageLoaderWrapper getInstance() {
		return sInstance;
	}
	
	public void initializeImageLoader(Context context) {
		ImageLoaderConfiguration imageLoaderConfiguration = new ImageLoaderConfiguration.Builder(
				context).memoryCacheExtraOptions(480, 800) // TODO 有待斟酌
				.denyCacheImageMultipleSizesInMemory() // 内存中只存一份
//				.writeDebugLogs()// TODO 发布版本需要关闭改log
				.build();
		com.nostra13.universalimageloader.utils.L.writeLogs(false); //关闭打印
		ImageLoader.getInstance().init(imageLoaderConfiguration); // 初始化

	}
	
	public void displayImage(String uri, ImageView imageAware,  DisplayImageOptions options){
		ImageLoader.getInstance().displayImage(uri, imageAware, options);
	}
	
	public void loadImage(String uri, ImageSize targetImageSize, ImageLoadingListener listener){
		ImageLoader.getInstance().loadImage(uri, targetImageSize, listener);
	}
	
	public void loadImage(String uri, ImageSize targetImageSize, DisplayImageOptions options, ImageLoadingListener listener){
		ImageLoader.getInstance().loadImage(uri, targetImageSize, options, listener);
	}
	
	/**
	 * 获取一般头像选项
	 * @return
	 */
	public DisplayImageOptions getLoadHeadImageOptions() {
		
		if (mLoadHeadOption == null) {
			mLoadHeadOption = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.drawable.g_head_default)
			.showImageForEmptyUri(R.drawable.g_head_default)
			.showImageOnFail(R.drawable.g_head_default)
			.cacheInMemory(true)
			.cacheOnDisk(false)
			.considerExifParams(true)
			.bitmapConfig(Bitmap.Config.RGB_565)
			.build();	
		}
		
		return mLoadHeadOption;
	}
	
	
	public DisplayImageOptions getLoadUserBgOptions() {
		
		if (mLoadUserBgOption == null) {
			mLoadUserBgOption = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.drawable.g_default_image)
			.showImageForEmptyUri(R.drawable.g_default_image)
			.showImageOnFail(R.drawable.g_default_image)
			.cacheInMemory(true)
			.cacheOnDisk(false)
			.considerExifParams(true)
			.bitmapConfig(Bitmap.Config.RGB_565)
			.build();	
		}
		
		return mLoadUserBgOption;
	}
	
	/**
	 * 获取列表图片选项
	 * @param delayInMillis
	 * @return
	 */
	public DisplayImageOptions getLoadListPhotoImageOptions(int delayInMillis) {
		if (mLoadListPhotoOption == null) {
			mLoadListPhotoOption = new DisplayImageOptions.Builder().cacheInMemory(true)
					.cacheOnDisk(true).delayBeforeLoading(delayInMillis)
					.showImageOnLoading(R.drawable.g_default_image)
					.showImageOnFail(R.drawable.g_default_image).build();
		}
		
		return mLoadListPhotoOption;
	}
	
	/**
	 * 获取非列表图片数据选项
	 * @return
	 */
	public DisplayImageOptions getLoadNormalPhotoImageOptions() {
		if (mLoadNormalPhotoOption == null) {
			mLoadNormalPhotoOption = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.g_default_image)
				.showImageForEmptyUri(R.drawable.g_default_image)
				.showImageOnFail(R.drawable.g_default_image).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
				.bitmapConfig(Bitmap.Config.RGB_565).build();
		}
		return mLoadNormalPhotoOption;
	}
	
	/**
	 * 清除内存缓存
	 */
	public void clearMomeryCache(){
		ImageLoader.getInstance().getMemoryCache().clear();
	}
}
