package com.meishengkangle.mskl.base.impl;

import java.util.ArrayList;
import java.util.List;

import com.meishengkangle.mskl.R;
import com.meishengkangle.mskl.base.BasePager;
import com.meishengkangle.mskl.view.MyCalendar;
import com.meishengkangle.mskl.view.MyCalendar.OnCalendarClickListener;
import com.meishengkangle.mskl.view.MyCalendar.OnCalendarDateChangedListener;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 报告
 * 
 * @author Kevin
 * @date 2015-10-18
 */
public class ReportPager extends BasePager {

	String date = null;// 设置默认选中的日期 格式为 “2014-04-05” 标准DATE格式

	public ReportPager(Activity activity) {
		super(activity);
	}

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.activity_report, null);
		
		RelativeLayout rl_title=(RelativeLayout) view.findViewById(R.id.rl_title);
		TextView tv_middle=(TextView) view.findViewById(R.id.tv_middle);
		rl_title.setBackgroundColor(mActivity.getResources().getColor(R.color.color_white));
		tv_middle.setText("服药报告");
		tv_middle.setTextColor(mActivity.getResources().getColor(R.color.color_orange));
		
		view.startAnimation(AnimationUtils.loadAnimation(mActivity,
				R.anim.fade_in));
		RelativeLayout ll_popup = (RelativeLayout) view.findViewById(R.id.ll_popup);
		ll_popup.startAnimation(AnimationUtils.loadAnimation(mActivity,
				R.anim.push_bottom_in_1));

		final TextView popupwindow_calendar_month = (TextView) view
				.findViewById(R.id.popupwindow_calendar_month);
		final MyCalendar calendar = (MyCalendar) view
				.findViewById(R.id.popupwindow_calendar);

		popupwindow_calendar_month.setText(calendar.getCalendarYear() + "年"
				+ calendar.getCalendarMonth() + "月");

		if (null != date) {

			int years = Integer.parseInt(date.substring(0, date.indexOf("-")));
			int month = Integer.parseInt(date.substring(date.indexOf("-") + 1,
					date.lastIndexOf("-")));
			popupwindow_calendar_month.setText(years + "年" + month + "月");

			calendar.showCalendar(years, month);
			calendar.setCalendarDayBgColor(date, R.drawable.state_today);
		}

		List<String> list = new ArrayList<String>(); // 设置标记列表
		list.add("2014-04-01");
		list.add("2014-04-02");
		calendar.addMarks(list, 0);

		// 监听所选中的日期
		calendar.setOnCalendarClickListener(new OnCalendarClickListener() {

			public void onCalendarClick(int row, int col, String dateFormat) {
				int month = Integer.parseInt(dateFormat.substring(
						dateFormat.indexOf("-") + 1,
						dateFormat.lastIndexOf("-")));

				if (calendar.getCalendarMonth() - month == 1// 跨年跳转
						|| calendar.getCalendarMonth() - month == -11) {
					calendar.lastMonth();

				} else if (month - calendar.getCalendarMonth() == 1 // 跨年跳转
						|| month - calendar.getCalendarMonth() == -11) {
					calendar.nextMonth();

				} else {
					calendar.removeAllBgColor();
					calendar.setCalendarDayBgColor(dateFormat,
							R.drawable.state_today);
		
					date = dateFormat;// 最后返回给全局 date
				}
			}
		});

		// 监听当前月份
		calendar.setOnCalendarDateChangedListener(new OnCalendarDateChangedListener() {
			public void onCalendarDateChanged(int year, int month) {
				popupwindow_calendar_month.setText(year + "年" + month + "月");
			}
		});

		// 上月监听按钮
		RelativeLayout popupwindow_calendar_last_month = (RelativeLayout) view
				.findViewById(R.id.popupwindow_calendar_last_month);
		popupwindow_calendar_last_month
				.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						calendar.lastMonth();
					}

				});

		// 下月监听按钮
		RelativeLayout popupwindow_calendar_next_month = (RelativeLayout) view
				.findViewById(R.id.popupwindow_calendar_next_month);
		popupwindow_calendar_next_month
				.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						calendar.nextMonth();
					}
				});

		return view;
	}

	@Override
	public void initData() {
		System.out.println("新闻中心初始化啦...");

		// // 要给帧布局填充布局对象
		// TextView view = new TextView(mActivity);
		// view.setText("报告");
		// view.setTextColor(Color.RED);
		// view.setTextSize(22);
		// view.setGravity(Gravity.CENTER);
		//
		// flContent.addView(view);

	}

}
