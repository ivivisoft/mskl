package com.meishengkangle.mskl.base.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

import com.meishengkangle.mskl.R;
import com.meishengkangle.mskl.activity.AddPlanActivity;
import com.meishengkangle.mskl.activity.AddPlanDetailActivity;
import com.meishengkangle.mskl.activity.DoseDetailActivity;
import com.meishengkangle.mskl.activity.RemindActivity;
import com.meishengkangle.mskl.adapter.HomeAdapter;
import com.meishengkangle.mskl.adapter.HomePagerAdapter;
import com.meishengkangle.mskl.adapter.PlanAdapter;
import com.meishengkangle.mskl.base.BasePager;
import com.meishengkangle.mskl.domain.User;
import com.meishengkangle.mskl.utils.UIUtils;
import com.meishengkangle.mskl.view.TwoWayView;

/**
 * 首页
 * 
 * @author Kevin
 * @date 2015-10-18
 */
public class HomePager extends BasePager {

	TwoWayView hListView;
	ArrayList<String> days;
	ArrayList<String> weeks;
	private HomeAdapter homeAdapter;
	private RelativeLayout rl_title;
	private TextView tv_middle, tv_add;
	private ImageView iv_left_img, iv_right_img, iv_guide;
	private ViewPager pager;
	private LinearLayout llContainer;
	private ImageView ivRedPoint;

	// 小红点移动距离
	private int mPointDis;
	private ArrayList<ImageView> mImageViewList;
	private int[] mImageIds;
	private Gallery gallery;
	private LinearLayout noPlan;

	List<Map<String, String>> moreList;
	private PopupWindow pwMyPopWindow;// popupwindow
	private ListView mPlanListView;//
	private int NUM_OF_VISIBLE_LIST_ROWS = 3;// 指定popupwindow中Item的数量

	private static int REQUEST_CODE = 1;

	private ArrayList<User> plans;

	public HomePager(Activity activity) {
		super(activity);
	}

	@SuppressLint("NewApi")
	@Override
	public View initView() {

		View view = View.inflate(mActivity, R.layout.activity_home, null);

		// 定义UI组件
		// final TextView tv_check = (TextView)view.findViewById(R.id.tv_check);
		gallery = (Gallery) view.findViewById(R.id.Gallery01);

		rl_title = (RelativeLayout) view.findViewById(R.id.rl_title);
		tv_middle = (TextView) view.findViewById(R.id.tv_middle);
		iv_left_img = (ImageView) view.findViewById(R.id.iv_left_img);
		iv_right_img = (ImageView) view.findViewById(R.id.iv_right_img);
		pager = (ViewPager) view.findViewById(R.id.vp_home);
		llContainer = (LinearLayout) view.findViewById(R.id.ll_container);
		ivRedPoint = (ImageView) view.findViewById(R.id.iv_red_point);
		iv_guide = (ImageView) view.findViewById(R.id.iv_guide);
		tv_add = (TextView) view.findViewById(R.id.tv_add);
		noPlan = (LinearLayout) view.findViewById(R.id.ll_noplan);
		mPlanListView = (ListView) view.findViewById(R.id.lv_plan);

		rl_title.setBackground(null);
		iv_left_img.setImageResource(R.drawable.home_pill);
		iv_right_img.setVisibility(View.VISIBLE);

//		 tv_add.setOnClickListener(new OnClickListener() {
//		
//		 @Override
//		 public void onClick(View v) {
//		 Intent intent=new Intent(mActivity, AddPlanActivity.class);
//		 mActivity.startActivity(intent);
//		 }
//		 });
		initData();
		initPagerDate();
		initPopupWindow();
		// 设置图片匹配器
		int screenWidth = UIUtils.getPhoneWidth(mActivity);
		homeAdapter = new HomeAdapter(mActivity, screenWidth, days, weeks);
		gallery.setAdapter(homeAdapter);
		// int space = (int) (screenWidth / 23);
		// gallery.setSpacing(space);

		gallery.setSelection(days.size() / 2);

		gallery.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				iv_guide.setVisibility(View.INVISIBLE);

			}
		});
		// 设置AdapterView点击监听器，Gallery是AdapterView的子类
		gallery.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (position > 2 && position < 33) {
					homeAdapter.setSelectItem(position);
				} else if (position < 3) {
					homeAdapter.setSelectItem(3);
				} else if (position > 32) {
					homeAdapter.setSelectItem(32);
				}

			}
		});
		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				if (position > 2 && position < 33) {
					homeAdapter.setSelectItem(position);
					gallery.setSelection(position);
				} else if (position < 3) {
					homeAdapter.setSelectItem(3);
					gallery.setSelection(3);
				} else if (position > 32) {
					homeAdapter.setSelectItem(32);
					gallery.setSelection(32);
				}
				// AlphaAnimation animation=new AlphaAnimation(0.0f, 1.0f);
				// animation.setFillAfter(true);
				// animation.setDuration(2000);
				// iv_guide.startAnimation(animation);

				noPlan.setVisibility(View.GONE);
				mPlanListView.setVisibility(View.VISIBLE);

				mPlanListView.setAdapter(new PlanAdapter(mActivity, plans));

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
		
		
		
		mPlanListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				
				//根据状态来判断跳转到那个页面
				
