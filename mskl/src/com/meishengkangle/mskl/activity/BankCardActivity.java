package com.meishengkangle.mskl.activity;

import java.util.Date;

import com.meishengkangle.mskl.R;

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
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 实名认证
 * 
 * @author Administrator
 * 
 */
public class BankCardActivity extends Activity implements OnClickListener {

	private ImageView iv_left_img;
	private TextView tv_middle, tv_right,mAddCard;
	private ImageView iv_image;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bankcard);

		iv_left_img = (ImageView) findViewById(R.id.iv_left_img);
		tv_middle = (TextView) findViewById(R.id.tv_middle);
		tv_right = (TextView) findViewById(R.id.tv_right);
		mAddCard = (TextView) findViewById(R.id.tv_add_card);

		tv_middle.setText("银行卡");
		tv_right.setVisibility(View.VISIBLE);
		tv_right.setTextColor(Color.WHITE);
		tv_right.setText("添加");

		iv_left_img.setOnClickListener(this);
		tv_right.setOnClickListener(this);
		mAddCard.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		
		Intent addIntent=new Intent(getApplicationContext(), AddBankCardActivity.class);
		switch (v.getId()) {

		case R.id.iv_left_img: // 取消操作
			this.finish();
			break;
		case R.id.tv_right: // 添加
			this.startActivity(addIntent);
			break;
		case R.id.tv_add_card: // 添加
			this.startActivity(addIntent);
			break;
		default:
			break;
		}

	}

}
