package com.meishengkangle.mskl;

import com.meishengkangle.mskl.utils.SharedPrefUtils;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// 如果是第一次进入, 跳新手引导
		// 否则跳主页面
		boolean isFirstEnter = SharedPrefUtils.getBoolean(
				this, "is_first_enter", true);

		Intent intent;
		if (isFirstEnter) {
			// 新手引导
			intent = new Intent(getApplicationContext(),
					GuideActivity.class);
		} else {
			// 主页面
			intent = new Intent(getApplicationContext(),
					StartActivity.class);
		}

		startActivity(intent);

		finish();// 结束当前页面
	}
}
