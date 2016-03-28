package com.meishengkangle.mskl.activity;

import com.meishengkangle.mskl.GuideActivity;
import com.meishengkangle.mskl.R;
import com.meishengkangle.mskl.net.interfa.LoginProtocol;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyAccountActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_my_account);
		initView();

	}

	public void initView() {
		
		ImageView iv_left_img = (ImageView) findViewById(R.id.iv_left_img);
		TextView tv_middle = (TextView) findViewById(R.id.tv_middle);
		TextView tv_right = (TextView) findViewById(R.id.tv_right);
		LinearLayout ll_real_name = (LinearLayout) findViewById(R.id.ll_real_name);
		LinearLayout ll_withdraw = (LinearLayout) findViewById(R.id.ll_withdraw);
		LinearLayout ll_bankcard = (LinearLayout) findViewById(R.id.ll_bankcard);
		LinearLayout ll_password = (LinearLayout) findViewById(R.id.ll_password);

		tv_middle.setVisibility(View.VISIBLE);
		tv_right.setVisibility(View.VISIBLE);
		tv_middle.setText("我的账户");
		tv_right.setText("提现记录");
		tv_right.setTextColor(Color.WHITE);
		
		tv_middle.setTextColor(getResources().getColor(R.color.color_white));
	

		
		
		iv_left_img.setOnClickListener(this);
		ll_real_name.setOnClickListener(this);
		ll_withdraw.setOnClickListener(this);
		ll_bankcard.setOnClickListener(this);
		tv_right.setOnClickListener(this);
		ll_password.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		
		
		switch (v.getId()) {
		case R.id.ll_real_name:
			Intent intent=new Intent(getApplicationContext(), AuthenticationActivity.class);
			this.startActivity(intent);
			break;

		case R.id.ll_withdraw:
			Intent wIntent=new Intent(getApplicationContext(), WithdrawActivity.class);
			this.startActivity(wIntent);
			break;
			
		case R.id.ll_password:
			Intent setPwIntent=new Intent(getApplicationContext(), SettingWithdrawPwActivity.class);
			this.startActivity(setPwIntent);
			break;
		case R.id.ll_bankcard:
			Intent bIntent=new Intent(getApplicationContext(), BankCardActivity.class);
			this.startActivity(bIntent);
			break;
		case R.id.iv_left_img:
			this.finish();
			break;
		case R.id.tv_right:
			Intent wRecordIntent=new Intent(getApplicationContext(), WithdrawRecordActivity.class);
			this.startActivity(wRecordIntent);
			break;

		}
	}
}
