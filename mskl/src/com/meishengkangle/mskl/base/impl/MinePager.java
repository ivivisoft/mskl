package com.meishengkangle.mskl.base.impl;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.OnekeyShareTheme;

import com.meishengkangle.mskl.GuideActivity;
import com.meishengkangle.mskl.R;
import com.meishengkangle.mskl.activity.AddPlanActivity;
import com.meishengkangle.mskl.activity.GuardianActivity;
import com.meishengkangle.mskl.activity.MoodRecordActivity;
import com.meishengkangle.mskl.activity.MyAccountActivity;
import com.meishengkangle.mskl.activity.SettingActivity;
import com.meishengkangle.mskl.base.BasePager;
import com.meishengkangle.mskl.utils.UIUtils;

/**
 * 设置
 * 
 * @author Kevin
 * @date 2015-10-18
 */
public class MinePager extends BasePager implements OnClickListener {

	public MinePager(Activity activity) {
		super(activity);
	}

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.activity_mine, null);
		LinearLayout mAccount = (LinearLayout) view
				.findViewById(R.id.ll_myaccount);
		LinearLayout mGuardian = (LinearLayout) view
				.findViewById(R.id.ll_guardian);
		LinearLayout mMood = (LinearLayout) view.findViewById(R.id.ll_mood);
		LinearLayout mPlan = (LinearLayout) view.findViewById(R.id.ll_plan);
		LinearLayout mSetting = (LinearLayout) view
				.findViewById(R.id.ll_setting);
		LinearLayout mShare = (LinearLayout) view.findViewById(R.id.ll_share);
		LinearLayout mHelp = (LinearLayout) view.findViewById(R.id.ll_help);

		mAccount.setOnClickListener(this);
		mGuardian.setOnClickListener(this);
		mMood.setOnClickListener(this);
		mPlan.setOnClickListener(this);
		mShare.setOnClickListener(this);
		mSetting.setOnClickListener(this);
		mHelp.setOnClickListener(this);

		return view;
	}

	@Override
	public void initData() {

	}

	@Override
	public void onClick(View v) {
		Intent intent=null;
		switch (v.getId()) {
		case R.id.ll_myaccount:

			intent=new Intent(mActivity,MyAccountActivity.class);
			mActivity.startActivity(intent);
			break;

		case R.id.ll_guardian:
		    intent=new Intent(mActivity, GuardianActivity.class);
			mActivity.startActivity(intent);
			
			break;
		case R.id.ll_mood:
			intent=new Intent(mActivity, MoodRecordActivity.class);
			mActivity.startActivity(intent);
			

			break;
		case R.id.ll_plan:
			intent=new Intent(mActivity, AddPlanActivity.class);
			mActivity.startActivity(intent);
			break;
		case R.id.ll_share:
			showShare();
			break;
		case R.id.ll_setting:
			intent=new Intent(mActivity, SettingActivity.class);
			mActivity.startActivity(intent);
			break;
		case R.id.ll_help:

			break;
		}
	}
	
	private void showShare(){

		   
		   ShareSDK.initSDK(mActivity);
		   OnekeyShare oks = new OnekeyShare();
		   
		   //关闭sso授权
		   oks.disableSSOWhenAuthorize(); 
		   
		  // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
		   //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
		   // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		   oks.setTitle(mActivity.getString(R.string.app_name));
		   // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		   oks.setTitleUrl("http://sharesdk.cn");
		   // text是分享文本，所有平台都需要这个字段
		   oks.setText("我是分享文本");
		   // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
		   oks.setImagePath("/sdcard/share_logo.jpg");//确保SDcard下面存在此张图片
		   // url仅在微信（包括好友和朋友圈）中使用
		   oks.setUrl("http://sharesdk.cn");
		   // comment是我对这条分享的评论，仅在人人网和QQ空间使用
		   oks.setComment("我是测试评论文本");
		   // site是分享此内容的网站名称，仅在QQ空间使用
		   oks.setSite(mActivity.getString(R.string.app_name));
		   // siteUrl是分享此内容的网站地址，仅在QQ空间使用
		   oks.setSiteUrl("http://sharesdk.cn");
		   
		  // 启动分享GUI
		   oks.show(mActivity);
		}

}
