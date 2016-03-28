package com.meishengkangle.mskl.fragment;
import java.util.ArrayList;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.meishengkangle.mskl.MainActivity;
import com.meishengkangle.mskl.R;
import com.meishengkangle.mskl.base.BasePager;
import com.meishengkangle.mskl.base.impl.HomePager;
import com.meishengkangle.mskl.base.impl.ReportPager;
import com.meishengkangle.mskl.base.impl.MinePager;
import com.meishengkangle.mskl.view.NoScrollViewPager;


/**
 * 主页面fragment
 * 
 * @author Kevin
 * @date 2015-10-18
 */
public class ContentFragment extends BaseFragment {

	private NoScrollViewPager mViewPager;
	private RadioGroup rgGroup;

	private ArrayList<BasePager> mPagers;// 三个标签页的集合

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.fragment_content, null);
		mViewPager = (NoScrollViewPager) view.findViewById(R.id.vp_content);
		rgGroup = (RadioGroup) view.findViewById(R.id.rg_group);
		return view;
	}

	@Override
	public void initData() {
		mPagers = new ArrayList<BasePager>();

		
		// 添加三个个标签页
		mPagers.add(new HomePager(mActivity));
		mPagers.add(new ReportPager(mActivity));
		
		mPagers.add(new MinePager(mActivity));

		mViewPager.setAdapter(new ContentAdapter());

		// 底栏标签切换监听
		rgGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rb_home:
					// 首页
					// mViewPager.setCurrentItem(0);
					mViewPager.setCurrentItem(0, false);// 参2:表示是否具有滑动动画
					break;
				case R.id.rb_news:
					// 报告
					mViewPager.setCurrentItem(1, false);
					break;
				case R.id.rb_setting:
					// 设置
					mViewPager.setCurrentItem(2, false);
					break;

				default:
					break;
				}
			}
		});

		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				BasePager pager = mPagers.get(position);
				pager.initData();

			
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {

			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}
		});

		// 手动加载第一页数据
		mPagers.get(0).initData();
		
	}

	

	class ContentAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return mPagers.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			BasePager pager = mPagers.get(position);
			View view = pager.mRootView;// 获取当前页面对象的布局

			// pager.initData();// 初始化数据, viewpager会默认加载下一个页面,
			// 为了节省流量和性能,不要在此处调用初始化数据的方法

			container.addView(view);

			return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

	}

	// 获取新闻中心页面
	public ReportPager getNewsCenterPager() {
		ReportPager pager = (ReportPager) mPagers.get(1);
		return pager;
	}

}