//				Intent detailIntent=new Intent(mActivity, DoseDetailActivity.class);
//				detailIntent.putExtra("drugname", plans.get(position).getName());
//				detailIntent.putExtra("time", plans.get(position).getTime());
//				mActivity.startActivity(detailIntent);
				
				Intent remindIntent=new Intent(mActivity, RemindActivity.class);
				remindIntent.putExtra("drugname", plans.get(position).getName());
				remindIntent.putExtra("time", plans.get(position).getTime());
				remindIntent.putExtra("count", plans.get(position).getCount());
				mActivity.startActivity(remindIntent);
			}
		});
		

		// 给viewpager填充数据

		pager.setAdapter(new HomePagerAdapter(mActivity, mImageViewList));

		pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {

			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				// 当页面滑动过程中的回调
				System.out.println("当前位置:" + position + ";移动偏移百分比:"
						+ positionOffset);
				// 更新小红点距离
				int leftMargin = (int) (mPointDis * positionOffset) + position
						* mPointDis;// 计算小红点当前的左边距
				RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivRedPoint
						.getLayoutParams();
				params.leftMargin = leftMargin;// 修改左边距

				// 重新设置布局参数
				ivRedPoint.setLayoutParams(params);

			}

			@Override
			public void onPageScrollStateChanged(int state) {
				// 页面状态发生变化的调用

			}
		});

		ivRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {

					@Override
					public void onGlobalLayout() {
						// 移除监听,避免重复回调
						ivRedPoint.getViewTreeObserver()
								.removeGlobalOnLayoutListener(this);
						// ivRedPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
						// layout方法执行结束的回调
						mPointDis = llContainer.getChildAt(1).getLeft()
								- llContainer.getChildAt(0).getLeft();
						System.out.println("圆点距离:" + mPointDis);
					}
				});

		iv_left_img.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (pwMyPopWindow.isShowing()) {

					pwMyPopWindow.dismiss();// 关闭
				} else {

					pwMyPopWindow.showAsDropDown(iv_left_img);// 显示
				}

			}

		});

		return view;
	}

	@Override
	public void initData() {

		weeks = new ArrayList<String>();
		days = new ArrayList<String>();
		int currDay;
		Calendar c = Calendar.getInstance(); // 当时的日期和时间

		c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		int currYear = UIUtils.getYear();
		int currMonth = UIUtils.getMonth();

		Toast.makeText(mActivity, "" + currYear + ":::" + currMonth,
				Toast.LENGTH_SHORT).show();
		int day = (int) System.currentTimeMillis();
		for (int i = 18; i > 0; i--) {

			currYear = UIUtils.getYear(System.currentTimeMillis() - i * 24
					* 3600 * 1000);
			currMonth = UIUtils.getMonth(System.currentTimeMillis() - i * 24
					* 3600 * 1000);
			day = UIUtils.getDay(System.currentTimeMillis() - i * 24 * 3600
					* 1000);
			weeks.add(UIUtils.getWeek(currYear + "-" + currMonth + "-" + day));
			days.add(String.valueOf(day));

		}

		for (int i = 0; i < 18; i++) {
			currYear = UIUtils.getYear(System.currentTimeMillis() + i * 24
					* 3600 * 1000);
			currMonth = UIUtils.getMonth(System.currentTimeMillis() + i * 24
					* 3600 * 1000);
			day = UIUtils.getDay(System.currentTimeMillis() + i * 24 * 3600
					* 1000);
			weeks.add(UIUtils.getWeek(currYear + "-" + currMonth + "-" + day));

			days.add(String.valueOf(day));

		}

		plans = new ArrayList<User>();
		User plan1 = new User();
		plan1.setName("云芝肝肽胶囊1");
		plan1.setCount("10粒");
		plan1.setTime("6:00");
		User plan2 = new User();
		plan2.setName("云芝肝肽胶囊2");
		plan2.setCount("2粒");
		plan2.setTime("12:00");
		User plan3 = new User();
		plan3.setName("云芝肝肽胶囊3");
		plan3.setCount("2粒");
		plan3.setTime("17:00");

		plans.add(plan1);
		plans.add(plan2);
		plans.add(plan3);

	}

	// 初始化数据
	private void initPagerDate() {
		mImageViewList = new ArrayList<ImageView>();

		mImageIds = new int[] { R.drawable.guide_1, R.drawable.guide_2,
				R.drawable.guide_3 };
		for (int i = 0; i < mImageIds.length; i++) {
			ImageView view = new ImageView(mActivity);
			view.setBackgroundResource(mImageIds[i]);
			mImageViewList.add(view);

			// 初始化小圆点
			ImageView point = new ImageView(mActivity);
			point.setImageResource(R.drawable.point_other);// 设置图片(shape形状)

			// 初始化布局参数, 宽高包裹内容,父控件是谁,就是谁声明的布局参数
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT);

			if (i > 0) {
				// 从第二个点开始设置左边距
				params.leftMargin = 16;
			}

			point.setLayoutParams(params);// 设置布局参数

			llContainer.addView(point);// 给容器添加圆点
		}
	}

	private void initPopupWindow() {

		LayoutInflater inflater = (LayoutInflater) mActivity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.popuwindow, null);
		TextView tv_add = (TextView) layout.findViewById(R.id.tv_add);
		TextView tv_manage = (TextView) layout.findViewById(R.id.tv_manage);

		pwMyPopWindow = new PopupWindow(layout, UIUtils.dip2px(mActivity, 150),
				LinearLayout.LayoutParams.WRAP_CONTENT);
		pwMyPopWindow.setContentView(layout);
		pwMyPopWindow.setFocusable(true);// 加上这个popupwindow中的ListView才可以接收点击事件

		// 控制popupwindow点击屏幕其他地方消失
		pwMyPopWindow.setBackgroundDrawable(mActivity.getResources()
				.getDrawable(R.drawable.bg_bomb_box));// 设置背景图片，不能在布局中设置，要通过代码来设置
		pwMyPopWindow.setOutsideTouchable(true);// 触摸popupwindow外部，popupwindow消失。这个要求你的popupwindow要有背景图片才可以成功，如上
	}

}
