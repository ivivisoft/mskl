package com.meishengkangle.mskl.activity;

import java.util.ArrayList;
import java.util.Date;


import com.alibaba.fastjson.JSON;
import com.meishengkangle.mskl.R;
import com.meishengkangle.mskl.utils.AssetsUtils;
import com.meishengkangle.mskl.view.AddressPicker;
import com.meishengkangle.mskl.view.CustomLayoutDialog;

import android.app.Activity;
import android.app.Application;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 实名认证
 * 
 * @author Administrator
 * 
 */
public class AddBankCardActivity extends Activity implements OnClickListener {

	private ImageView iv_left_img;
	private TextView tv_middle,open_bank,tv_address;
	private ImageView iv_image;
	private Button mAddButton;
	private LinearLayout ll_bank,mAddress;
	private CustomLayoutDialog dialog;
	private RadioButton bank1,bank2,bank3,bank4,bank5,bank6;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_bankcard);

		iv_left_img = (ImageView) findViewById(R.id.iv_left_img);
		tv_middle = (TextView) findViewById(R.id.tv_middle);
		open_bank = (TextView) findViewById(R.id.open_bank);
		tv_address = (TextView) findViewById(R.id.tv_address);
		mAddButton = (Button) findViewById(R.id.btn_submit);
		mAddress = (LinearLayout) findViewById(R.id.ll_location);
		ll_bank = (LinearLayout) findViewById(R.id.ll_bank);

		tv_middle.setText("添加银行卡");

		iv_left_img.setOnClickListener(this);
		mAddButton.setOnClickListener(this);
		mAddress.setOnClickListener(this);
		ll_bank.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.iv_left_img: // 取消操作
			this.finish();
			break;
		case R.id.ll_bank: // 开户行
			showDialog();
			break;
		
		case R.id.ll_location: // 开户地址
			  ArrayList<AddressPicker.Province> data = new ArrayList<AddressPicker.Province>();
		        String json = AssetsUtils.readText(this, "city.json");
		        data.addAll(JSON.parseArray(json, AddressPicker.Province.class));
		        AddressPicker picker = new AddressPicker(this,data);
		        picker.setCancelTextColor(getResources().getColor(
						R.color.color_orange));
				picker.setSubmitTextColor(getResources().getColor(
						R.color.color_orange));
				picker.setTitleText("请选择开户地区");
				picker.setTopLineColor(Color.GRAY);
		        picker.setSelectedItem("北京", "北京", "朝阳区");
		        picker.setOnAddressPickListener(new AddressPicker.OnAddressPickListener() {
		            @Override
		            public void onAddressPicked(String province, String city, String county) {
		            	tv_address.setText(province+city+county);
		            }
		        });
		        picker.show();
			break;
		case R.id.btn_submit: // 添加
			
			break;
		
		case R.id.tv_cancle:

			dialog.dismiss();
			break;
		case R.id.tv_ok:

			if(bank1.isChecked()){
				open_bank.setText(bank1.getText());
				
			}else if(bank2.isChecked()){
				open_bank.setText(bank2.getText());
				
			}
			else if(bank3.isChecked()){
				open_bank.setText(bank3.getText());
				
			}else if(bank4.isChecked()){
				open_bank.setText(bank4.getText());
				
			}
			else if(bank5.isChecked()){
				open_bank.setText(bank5.getText());
				
			}
			else if(bank6.isChecked()){
				open_bank.setText(bank6.getText());
				
			}
			dialog.dismiss();
			break;
		
		}

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

		TextView cancle = (TextView) dialog.findViewById(R.id.tv_cancle);
		TextView ok = (TextView) dialog.findViewById(R.id.tv_ok);
		bank1 = (RadioButton) dialog.findViewById(R.id.rb_li);
		bank2 = (RadioButton) dialog.findViewById(R.id.rb_pian);
		bank3 = (RadioButton) dialog.findViewById(R.id.rb_dai);
		bank4 = (RadioButton) dialog.findViewById(R.id.rb_zhi);
		bank5 = (RadioButton) dialog.findViewById(R.id.rb_bank5);
		bank6 = (RadioButton) dialog.findViewById(R.id.rb_bank6);
		View view5 = (View) dialog.findViewById(R.id.view5);
		View view6 = (View) dialog.findViewById(R.id.view6);
		view5.setVisibility(View.VISIBLE);
		view6.setVisibility(View.VISIBLE);
		bank5.setVisibility(View.VISIBLE);
		bank6.setVisibility(View.VISIBLE);
		
		bank1.setText("中国银行");
		bank2.setText("工商银行");
		bank3.setText("招商银行");
		bank4.setText("建设银行");
		bank5.setText("华夏银行");
		bank6.setText("交通银行");
		

		cancle.setOnClickListener(this);
		ok.setOnClickListener(this);
		
		
		
	}
}
