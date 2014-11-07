package com.owl.baselib.utils.network;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;

import org.apache.http.HttpHost;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络工具类
 * @author qiushunming
 * 2014年7月29日
 */
public class NetUtils {
	
	public static final String CTWAP = "ctwap";
	public static final String CTNET = "ctnet";
	public static final String CMWAP = "cmwap";
	public static final String UNIWAP = "uniwap";

	/**
	 * 根据当前网络接入点设置HttpURLConnection 代理
	 * @param context
	 * @param strUrl
	 * @return
	 * @throws IOException
	 */
	public static HttpURLConnection openHttpConnection(Context context, String strUrl) throws IOException{
		//设置cmwap代理（方式2），得到移动session时还有一种cmwap代理设置方式，那种比较麻烦需要截取地址设置
		URL url = new URL(strUrl);
		HttpURLConnection mConn = null;
    	if (isWifi(context)) {
    		mConn = (HttpURLConnection)url.openConnection();
    		return mConn;
		}else if(getAPNState(context).equalsIgnoreCase(CMWAP)){
			String proxyHost = android.net.Proxy.getDefaultHost(); 
			if (proxyHost != null) { 
				java.net.Proxy proxy = new java.net.Proxy(java.net.Proxy.Type.HTTP, 
					new InetSocketAddress(android.net.Proxy.getDefaultHost(), 
					android.net.Proxy.getDefaultPort())); 
				mConn = (HttpURLConnection) url.openConnection(proxy);
				return mConn;
			}			
		}else if(getAPNState(context).equalsIgnoreCase(CTWAP)){
			String proxyHost = android.net.Proxy.getDefaultHost(); 
			if (proxyHost != null) { 
				java.net.Proxy proxy = new java.net.Proxy(java.net.Proxy.Type.HTTP, 
					new InetSocketAddress(android.net.Proxy.getDefaultHost(), 
					android.net.Proxy.getDefaultPort())); 
				mConn = (HttpURLConnection) url.openConnection(proxy);
				return mConn;
			}			
		}else if(getAPNState(context).equalsIgnoreCase(UNIWAP)){
			String proxyHost = android.net.Proxy.getDefaultHost(); 
			if (proxyHost != null) { 
				java.net.Proxy proxy = new java.net.Proxy(java.net.Proxy.Type.HTTP, 
					new InetSocketAddress(android.net.Proxy.getDefaultHost(), 
					android.net.Proxy.getDefaultPort())); 
				mConn = (HttpURLConnection) url.openConnection(proxy);
				return mConn;
			}			
		}
    	
    	return (HttpURLConnection) url.openConnection();
	}
	
	/**
	 * 判断当前网络是否是wifi
	 * @return true 是wifi false 不是或者没有网络
	 */
	public static boolean isWifi(Context context){
		ConnectivityManager connectManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectManager.getActiveNetworkInfo();
		if (networkInfo == null) {
			return false;
		}
		int type = networkInfo.getType();
		if (ConnectivityManager.TYPE_WIFI == type) {
			return true;
		}
		return false;
	}
	
	/**
	 * 获取网络apn信息
	 * @param context
	 * @return
	 */
	public static String getAPNState(Context context){
		// 通过context得到ConnectivityManager连接管理  
        String apn = null; 
        ConnectivityManager manager = (ConnectivityManager) context  
                .getSystemService(Context.CONNECTIVITY_SERVICE);  
        // 通过ConnectivityManager得到NetworkInfo网络信息  
        NetworkInfo info = manager.getActiveNetworkInfo();  
        // 获取NetworkInfo中的apn信息  
        if (info != null) {  
            apn = info.getExtraInfo();  
            if (apn == null) {  
                apn = "取不到移动网络信息";  
            }  
        } else {  
            apn = "取不到网络信息";  
        } 
        //Debug.e("APN", "接入点名称：" + apn);
        return apn;  

	}
	
	/**
	 * 获取apn类型
	 * @param context
	 * @return
	 */
	public static String getAPNType(Context context){
		// 通过context得到ConnectivityManager连接管理  
        String apn = null; 
        ConnectivityManager manager = (ConnectivityManager) context  
                .getSystemService(Context.CONNECTIVITY_SERVICE);  
        // 通过ConnectivityManager得到NetworkInfo网络信息  
        NetworkInfo info = manager.getActiveNetworkInfo();  
        // 获取NetworkInfo中的apn信息  
        if (info != null) {  
            apn = info.getExtraInfo();  
//            if (apn == null) {  
//                apn = "取不到移动网络信息";  
//            }  
        }
//        else {  
//            apn = "取不到网络信息";  
//        } 
        //Debug.e("APN", "接入点名称：" + apn);
        return apn;  

	}
	
	/**
	 * 根据网络类型获取代理地址
	 * @param context
	 * @return
	 */
	public static HttpHost getProxy(Context context){
		//设置cmwap代理（方式2），得到移动session时还有一种cmwap代理设置方式，那种比较麻烦需要截取地址设置
		if (isWifi(context)) {
    		return null;
		}else if(getAPNState(context).equalsIgnoreCase(CMWAP)){
			String proxyHost = android.net.Proxy.getDefaultHost(); 
			if (proxyHost != null) { 
				HttpHost proxy = new HttpHost(android.net.Proxy.getDefaultHost(), 
					android.net.Proxy.getDefaultPort()); 
				return proxy;
			}		
		}else if(getAPNState(context).equalsIgnoreCase(CTWAP)){
			String proxyHost = android.net.Proxy.getDefaultHost(); 
			if (proxyHost != null) { 
				HttpHost proxy = new HttpHost(android.net.Proxy.getDefaultHost(), 
					android.net.Proxy.getDefaultPort()); 
				return proxy;
			}
		}else if(getAPNState(context).equalsIgnoreCase(UNIWAP)){
			String proxyHost = android.net.Proxy.getDefaultHost(); 
			if (proxyHost != null) { 
				HttpHost proxy = new HttpHost(android.net.Proxy.getDefaultHost(), 
					android.net.Proxy.getDefaultPort()); 
				return proxy;
			}
		}
    	
    	return null;
	}

}
