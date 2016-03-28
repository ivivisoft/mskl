package com.meishengkangle.mskl.activity;

import com.meishengkangle.mskl.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SettingWithdrawPwActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_setting_withdraw_pw);
		initView();

	}

	public void initView() {
		
		ImageView iv_left_img = (ImageView) findViewById(R.id.iv_left_img);
		TextView tv_middle = (TextView) findViewById(R.id.tv_middle);
		Button btn_submit = (Button) findViewById(R.id.btn_submit);

		tv_middle.setVisibility(View.VISIBLE);
		tv_middle.setText("提现密码");
		
		tv_middle.setTextColor(getResources().getColor(R.color.color_white));
	

		
		
		iv_left_img.setOnClickListener(this);
		btn_submit.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		Intent intent=new Intent(getApplicationContext(), AddGuardianActivity.class);
		
		switch (v.getId()) {
		case R.id.btn_submit:
			
			this.startActivity(intent);
			break;

		case R.id.iv_left_img:
			this.finish();
			break;

		}
	}
}