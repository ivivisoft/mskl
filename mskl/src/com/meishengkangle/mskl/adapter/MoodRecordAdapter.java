package com.meishengkangle.mskl.adapter;

import java.util.ArrayList;

import com.meishengkangle.mskl.R;
import com.meishengkangle.mskl.view.SwipeLayout;
import com.meishengkangle.mskl.view.SwipeLayout.OnSwipeStateChangeListener;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MoodRecordAdapter extends BaseAdapter implements
		OnSwipeStateChangeListener {
	private Context mContext;
	private ArrayList<String> list;

	public MoodRecordAdapter(Context mContext, ArrayList<String> list) {
		this.mContext = mContext;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = View.inflate(mContext, R.layout.item_mood_list, null);
		}
		ViewHolder holder = ViewHolder.getHolder(convertView);

		holder.tv_name.setText(list.get(position));

		holder.swipeLayout.setTag(position);
		holder.swipeLayout.setOnSwipeStateChangeListener(this);

		return convertView;
	}

	@Override
	public void onOpen(Object tag) {
		Toast.makeText(mContext, "第" + (Integer) tag + "个打开", 0).show();
	}

	@Override
	public void onClose(Object tag) {
		Toast.makeText(mContext, "第" + (Integer) tag + "个关闭", 0).show();
	}

}

class ViewHolder {
	TextView tv_name, tv_delete;
	SwipeLayout swipeLayout;

	public ViewHolder(View convertView) {
		tv_name = (TextView) convertView.findViewById(R.id.tv_name);
		tv_delete = (TextView) convertView.findViewById(R.id.tv_delete);
		swipeLayout = (SwipeLayout) convertView.findViewById(R.id.swipeLayout);
	}

	public static ViewHolder getHolder(View convertView) {
		ViewHolder holder = (ViewHolder) convertView.getTag();
		if (holder == null) {
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}
		return holder;
	}
}