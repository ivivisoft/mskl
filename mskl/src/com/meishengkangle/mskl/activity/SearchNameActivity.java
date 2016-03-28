package com.meishengkangle.mskl.activity;

import java.util.ArrayList;
import java.util.Collections;

import com.meishengkangle.mskl.R;
import com.meishengkangle.mskl.adapter.SearchDrugNameAdapter;
import com.meishengkangle.mskl.domain.Friend;
import com.meishengkangle.mskl.utils.PinYinUtil;
import com.meishengkangle.mskl.view.QuickIndexBar;
import com.meishengkangle.mskl.view.QuickIndexBar.OnTouchLetterListener;
import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.FeatureInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Initial the camera
 * 
 * @author Ryan.Tang
 */
public class SearchNameActivity extends Activity {

	/** Called when the activity is first created. */
	private QuickIndexBar quickIndexBar;
	private ListView listview, search_listview;
	private TextView currentWord, tv_middle;
	private EditText editName;
	private Button submit;
	private ImageView iv_left_img;
	private RelativeLayout rl_list;

	private ArrayList<Friend> friends = new ArrayList<Friend>();

	private ArrayList<Friend> searchList = new ArrayList<Friend>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_name);
		quickIndexBar = (QuickIndexBar) findViewById(R.id.quickIndexBar);
		rl_list = (RelativeLayout) findViewById(R.id.rl_list);
		listview = (ListView) findViewById(R.id.listview);
		search_listview = (ListView) findViewById(R.id.search_listview);
		currentWord = (TextView) findViewById(R.id.currentWord);
		editName = (EditText) findViewById(R.id.et_drug_name);
		submit = (Button) findViewById(R.id.btn_submit);
		tv_middle = (TextView) findViewById(R.id.tv_middle);
		iv_left_img = (ImageView) findViewById(R.id.iv_left_img);

		tv_middle.setVisibility(View.VISIBLE);
		tv_middle.setText("选择药品名称");

		iv_left_img.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		// 1.准备数据
		fillList();
		// 2.对数据进行排序
		Collections.sort(friends);
		// 3.设置Adapter
		listview.setAdapter(new SearchDrugNameAdapter(this, friends));

		quickIndexBar.setOnTouchLetterListener(new OnTouchLetterListener() {
			@Override
			public void onTouchLetter(String letter) {
				// 根据当前触摸的字母，去集合中找那个item的首字母和letter一样，然后将对应的item放到屏幕顶端
				for (int i = 0; i < friends.size(); i++) {
					String firstWord = friends.get(i).getPinyin().charAt(0)
							+ "";
					if (letter.equals(firstWord)) {
						// 说明找到了，那么应该讲当前的item放到屏幕顶端
						listview.setSelection(i);
						break;// 只需要找到第一个就行
					}
				}

				// 显示当前触摸的字母
				showCurrentWord(letter);
			}
		});

		// 通过缩小currentWord来隐藏
		ViewHelper.setScaleX(currentWord, 0);
		ViewHelper.setScaleY(currentWord, 0);

		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				editName.setText(friends.get(position).getName());

			}
		});
		search_listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				editName.setText(searchList.get(position).getName());
			}
		});

		editName.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// 根据当前触摸的字母，去集合中找那个item的首字母和letter一样，然后将对应的item放到屏幕顶端
				String nameWord;
				searchList.clear();
				for (int i = 0; i < friends.size(); i++) {
					String firstWord = friends.get(i).getPinyin().charAt(0)
							+ "";
					if (!editName.getText().toString().trim().isEmpty()) {
						nameWord = PinYinUtil.getPinyin(
								editName.getText().toString()).charAt(0)
								+ "";
						if (nameWord.equals(firstWord)) {
							// 说明找到了，那么应该讲当前的item放到屏幕顶端

							searchList.add(friends.get(i));

						}
						
					}
					if (searchList.size() > 0) {
						search_listview.setVisibility(View.VISIBLE);
						search_listview.setAdapter(new SearchDrugNameAdapter(
								getApplicationContext(), searchList));
						rl_list.setVisibility(View.GONE);
					} 

					if (editName.getText().toString().trim().isEmpty()) {
						search_listview.setVisibility(View.GONE);
						rl_list.setVisibility(View.VISIBLE);
					}
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

		submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String resultString = editName.getText().toString();
				 if (resultString.isEmpty()) {
					Toast.makeText(SearchNameActivity.this, "不能为空",
							Toast.LENGTH_SHORT).show();
				} else {
					Intent resultIntent = new Intent();
					resultIntent.putExtra("result", resultString);
					SearchNameActivity.this.setResult(RESULT_OK, resultIntent);
					SearchNameActivity.this.finish();
				}

			}
		});

	}

	private boolean isScale = false;
	private Handler handler = new Handler();

	protected void showCurrentWord(String letter) {
		currentWord.setText(letter);
		if (!isScale) {
			isScale = true;
			ViewPropertyAnimator.animate(currentWord).scaleX(1f)
					.setInterpolator(new OvershootInterpolator())
					.setDuration(450).start();
			ViewPropertyAnimator.animate(currentWord).scaleY(1f)
					.setInterpolator(new OvershootInterpolator())
					.setDuration(450).start();
		}

		// 先移除之前的任务
		handler.removeCallbacksAndMessages(null);

		// 延时隐藏currentWord
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				// currentWord.setVisibility(View.INVISIBLE);
				ViewPropertyAnimator.animate(currentWord).scaleX(0f)
						.setDuration(450).start();
				ViewPropertyAnimator.animate(currentWord).scaleY(0f)
						.setDuration(450).start();
				isScale = false;
			}
		}, 1500);
	}

	private void fillList() {
		// 虚拟数据
		friends.add(new Friend("李伟"));
		friends.add(new Friend("张三"));
		friends.add(new Friend("阿三"));
		friends.add(new Friend("阿四"));
		friends.add(new Friend("段誉"));
		friends.add(new Friend("段正淳"));
		friends.add(new Friend("张三丰"));
		friends.add(new Friend("陈坤"));
		friends.add(new Friend("林俊杰1"));
		friends.add(new Friend("陈坤2"));
		friends.add(new Friend("王二a"));
		friends.add(new Friend("林俊杰a"));
		friends.add(new Friend("张四"));
		friends.add(new Friend("林俊杰"));
		friends.add(new Friend("王二"));
		friends.add(new Friend("王二b"));
		friends.add(new Friend("赵四"));
		friends.add(new Friend("杨坤"));
		friends.add(new Friend("赵子龙"));
		friends.add(new Friend("杨坤1"));
		friends.add(new Friend("李伟1"));
		friends.add(new Friend("宋江"));
		friends.add(new Friend("宋江1"));
		friends.add(new Friend("李伟3"));
	}

}
