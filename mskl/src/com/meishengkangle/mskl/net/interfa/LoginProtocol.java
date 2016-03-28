package com.meishengkangle.mskl.net.interfa;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.meishengkangle.mskl.domain.User;
import com.meishengkangle.mskl.global.GlobalConstants;
import com.meishengkangle.mskl.utils.LogUtils;
import com.meishengkangle.mskl.utils.MD5Util;

public class LoginProtocol {

	private Context mContext;
	private User user;

	public LoginProtocol(Context mContext) {
		this.mContext = mContext;
	}

	/**
	 * 从服务器获取数据 需要权限:<uses-permission android:name="android.permission.INTERNET"
	 * />
	 */

	public void getDataFromServer(String username, String password) {
		HttpUtils utils = new HttpUtils();
		
		JSONObject json=new JSONObject();
		try {
			json.put("username",username);
			json.put("password", password);
			
			
		} catch (JSONException e) {
			LogUtils.e(e);
		}
		
		RequestParams params=new RequestParams();
		params.setContentType("application/json");
		try {
			params.setBodyEntity(new StringEntity(json.toString(),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			
			LogUtils.e(e);
		}
		
		Date date=new Date();
		long time=date.getTime();
		String md5Str=MD5Util.encode("password='" + password + "'&username='" + username + "'"+"&t=" + time + GlobalConstants.signKey);
		

		utils.send(HttpMethod.POST, GlobalConstants.LOGIN_URL+"/"+time+"/"+md5Str, params,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException error, String msg) {
						// 请求失败
						error.printStackTrace();
						Toast.makeText(mContext, msg, Toast.LENGTH_SHORT)
								.show();

					}

					@Override
					public void onSuccess(ResponseInfo<String> responseInfo) {
						// 请求成功
						String result = responseInfo.result;// 获取服务器返回结果
						System.out.println("服务器返回结果:" + result);

						// JsonObject, Gson

						processData(result);
						// 写缓存
						// CacheUtils.setCache(GlobalConstants.CATEGORY_URL,
						// result, mActivity);

					}

				});
	}
	
	
	/**
	 * 解析数据
	 */
	protected User processData(String json) {
		Gson gson=new Gson();
		user = gson.fromJson(json, User.class);
		return user;
	}

}
