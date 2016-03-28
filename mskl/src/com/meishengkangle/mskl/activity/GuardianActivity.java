package com.meishengkangle.mskl.activity;

import com.meishengkangle.mskl.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class GuardianActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_guardian);
		initView();

	}

	public void initView() {
		TextView titleRitgt = (TextView) findViewById(R.id.tv_right);
		ImageView iv_left_img = (ImageView) findViewById(R.id.iv_left_img);
		TextView tv_middle = (TextView) findViewById(R.id.tv_middle);
		TextView tv_add = (TextView) findViewById(R.id.tv_add);

		tv_middle.setVisibility(View.VISIBLE);
		titleRitgt.setVisibility(View.VISIBLE);
		tv_middle.setText("我的监督人");
		titleRitgt.setText("添加");
		tv_middle.setTextColor(getResources().getColor(R.color.color_white));
		titleRitgt.setTextColor(getResources().getColor(R.color.color_white));

		titleRitgt.setOnClickListener(this);
		tv_add.setOnClickListener(this);
		iv_left_img.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		Intent intent=new Intent(getApplicationContext(), AddGuardianActivity.class);
		
		switch (v.getId()) {
		case R.id.tv_right:
			
			this.startActivity(intent);
			break;

		case R.id.tv_add:
			this.startActivity(intent);
			break;
		case R.id.iv_left_img:
			this.finish();
			break;

		}
	}
}
