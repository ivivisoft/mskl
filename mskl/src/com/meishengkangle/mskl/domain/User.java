package com.meishengkangle.mskl.domain;

import com.meishengkangle.mskl.utils.PinYinUtil;

public class User{
	private String name;
	private String count;
	private String time;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	//使用成员变量生成构造方法：alt+shift+s->o
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	
	
}
