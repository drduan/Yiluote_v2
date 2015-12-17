package com.yiluote.yiluote_v2;

import org.w3c.dom.Text;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class ReleaseActivity2 extends Activity implements OnClickListener{
	
	private Intent intent;
	private ImageView releaseBack2;
	private TextView releaseBack2Name;
	private TextView releaseNext2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_release2);
		
		releaseBack2 = (ImageView) findViewById(R.id.releaseBack2);
		releaseBack2Name = (TextView) findViewById(R.id.releaseBack2Name);
		releaseNext2 = (TextView) findViewById(R.id.releaseNext2);
		
		releaseBack2.setOnClickListener(this);
		releaseBack2Name.setOnClickListener(this);
		releaseNext2.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.releaseBack2:
		case R.id.releaseBack2Name:
			intent = new Intent();
			intent.setClass(ReleaseActivity2.this, ReleaseActivity1.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			startActivity(intent);
			finish();
			break;
			
		case R.id.releaseNext2:
			intent = new Intent();
			intent.setClass(ReleaseActivity2.this, ReleaseActivity3.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}
}
