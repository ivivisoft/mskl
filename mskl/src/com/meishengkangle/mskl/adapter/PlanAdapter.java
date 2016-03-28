package com.meishengkangle.mskl.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.meishengkangle.mskl.R;
import com.meishengkangle.mskl.domain.Friend;
import com.meishengkangle.mskl.domain.User;

public class PlanAdapter extends BaseAdapter{
	private Context context;
	private ArrayList<User> list;
	public PlanAdapter(Context context,ArrayList<User> list) {
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
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null){
			convertView = View.inflate(context, R.layout.item_plan,null);
		}
		
		ViewHolder holder = ViewHolder.getHolder(convertView);
		//设置数据
		User plan = list.get(position);
		holder.name.setText(plan.getName());
		holder.count.setText(plan.getCount());
		holder.time.setText(plan.getTime());
		
		
		
		
		return convertView;
	}
	//ViewHolder进行封装，避免findviewById调用多次

	static class ViewHolder{
		TextView name,count,time;
		
		public ViewHolder(View convertView){
			name = (TextView) convertView.findViewById(R.id.drug_name);
			count = (TextView) convertView.findViewById(R.id.tv_count);
			time = (TextView) convertView.findViewById(R.id.tv_time);
			
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
