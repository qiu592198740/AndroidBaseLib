package com.owl.baselib.utils.log;

import android.text.TextUtils;
import android.util.Log;

import com.lidroid.xutils.util.OtherUtils;

/**
 * Log工具，类似android.util.Log。 tag自动产生，格式:
 * customTagPrefix:className.methodName(L:lineNumber),
 * customTagPrefix为空时只输出：className.methodName(L:lineNumber)。
 * <p/>
 */
public class LogUtils {

	public static String customTagPrefix = "";

	private LogUtils() {
	}
	
	public static boolean allowV = false;
	public static boolean allowD = false;
	public static boolean allowE = true;
	public static boolean allowI = false;
//	public static boolean allowV = true;
//	public static boolean allowD = true;
//	public static boolean allowE = true;
//	public static boolean allowI = true;

	private static String generateTag(StackTraceElement caller) {
		String tag = "%s.%s(L:%d)";
		String callerClazzName = caller.getClassName();
		callerClazzName = callerClazzName.substring(callerClazzName
				.lastIndexOf(".") + 1);
		tag = String.format(tag, callerClazzName, caller.getMethodName(),
				caller.getLineNumber());
		tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":"
				+ tag;
		return tag;
	}

	public static CustomLogger customLogger;

	public interface CustomLogger {
		void d(String tag, String content);

		void d(String tag, String content, Throwable tr);

		void e(String tag, String content);

		void e(String tag, String content, Throwable tr);

		void i(String tag, String content);

		void i(String tag, String content, Throwable tr);

		void v(String tag, String content);

		void v(String tag, String content, Throwable tr);

	}

	public static void v(String content) {
		if (!allowV)
			return;
		StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
		String tag = generateTag(caller);

		if (customLogger != null) {
			customLogger.v(tag, content);
		} else {
			Log.v(tag, content);
		}
	}

	public static void v(String content, Throwable tr) {
		if (!allowV)
			return;
		StackTraceElement caller = OtherUtils.getCallerStackTraceElement();
		String tag = generateTag(caller);

		if (customLogger != null) {
			customLogger.v(tag, content, tr);
		} else {
			Log.v(tag, content, tr);
		}
	}

	public static void d(String content) {
		if (!allowD)
			return;
		StackTraceElement caller = getCallerStackTraceElement();
		String tag = generateTag(caller);

		if (customLogger != null) {
			customLogger.d(tag, content);
		} else {
			Log.d(tag, content);
		}
	}

	public static void d(String content, Throwable tr) {
		if (!allowD)
			return;
		StackTraceElement caller = getCallerStackTraceElement();
		String tag = generateTag(caller);

		if (customLogger != null) {
			customLogger.d(tag, content, tr);
		} else {
			Log.d(tag, content, tr);
		}
	}

	public static void e(String content) {
		if (!allowE)
			return;
		StackTraceElement caller = getCallerStackTraceElement();
		String tag = generateTag(caller);

		if (customLogger != null) {
			customLogger.e(tag, content);
		} else {
			Log.e(tag, content);
		}
	}

	public static void e(String content, Throwable tr) {
		if (!allowE)
			return;
		StackTraceElement caller = getCallerStackTraceElement();
		String tag = generateTag(caller);

		if (customLogger != null) {
			customLogger.e(tag, content, tr);
		} else {
			Log.e(tag, content, tr);
		}
	}

	public static void i(String content) {
		if (!allowI)
			return;
		StackTraceElement caller = getCallerStackTraceElement();
		String tag = generateTag(caller);

		if (customLogger != null) {
			customLogger.i(tag, content);
		} else {
			Log.i(tag, content);
		}
	}

	public static void i(String content, Throwable tr) {
		if (!allowI)
			return;
		StackTraceElement caller = getCallerStackTraceElement();
		String tag = generateTag(caller);

		if (customLogger != null) {
			customLogger.i(tag, content, tr);
		} else {
			Log.i(tag, content, tr);
		}
	}

	public static StackTraceElement getCallerStackTraceElement() {
		return Thread.currentThread().getStackTrace()[4];
	}

}
