package com.meishengkangle.mskl.login;

import java.util.ArrayList;
import java.util.Map;

import org.json.JSONObject;


import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import com.meishengkangle.mskl.R;
import com.meishengkangle.mskl.utils.MD5Util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegistActivity extends Activity implements OnClickListener{

	private EditText register_phone;
	private EditText register_passwd;
	private EditText et_validate;
	private EditText et_invite;
	private Button btn_regist;
	private TextView getCode;
	
	private String iPhone;
	private String iCord;
	private boolean flag = true;
	 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_regist);
		
		init();
		
		SMSSDK.initSDK(this, "1041c377fef38", "1173604d4cdae71cb3fc528cc1845484");
		
		EventHandler eh=new EventHandler(){
			 
            @Override
            public void afterEvent(int event, int result, Object data) {
                 
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
             
        };
        SMSSDK.registerEventHandler(eh);
         

	}
	
	 private void init() {
		   register_phone=(EditText)findViewById(R.id.et_phone);
			et_validate=(EditText)findViewById(R.id.et_validate);
			register_passwd=(EditText)findViewById(R.id.et_password);
			et_invite=(EditText)findViewById(R.id.et_invite);
			btn_regist = (Button) findViewById(R.id.btn_regist);
			getCode=(TextView) findViewById(R.id.tv_getcode);
	        getCode.setOnClickListener(this);
	        btn_regist.setOnClickListener(this);
	    }
	  @Override
	    public void onClick(View v) {
	        switch (v.getId()) {
	        case R.id.tv_getcode:
	            if(!TextUtils.isEmpty(register_phone.getText().toString().trim())){
	                if(register_phone.getText().toString().trim().length()==11){
	                    iPhone = register_phone.getText().toString().trim();
	                    SMSSDK.getVerificationCode("86",iPhone);
	                    et_validate.requestFocus();
	                    getCode.setVisibility(View.GONE);
	                }else{
	                    Toast.makeText(RegistActivity.this, "请输入完整电话号码", Toast.LENGTH_LONG).show();
	                    register_phone.requestFocus();
	                }
	            }else{
	                Toast.makeText(RegistActivity.this, "请输入您的电话号码", Toast.LENGTH_LONG).show();
	                register_phone.requestFocus();
	            }
	            break;
	 
	        case R.id.btn_regist:
	            if(!TextUtils.isEmpty(et_validate.getText().toString().trim())){
	                if(et_validate.getText().toString().trim().length()==4){
	                    iCord = et_validate.getText().toString().trim();
	                    SMSSDK.submitVerificationCode("86", iPhone, iCord);
	                    flag = false;
	                }else{
	                    Toast.makeText(RegistActivity.this, "请输入完整验证码", Toast.LENGTH_LONG).show();
	                    et_validate.requestFocus();
	                }
	            }else{
	                Toast.makeText(RegistActivity.this, "请输入验证码", Toast.LENGTH_LONG).show();
	                et_validate.requestFocus();
	            }
	            break;
	             
	        default:
	            break;
	        }
	    }
	  
	  Handler handler=new Handler(){
		  
	        @Override
	        public void handleMessage(Message msg) {
	            // TODO Auto-generated method stub
	            super.handleMessage(msg);
	            int event = msg.arg1;
	            int result = msg.arg2;
	            Object data = msg.obj;
	            Log.e("event", "event="+event);
	            if (result == SMSSDK.RESULT_COMPLETE) {
	                //短信注册成功后，返回MainActivity,然后提示新好友
	                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {//提交验证码成功,验证通过
	                    Toast.makeText(getApplicationContext(), "验证码校验成功", Toast.LENGTH_SHORT).show();
	                   
	                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){//服务器验证码发送成功
	                   
	                    Toast.makeText(getApplicationContext(), "验证码已经发送", Toast.LENGTH_SHORT).show();
	                }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){//返回支持发送验证码的国家列表
	                    Toast.makeText(getApplicationContext(), "获取国家列表成功", Toast.LENGTH_SHORT).show();
	                }
	            } else {
	                if(flag){
	                    getCode.setVisibility(View.VISIBLE);
	                    Toast.makeText(RegistActivity.this, "验证码获取失败，请重新获取", Toast.LENGTH_SHORT).show();
	                    register_phone.requestFocus();
	                }else{
	                    ((Throwable) data).printStackTrace();
	                    Toast.makeText(RegistActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
	                    et_validate.selectAll();
//	                    if (resId > 0) {
//	                        Toast.makeText(RegistActivity.this, resId, Toast.LENGTH_SHORT).show();
//	                    }
	                }
	                 
	            }
	             
	        }
	         
	    };
	    
	    
	    @Override
	    protected void onDestroy() {
	        super.onDestroy();
	        SMSSDK.unregisterAllEventHandler();
	    }
	 
	 
}