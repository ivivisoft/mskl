package com.meishengkangle.mskl.login;


import com.meishengkangle.mskl.R;
import com.meishengkangle.mskl.net.interfa.LoginProtocol;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {

	private Button btn_login;
	private Button btnRegist;
	private EditText userName,passWord;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		
		
		initView();
	}

	public void initView(){
		userName = (EditText) findViewById(R.id.et_username);
		passWord = (EditText) findViewById(R.id.et_password);
		btn_login = (Button) findViewById(R.id.btn_login);
		btnRegist = (Button) findViewById(R.id.btn_regist);
		
		btn_login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				LoginProtocol protocol=new LoginProtocol(LoginActivity.this);
				protocol.getDataFromServer(userName.getText().toString(), passWord.getText().toString());
				
				
			}
		});
		
	}
	
	
	

}
