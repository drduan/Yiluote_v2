package com.yiluote.yiluote_v2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class UserFeedbackActivity extends Activity implements OnClickListener{
	
	Intent intent;
	
	TextView userFeedbackBack;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_feedback);
		
		userFeedbackBack = (TextView) findViewById(R.id.userFeedbackBack);
		userFeedbackBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.userFeedbackBack:
			intent = new Intent();
			intent.setClass(UserFeedbackActivity.this, UserActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}
	}
}
