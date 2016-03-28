package com.meishengkangle.mskl.adapter;

import java.util.ArrayList;

import com.meishengkangle.mskl.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class HorizontalListViewAdapter extends BaseAdapter{
	
	 private ArrayList<String> days;
	 private ArrayList<String> weeks;
	 private Context mContext;  
	public HorizontalListViewAdapter(Context context,ArrayList<String> days,ArrayList<String> weeks) {
		this.days=days;
		this.mContext=context;
		this.weeks=weeks;
	}
	
	

	@Override
	public int getCount() {
		return days.size();
	}

	@Override
	public Object getItem(int position) {
		return days.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;  
        if(convertView==null){  
            holder = new ViewHolder();  
            convertView = View.inflate(mContext,R.layout.horizontal_list_item, null);  
            holder.day=(TextView)convertView.findViewById(R.id.tv_item);  
            holder.week=(TextView)convertView.findViewById(R.id.tv_week);  
            convertView.setTag(holder);  
        }else{  
            holder=(ViewHolder)convertView.getTag();  
        }  
       
          
        holder.day.setText(days.get(position));  
        holder.week.setText(weeks.get(position));  
		
		return convertView;
	}
	
	private static class ViewHolder {  
        private TextView day ;  
        private TextView week ;  
        
    }  

}
