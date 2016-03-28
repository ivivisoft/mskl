package com.meishengkangle.mskl;

import com.meishengkangle.mskl.fragment.ContentFragment;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends FragmentActivity {

	private static final String TAG_CONTENT = "TAG_CONTENT";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	
		
		initFragment();
	}

	
	
	/**
	 * 初始化fragment
	 */
	private void initFragment() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();// 开始事务
		
		transaction.replace(R.id.fl_main, new ContentFragment(), TAG_CONTENT);
		transaction.commit();// 提交事务
		// Fragment fragment =
		// fm.findFragmentByTag(TAG_LEFT_MENU);//根据标记找到对应的fragment
	}

	
	// 获取主页fragment对象
		public ContentFragment getContentFragment() {
			FragmentManager fm = getSupportFragmentManager();
			ContentFragment fragment = (ContentFragment) fm
					.findFragmentByTag(TAG_CONTENT);// 根据标记找到对应的fragment
			return fragment;
		}
	
}
