package com.meishengkangle.mskl.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.meishengkangle.mskl.R;
import com.meishengkangle.mskl.domain.Friend;

public class SearchDrugNameAdapter extends BaseAdapter{
	private Context context;
	private ArrayList<Friend> list;
	public SearchDrugNameAdapter(Context context,ArrayList<Friend> list) {
		super();
		this.context = context;
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
		if(convertView==null){
			convertView = View.inflate(context, R.layout.item_drug_name,null);
		}
		
		ViewHolder holder = ViewHolder.getHolder(convertView);
		//设置数据
		Friend friend = list.get(position);
		holder.name.setText(friend.getName());
		
		
		
		
		return convertView;
	}
	//ViewHolder进行封装，避免findviewById调用多次

	static class ViewHolder{
		TextView name;
		
		public ViewHolder(View convertView){
			name = (TextView) convertView.findViewById(R.id.tv_drug_name);
			
		}
		public static ViewHolder getHolder(View convertView){
			ViewHolder holder = (ViewHolder) convertView.getTag();
			if(holder==null){
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			}
			return holder;
		}
	}
}
