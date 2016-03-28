package com.meishengkangle.mskl.activity;

import java.util.ArrayList;
import java.util.Calendar;

import com.meishengkangle.mskl.R;
import com.meishengkangle.mskl.domain.User;
import com.meishengkangle.mskl.utils.UIUtils;
import com.meishengkangle.mskl.view.CustomLayoutDialog;
import com.meishengkangle.mskl.view.OptionPicker;
import com.meishengkangle.mskl.view.TimePicker;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Initial the camera
 * 服药详情
 * @author Ryan.Tang
 */
public class DoseDetailActivity extends Activity  {

	

	private TextView drugName,settingTime,finishTime,state,mood,moodSay,tv_middle;
	private ImageView iv_left_img;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dose_detail);
		initView();
		
	}

	
	private void initView() {
	
		drugName = (TextView) findViewById(R.id.tv_drug_name);
		settingTime=(TextView) findViewById(R.id.tv_setting_time);
		finishTime=(TextView) findViewById(R.id.tv_finish_time);
		state=(TextView) findViewById(R.id.tv_state);
		mood=(TextView) findViewById(R.id.tv_mood);
		moodSay=(TextView) findViewById(R.id.tv_mood_say);
		tv_middle=(TextView) findViewById(R.id.tv_middle);
		iv_left_img = (ImageView) findViewById(R.id.iv_left_img);
		
		tv_middle.setText("服药详情");
		
		Intent intent=getIntent();
		String name=intent.getStringExtra("drugname");
		String time=intent.getStringExtra("time");
		
		drugName.setText(name);
		settingTime.setText(time);
		
		
		iv_left_img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DoseDetailActivity.this.finish();
			}
		});

	}

	

}