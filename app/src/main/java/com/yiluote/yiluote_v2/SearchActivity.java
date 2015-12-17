package com.yiluote.yiluote_v2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class SearchActivity extends Activity implements OnClickListener{
	Intent intent;
	
	ImageView searchBack;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		searchBack = (ImageView) findViewById(R.id.searchBack);
		searchBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.searchBack:
			intent = new Intent();
			intent.setClass(SearchActivity.this, MainActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			startActivity(intent);
			finish();
			break;

		default:
			break;
		}
	}
}
