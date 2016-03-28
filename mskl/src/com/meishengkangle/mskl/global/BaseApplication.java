package com.meishengkangle.mskl.global;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * 定义一个全局的Application
 * @author MS
 *
 */
public class BaseApplication extends Application {

	private static Context context;
	private static int mainThreadId;
	private static Handler handler;

	@Override
	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();
		mainThreadId = android.os.Process.myTid();// 获取主线程Id
		handler = new Handler();
	}

	public static Context getContext() {
		return context;
	}

	public static int getMainThreadId() {
		return mainThreadId;
	}

	public static Handler getHandler() {
		return handler;
	}
}
