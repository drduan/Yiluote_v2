package com.yiluote.yiluote_v2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {

	Intent intent;

	EditText loginUsername;
	EditText loginPassword;
	TextView loginButton;
	TextView loginOther;
	TextView loginToRegister;
	TextView loginSkip;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		loginUsername = (EditText) findViewById(R.id.loginUsername);
		loginPassword = (EditText) findViewById(R.id.loginPassword);
		loginButton = (TextView) findViewById(R.id.loginButton);
		loginToRegister = (TextView) findViewById(R.id.loginToRegister);
		loginSkip = (TextView) findViewById(R.id.loginSkip);
		loginOther = (TextView) findViewById(R.id.loginOther);


		loginSkip.setOnClickListener(this);
		loginButton.setOnClickListener(this);
		loginToRegister.setOnClickListener(this);
		loginOther.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.loginSkip:
				intent = new Intent();
				intent.setClass(LoginActivity.this, MainActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
				finish();//结束此Activity
				break;

			case R.id.loginButton:
				intent = new Intent();
				if(loginUsername.getText().toString().equals("") && loginPassword.getText().toString().equals("")){
					Toast.makeText(this, "帐号或密码不能为空", Toast.LENGTH_SHORT).show();
				} else if(loginUsername.getText().toString().equals("lisi")  && loginPassword.getText().toString().equals("123")){
					Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
					intent.setClass(LoginActivity.this, MainActivity.class);
					startActivity(intent);
					overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
					finish();
				} else {
					Toast.makeText(this, "帐号或密码错误", Toast.LENGTH_SHORT).show();
				}
				break;

			case R.id.loginOther:
				break;

			case R.id.loginToRegister:
				break;

			default:
				break;
		}
	}
}
