package com.meishengkangle.mskl.adapter;

import java.util.ArrayList;

import com.meishengkangle.mskl.R;
import com.meishengkangle.mskl.utils.UIUtils;

import android.content.Context;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

// 定义继承BaseAdapter的匹配器
public class HomeAdapter extends BaseAdapter {

	// 上下文对象
	private Context mContext;
	private ArrayList<String> days;
	private int selectItem;
	private int smallY;
	private int bigY;

	private ArrayList<String> weeks;

	// 构造方法
	public HomeAdapter(Context c, int screenWidth, ArrayList<String> days,
			ArrayList<String> weeks) {
		mContext = c;
		this.days = days;
		this.weeks = weeks;
		this.smallY = screenWidth / 11;
		this.bigY = UIUtils.dip2px(mContext, 47);
	}

	public void setSelectItem(int selectItem) {
		if (this.selectItem != selectItem) {
			this.selectItem = selectItem;
			notifyDataSetChanged();
		}
	}

	// 返回项目数量
	@Override
	public int getCount() {
		return days.size();
	}

	// 返回项目
	@Override
	public Object getItem(int position) {
		return position;
	}

	// 返回项目Id
	@Override
	public long getItemId(int position) {
		return position;
	}

	

	// 返回视图
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = View.inflate(mContext, R.layout.horizontal_list_item,
					null);
			holder.day = (TextView) convertView.findViewById(R.id.tv_item);
			holder.week = (TextView) convertView.findViewById(R.id.tv_week);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if (selectItem == position) {

			 
			  holder.day.setBackgroundResource(R.drawable.check_day1);
			  ScaleAnimation animation=new ScaleAnimation(0.8f, 1.0f, 0.8f, 1.0f, Animation.RELATIVE_TO_SELF
					  ,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
				animation.setDuration(1);
				animation.setFillAfter(true);
				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
						bigY, bigY);
				params.setMargins(0, UIUtils.dip2px(mContext, 5), 0, 0);
				holder.day.setLayoutParams(params);
				holder.day.startAnimation(animation);
				
			} else {
				holder.day.setBackgroundResource(R.drawable.bg_past_day);
				LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
						smallY, smallY);
				params.setMargins(0, UIUtils.dip2px(mContext, 10), 0, 0);
				holder.day.setLayoutParams(params);
			}

		holder.day.setText(days.get(position));
		holder.week.setText(weeks.get(position));

		return convertView;
	}

	private static class ViewHolder {
		private TextView day;
		private TextView week;

	}

	
}
