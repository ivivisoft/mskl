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
public class RemindActivity extends Activity implements OnClickListener {

	

	private TextView drugName,time,count,mood,moodSay,tv_middle;
	private ImageView iv_left_img;
	private Button mFinish,mRemind;
	private EditText et_edit;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_remind);
		initView();
		
	}

	
	private void initView() {
	
		drugName = (TextView) findViewById(R.id.drug_name);
		time=(TextView) findViewById(R.id.tv_time);
		count=(TextView) findViewById(R.id.tv_count);
		tv_middle=(TextView) findViewById(R.id.tv_middle);
		iv_left_img = (ImageView) findViewById(R.id.iv_left_img);
		mFinish = (Button) findViewById(R.id.btn_finish);
		mRemind = (Button) findViewById(R.id.btn_remind);
		et_edit = (EditText) findViewById(R.id.et_edit);
		
		et_edit.setSelection(et_edit.getText().length());
		
		tv_middle.setText("服药提醒");
		
		Intent intent=getIntent();
		String name=intent.getStringExtra("drugname");
		String getTime=intent.getStringExtra("time");
		String getCount=intent.getStringExtra("count");
		
		drugName.setText(name);
		time.setText(getTime);
		count.setText(getCount);
		
		
		iv_left_img.setOnClickListener(this);
		mFinish.setOnClickListener(this);
		mRemind.setOnClickListener(this);
		
	
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_left_img:
			RemindActivity.this.finish();
			break;
			
		case R.id.btn_finish:
			
			break;
			
		case R.id.btn_remind:
			
			TimePicker picker = new TimePicker(RemindActivity.this,
					TimePicker.HOUR_OF_DAY);
			picker.setTopLineVisible(false);
			picker.setCancelTextColor(getResources().getColor(
					R.color.color_orange));
			picker.setSubmitTextColor(getResources().getColor(
					R.color.color_orange));
			picker.setOnTimePickListener(new TimePicker.OnTimePickListener() {
				@Override
				public void onTimePicked(String hour, String minute) {
					
				}
			});
			picker.show();
			
			break;

		}
	}

	

}