package com.owl.baselib.utils.asert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.os.Environment;



/**
 * 断言类
 * @author qiushunming
 * 2014年7月28日
 */
public class AppAssert {
	
	private final static boolean DEBUG = true;
	
	public static StringBuffer ASSERT_BUFFER = new StringBuffer();
	
	/**
	 * 如果condition为true，断言不触发，程序正常执行
	 * 如果condition为false，则触发断言，显示提示消息
	 * @param message 提示消息
	 * @param condition 判断条件
	 */
	public static void assertTrue(String message, boolean condition){
		AppAssert.assertTrue(message, condition);
		if(!condition){
			//最多输出500K个字节
			if(ASSERT_BUFFER.length() < 512*1024){
				ASSERT_BUFFER.append(message);
				ASSERT_BUFFER.append("\n");
				ASSERT_BUFFER.append("\r\n");
			}
		}
	}
	
	/**
	 * 将断言信息输出 到文件
	 */
	public static void outputAssert(){
		if(DEBUG && Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
			try {
				File filePath = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/LaoyouzaAssert");
				if(!filePath.exists()){
					filePath.mkdirs();
				}
				SimpleDateFormat format = new SimpleDateFormat("_yyyy_MM_dd_HH_mm_ss", Locale.CHINA);
				File file = new File(filePath.getAbsoluteFile() + "/assert" + format.format(new Date(System.currentTimeMillis())) + ".txt");
				file.createNewFile();
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(ASSERT_BUFFER.toString().getBytes("utf-8"));
				fos.flush();
				fos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//输出后清除
		ASSERT_BUFFER.reverse();
	}
}
