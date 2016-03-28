package com.meishengkangle.mskl.activity;

import java.util.ArrayList;

import com.meishengkangle.mskl.R;
import com.meishengkangle.mskl.adapter.MoodRecordAdapter;
import com.meishengkangle.mskl.view.SwipeLayoutManager;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.AbsListView.OnScrollListener;

public class MoodRecordActivity extends Activity  {

	private ListView listview;
	private ArrayList<String> list = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_mood_record);
		listview = (ListView) findViewById(R.id.listview);
		//1.准备数据
		for (int i = 0; i < 30; i++) {
			list.add("name - "+i);
		}
		listview.setAdapter(new MoodRecordAdapter(this,list));
		
		
		listview.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				if(scrollState==OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
					//如果垂直滑动，则需要关闭已经打开的layout
					SwipeLayoutManager.getInstance().closeCurrentLayout();
				}
			}
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
			}
		});

	}

	
}
