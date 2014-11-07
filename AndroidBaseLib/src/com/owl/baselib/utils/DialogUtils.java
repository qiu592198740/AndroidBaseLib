package com.owl.baselib.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

/**
 * 对话框工具类
 * @author qiushunming
 * 2014年8月22日
 */
public class DialogUtils {
	
	/**
	 * progressDialog
	 */
	public static final int DIALOG_TYPE_PROGRESS = 1;
	
	/**
	 * alertDialog
	 */
	public static final int DIALOG_TYPE_ALERT = 2;
	
	Context mContext;

	ProgressDialog mProgressDialog;
	
	AlertDialog.Builder mAlertDialogBuilder;
	
	AlertDialog mAlertDialog;
	
	public DialogUtils(Context context) {
		mContext = context;
		mProgressDialog = new ProgressDialog(context);
		mAlertDialogBuilder = new AlertDialog.Builder(context);
	}
	
	public void showNormalLoading(){
		showProgressDialog(false, "加载中，请稍后...");
	}
	
	public void showProgressDialog(int titleId, boolean cancelable, CharSequence message){
		mProgressDialog.setTitle(titleId);
		showProgressDialog(cancelable, message);
	}
	
	public void showProgressDialog(CharSequence title, boolean cancelable, CharSequence message){
		mProgressDialog.setTitle(title);
		showProgressDialog(cancelable, message);
	}
	
	public void showProgressDialog(boolean cancelable, CharSequence message){
		mProgressDialog.setCancelable(cancelable);
		mProgressDialog.setMessage(message);
		mProgressDialog.show();
	}
	
	public void cancelProgressDialog(){
		if (mProgressDialog.isShowing()) {
			cancelProgressDialog(null);
		}
	}
	
	public void cancelProgressDialog(OnCancelListener listener){
		mProgressDialog.setOnCancelListener(listener);
		mProgressDialog.cancel();
	}
	
	public void showAlertDialog(int titleId, boolean cancelable, CharSequence message){
		if (mAlertDialog != null && mAlertDialog.isShowing()) {
			mAlertDialog.cancel();
		}
		mAlertDialogBuilder.setTitle(titleId);
		mAlertDialogBuilder.setMessage(message);
//		mAlertDialogBuilder.setCancelable(cancelable);
		mAlertDialog = mAlertDialogBuilder.show();
	}
	
	public void showAlertDialog(int titleId, boolean cancelable,
			CharSequence message, String positiveButtonText,
			DialogInterface.OnClickListener positiveListener,  String negativeButtonText,
			DialogInterface.OnClickListener negativeListener) {
		mAlertDialogBuilder.setTitle(titleId);
		mAlertDialogBuilder.setMessage(message);
		mAlertDialogBuilder.setPositiveButton(positiveButtonText, positiveListener);
		mAlertDialogBuilder.setNegativeButton(negativeButtonText, negativeListener);
		mAlertDialogBuilder.show();
	}
	
	public void cancelAlertDialog(){
		
	}
}
