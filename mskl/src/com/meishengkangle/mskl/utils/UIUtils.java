package com.meishengkangle.mskl.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ParseException;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import com.meishengkangle.mskl.global.BaseApplication;

/**
 * 工具类，专门处理UI的相关操作
 * 
 * @author MS
 * 
 */
public class UIUtils {

	public static Context getContext() {
		return BaseApplication.getContext();
	}

	public static Handler getHandler() {
		return BaseApplication.getHandler();
	}

	public static int getMainThreadId() {
		return BaseApplication.getMainThreadId();
	}

	// 根据资源id获取字符串

	public static String getString(int id) {
		return getContext().getResources().getString(id);
	}

	// 根据资源id获取图片
	public static Drawable getDrawable(int id) {
		return getContext().getResources().getDrawable(id);
	}

	// 根据id获取颜色值
	public static int getColor(int id) {
		return getContext().getResources().getColor(id);
	}

	/**
	 * dp转px
	 */
	public static int dip2px(Context context, float dp) {
		float density = context.getResources().getDisplayMetrics().density;
		return (int) (density * dp + 0.5);
	}

	/**
	 * px转dp
	 */
	public static float px2dip(float px) {
		float density = getContext().getResources().getDisplayMetrics().density;
		return px / density;
	}

	/**
	 * 加载布局文件
	 */
	public static View inflate(int layoutId) {
		return View.inflate(getContext(), layoutId, null);
	}

	/**
	 * 判断当前是否运行在主线程
	 * 
	 * @return
	 */
	public static boolean isRunOnUiThread() {
		return getMainThreadId() == android.os.Process.myTid();
	}

	/**
	 * 保证当前的操作运行在UI主线程
	 * 
	 * @param runnable
	 */
	public static void runOnUiThread(Runnable runnable) {
		if (isRunOnUiThread()) {
			runnable.run();
		} else {
			getHandler().post(runnable);
		}
	}

	private static String mYear;
	private static String mMonth;
	private static String mDay;
	private static String mWay;
	private static final Calendar c = Calendar.getInstance();

	public static String getData() {

		c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
		mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
		mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
		mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
		if ("1".equals(mWay)) {
			mWay = "天";
		} else if ("2".equals(mWay)) {
			mWay = "一";
		} else if ("3".equals(mWay)) {
			mWay = "二";
		} else if ("4".equals(mWay)) {
			mWay = "三";
		} else if ("5".equals(mWay)) {
			mWay = "四";
		} else if ("6".equals(mWay)) {
			mWay = "五";
		} else if ("7".equals(mWay)) {
			mWay = "六";
		}
		return mYear + "年" + mMonth + "月" + mDay + "日" + "/星期" + mWay;
	}

	public static int getYear() {
		c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

		int mYear = c.get(Calendar.YEAR); // 获取当前年份

		return mYear;
	}

	public static int getMonth() {
		c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

		int mMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份

		return mMonth;
	}

	// 获取当前日期号码
	public static int getDay() {
		c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

		int mDay = c.get(Calendar.DAY_OF_MONTH);// 获取当前月份的日期号码

		return mDay;
	}
	
	public static int getDay(long milliseconds) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliseconds);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}
		
	public static int getYear(long milliseconds) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliseconds);
		return calendar.get(Calendar.YEAR);
	}
	public static int getMonth(long milliseconds) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliseconds);
		return calendar.get(Calendar.MONTH) + 1;
	}

	public static String getWeek(String pTime) {

		String Week = "周";

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		try {

			c.setTime(format.parse(pTime));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			Week += "日";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 2) {
			Week += "一";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 3) {
			Week += "二";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 4) {
			Week += "三";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 5) {
			Week += "四";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 6) {
			Week += "五";
		}
		if (c.get(Calendar.DAY_OF_WEEK) == 7) {
			Week += "六";
		}

		return Week;
	}

	public static String getWay(int year, int monthOfYear, int dayOfMonth) {
		// TODO Auto-generated method stub
		/*
		 * 这里通过蔡勒公式算出某一天是星期几
		 */
		int y = year - 1;
		int m = monthOfYear;
		int c = 20;
		int d = dayOfMonth + 12;
		int w = (y + (y / 4) + (c / 4) - 2 * c + (26 * (m + 1) / 10) + d - 1) % 7;
		String myWeek = null;
		switch (w) {
		case 0:
			myWeek = "日";
			break;
		case 1:
			myWeek = "一";
			break;
		case 2:
			myWeek = "二";
			break;
		case 3:
			myWeek = "三";
			break;
		case 4:
			myWeek = "四";
			break;
		case 5:
			myWeek = "五";
			break;
		case 6:
			myWeek = "六";
			break;
		default:
			break;
		}
		return "周" + myWeek;
	}

	// 得到屏幕宽度
	public static int getPhoneWidth(Activity context) {
		DisplayMetrics dm = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenWidth = dm.widthPixels;
		return screenWidth;
	}

}
