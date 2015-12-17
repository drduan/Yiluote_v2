package com.yiluote.yiluote_v2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class UserActivity extends Activity implements OnClickListener {

	LinearLayout myMessage, myDrafts, myParticipation, myCollection, myTie, userLuoluobi, userFeedback;
	ImageView userBack;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user);

		myMessage = (LinearLayout) findViewById(R.id.myMessage);
		myDrafts = (LinearLayout) findViewById(R.id.myDrafts);
		myParticipation = (LinearLayout) findViewById(R.id.myParticipation);
		myTie = (LinearLayout) findViewById(R.id.myTie);
		myCollection = (LinearLayout) findViewById(R.id.myCollection);
		userFeedback = (LinearLayout) findViewById(R.id.userFeedback);
		userLuoluobi = (LinearLayout) findViewById(R.id.userLuoluobi);
		userBack = (ImageView) findViewById(R.id.userBack);
		
		myMessage.setOnClickListener(this);
		myDrafts.setOnClickListener(this);
		myParticipation.setOnClickListener(this);
		myTie.setOnClickListener(this);
		myCollection.setOnClickListener(this);
		userBack.setOnClickListener(this);
		userFeedback.setOnClickListener(this);
		userLuoluobi.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.myMessage:
			intent = new Intent();
			intent.setClass(UserActivity.this, UserMessageActivity.class);
			startActivity(intent);
			break;
			
		case R.id.myDrafts:
			intent = new Intent();
			intent.setClass(UserActivity.this, UserDraftsActivity.class);
			startActivity(intent);
			break;
			
		case R.id.myParticipation:
			intent = new Intent();
			intent.setClass(UserActivity.this, UserParticitapionActivity.class);
			startActivity(intent);
			break;
			
		case R.id.myCollection:
			intent = new Intent();
			intent.setClass(UserActivity.this, UserCollectionActivity.class);
			startActivity(intent);
			break;
		
		case R.id.myTie:
			intent = new Intent();
			intent.setClass(UserActivity.this, UserTieziActivity.class);
			startActivity(intent);
			break;
			
		case R.id.userFeedback:
			intent = new Intent();
			intent.setClass(UserActivity.this, UserFeedbackActivity.class);
			startActivity(intent);
			break;
			
		case R.id.userLuoluobi:
			Toast.makeText(this, "亲，洛洛币功能还没上线哦~", Toast.LENGTH_SHORT).show();
			break;
			
		case R.id.userBack:
			intent = new Intent();
			intent.setClass(UserActivity.this, MainActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}
	}
}