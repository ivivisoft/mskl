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
import android.graphics.Color;
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
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Initial the camera
 * 
 * @author Ryan.Tang
 */
public class AddPlanDetailActivity extends Activity implements OnClickListener {

	private final static int SCANNIN_GREQUEST_CODE = 1;
	private Button charge_minus, already_minus, everyday_minus,
			everytime_minus, charge_add, already_add, everyday_add,
			everytime_add;

	private EditText et_charge, et_already, et_everyday, et_everytime;

	private int chargeNum, alreadyNum, everydayNum, everytimeNum;

	private RadioButton  li, pian, dai, zhi;
	private TextView noon, tv_unit,tv_drug,tv_middle,tv_right, cancle, ok;
	private CustomLayoutDialog dialog;
	private ImageView iv_left_img;
	private ArrayList<User> plans;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_plan_detail);
		initView();
	}

	private void initView() {
		tv_middle = (TextView) findViewById(R.id.tv_middle);
		tv_right = (TextView) findViewById(R.id.tv_right);
		iv_left_img = (ImageView) findViewById(R.id.iv_left_img);
		charge_minus = (Button) findViewById(R.id.btn_charge_minus);
		already_minus = (Button) findViewById(R.id.btn_already_minus);
		everyday_minus = (Button) findViewById(R.id.btn_everyday_minus);
		everytime_minus = (Button) findViewById(R.id.btn_everytime_minus);

		charge_add = (Button) findViewById(R.id.btn_charge_add);
		already_add = (Button) findViewById(R.id.btn_already_add);
		everyday_add = (Button) findViewById(R.id.btn_everyday_add);
		everytime_add = (Button) findViewById(R.id.btn_everytime_add);

		et_charge = (EditText) findViewById(R.id.et_charge);
		et_already = (EditText) findViewById(R.id.et_already);
		et_everyday = (EditText) findViewById(R.id.et_everyday);
		et_everytime = (EditText) findViewById(R.id.et_everytime);

		RelativeLayout noonTime = (RelativeLayout) findViewById(R.id.rl_noontime);
		LinearLayout ll_unit = (LinearLayout) findViewById(R.id.ll_unit);
		LinearLayout ll_name = (LinearLayout) findViewById(R.id.ll_name);
		noon = (TextView) findViewById(R.id.tv_noontime);
		tv_unit = (TextView) findViewById(R.id.tv_unit);
		tv_drug = (TextView) findViewById(R.id.tv_drug);
		
		tv_right.setVisibility(View.VISIBLE);
		tv_middle.setText("添加服药计划");
		tv_right.setText("添加");

		charge_minus.setOnClickListener(this);
		already_minus.setOnClickListener(this);
		everyday_minus.setOnClickListener(this);
		everytime_minus.setOnClickListener(this);
		charge_add.setOnClickListener(this);
		already_add.setOnClickListener(this);
		everyday_add.setOnClickListener(this);
		everytime_add.setOnClickListener(this);

		noonTime.setOnClickListener(this);
		ll_unit.setOnClickListener(this);
		ll_name.setOnClickListener(this);
		tv_right.setOnClickListener(this);

	}

	@SuppressLint("NewApi")
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_left_img:

			finish();
			break;
		case R.id.tv_right:

			Intent intent=new Intent();
			intent.putExtra("name", tv_drug.getText().toString());
			intent.putExtra("count", et_everytime.getText().toString());
			intent.putExtra("time", tv_unit.getText().toString());
			
			
			
			finish();
			break;
		case R.id.btn_charge_minus:
			if (!et_charge.getText().toString().isEmpty()) {
				chargeNum = Integer.valueOf(et_charge.getText().toString());
				chargeNum--;

				et_charge.setText(chargeNum + "");
			}

			break;

		case R.id.btn_already_minus:
			if (!et_already.getText().toString().isEmpty()) {
				alreadyNum = Integer.valueOf(et_already.getText().toString());
				alreadyNum--;

				et_already.setText(alreadyNum + "");
			}
			break;
		case R.id.btn_everyday_minus:
			if (!et_everyday.getText().toString().isEmpty()) {
				everydayNum = Integer.valueOf(et_everyday.getText().toString());
				everydayNum--;

				et_everyday.setText(everydayNum + "");
			}
			break;
		case R.id.btn_everytime_minus:
			if (!et_everytime.getText().toString().isEmpty()) {
				everytimeNum = Integer.valueOf(et_everytime.getText()
						.toString());
				everytimeNum--;

				et_everytime.setText(everytimeNum + "");
			}
			break;
		case R.id.btn_charge_add:
			if (!et_charge.getText().toString().isEmpty()) {
				chargeNum = Integer.valueOf(et_charge.getText().toString());
				chargeNum++;
				et_charge.setText(chargeNum + "");
			}
			break;
		case R.id.btn_already_add:
			if (!et_already.getText().toString().isEmpty()) {
				alreadyNum = Integer.valueOf(et_already.getText().toString());
				alreadyNum++;
				et_already.setText(alreadyNum + "");
			}
			break;
		case R.id.btn_everyday_add:
			if (!et_everyday.getText().toString().isEmpty()) {
				everydayNum = Integer.valueOf(et_everyday.getText().toString());
				everydayNum++;
				et_everyday.setText(everydayNum + "");
			}
			break;
		case R.id.btn_everytime_add:
			if (!et_everytime.getText().toString().isEmpty()) {
				everytimeNum = Integer.valueOf(et_everytime.getText()
						.toString());
				everytimeNum++;
				et_everytime.setText(everytimeNum + "");
			}

			break;
		case R.id.ll_unit:

			showDialog();
			break;
		case R.id.ll_name:

			Intent searchIntent=new Intent(this, SearchNameActivity.class);
			startActivityForResult(searchIntent, SCANNIN_GREQUEST_CODE);
			break;
		case R.id.rl_noontime:

			TimePicker picker = new TimePicker(AddPlanDetailActivity.this,
					TimePicker.HOUR_OF_DAY);
			picker.setCancelTextColor(getResources().getColor(
					R.color.color_orange));
			picker.setSubmitTextColor(getResources().getColor(
					R.color.color_orange));
			picker.setTitleText("请选择服药提醒时间");
			picker.setTopLineColor(Color.GRAY);
			picker.setOnTimePickListener(new TimePicker.OnTimePickListener() {
				@Override
				public void onTimePicked(String hour, String minute) {
					if (Integer.parseInt(hour) <= 12) {
						noon.setText("上午" + hour + ":" + minute);
					} else {
						noon.setText("下午" + hour + ":" + minute);
					}
				}
			});
			picker.show();
			break;
		case R.id.tv_cancle:

			dialog.dismiss();
			break;
		case R.id.tv_ok:

			if(li.isChecked()){
				tv_unit.setText(li.getText());
				
			}else if(pian.isChecked()){
				tv_unit.setText(pian.getText());
				
			}
			else if(dai.isChecked()){
				tv_unit.setText(dai.getText());
				
			}else if(zhi.isChecked()){
				tv_unit.setText(zhi.getText());
				
			}
			dialog.dismiss();
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		 switch (requestCode) {
			case SCANNIN_GREQUEST_CODE:
				if(resultCode == RESULT_OK){
					
					tv_drug.setText(data.getStringExtra("result"));
				}
				break;
			}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	
	public void showDialog() {
		dialog = new CustomLayoutDialog(this,
				R.style.Dialog_withdraw, R.layout.unit_popuwindow);
		Window window = dialog.getWindow();
		window.setGravity(Gravity.BOTTOM); // 此处可以设置dialog显示的位置

		dialog.show();
		WindowManager windowManager = getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
		lp.width = (int) (display.getWidth()); // 设置宽度
		dialog.getWindow().setAttributes(lp);

		cancle = (TextView) dialog.findViewById(R.id.tv_cancle);
		ok = (TextView) dialog.findViewById(R.id.tv_ok);
		li = (RadioButton) dialog.findViewById(R.id.rb_li);
		pian = (RadioButton) dialog.findViewById(R.id.rb_pian);
		dai = (RadioButton) dialog.findViewById(R.id.rb_dai);
		zhi = (RadioButton) dialog.findViewById(R.id.rb_zhi);

		cancle.setOnClickListener(this);
		ok.setOnClickListener(this);
	
		
		
	}

	
	
}