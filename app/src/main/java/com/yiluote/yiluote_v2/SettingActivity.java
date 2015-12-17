package com.yiluote.yiluote_v2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SettingActivity extends Activity implements OnClickListener{
	
	Intent intent;
	TextView settingRelative;
	ImageView settingBack;
	RelativeLayout settingLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting2);
		
//		settingBack = (ImageView) findViewById(R.id.settingBack);
		settingRelative = (TextView) findViewById(R.id.settingRelative);
		settingLayout = (RelativeLayout) findViewById(R.id.settingLayout);
		
//		settingBack.setOnClickListener(this);
		settingRelative.setOnClickListener(this);
		settingLayout.setOnClickListener(new OnClickListener() {  
            public void onClick(View v) {  
                //Toast.makeText(getApplicationContext(), "提示：点击外部关闭窗口", Toast.LENGTH_SHORT).show();
            }  
        });
	}

	//实现onTouchEvent触屏函数，点击屏幕其他地方时销毁本Activity  
    @Override  
    public boolean onTouchEvent(MotionEvent event){  
        finish();  
        return true;  
    }
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
//		case R.id.settingBack:
//			intent = new Intent();
//			intent.setClass(SettingActivity.this, MainActivity.class);
//			intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//			startActivity(intent);
//			finish();
//			break;
			
		case R.id.settingRelative:
			intent = new Intent();
			intent.setClass(SettingActivity.this, SettingRelativeActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}
}
