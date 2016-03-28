package com.meishengkangle.mskl.adapter;

import java.util.ArrayList;

import com.meishengkangle.mskl.R;
import com.meishengkangle.mskl.utils.UIUtils;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

// 定义继承BaseAdapter的匹配器
public class HomePagerAdapter extends PagerAdapter {

	// 上下文对象
	private Context mContext;

	private ArrayList<ImageView> mImageViewList;

	// 构造方法
	public HomePagerAdapter(Context c, ArrayList<ImageView> mImageViewList) {
		mContext = c;
		this.mImageViewList = mImageViewList;
	}

	// item的个数
	@Override
	public int getCount() {
		return mImageViewList.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		ImageView view = mImageViewList.get(position);
		container.addView(view);
		return view;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}



}
