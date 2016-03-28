package com.meishengkangle.mskl.activity;

import com.meishengkangle.mskl.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class AddGuardianActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_add_guardian);
		initView();

	}

	public void initView() {
		TextView tv_middle = (TextView) findViewById(R.id.tv_middle);
		ImageView iv_left_img = (ImageView) findViewById(R.id.iv_left_img);
		
		tv_middle.setVisibility(View.VISIBLE);
		tv_middle.setText("添加监督人");
		tv_middle.setTextColor(getResources().getColor(R.color.color_white));

		iv_left_img.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		

		case R.id.iv_left_img:

			this.finish();
			break;

		}
	}
}
