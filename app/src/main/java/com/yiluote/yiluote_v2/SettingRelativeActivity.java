package com.yiluote.yiluote_v2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class SettingRelativeActivity extends Activity implements OnClickListener {

	private Intent intent;
	private ImageView qq_view;
	private ImageView sina_view;
	private ImageView weixin_view;
	private ImageView qq_duihao;
	private ImageView sina_duihao;
	private ImageView weixin_duihao;
	private TextView settingRelativeBack;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_setting_relative);
		this.qq_view = (ImageView)findViewById(R.id.qq_logo);
		this.sina_view = (ImageView)findViewById(R.id.sina_logo);
		this.weixin_view = (ImageView)findViewById(R.id.weixin_logo);
		this.qq_duihao=(ImageView)findViewById(R.id.qq_duihao);
		this.sina_duihao=(ImageView)findViewById(R.id.sina_duihao);
		this.weixin_duihao=(ImageView)findViewById(R.id.weixin_duihao);
		settingRelativeBack = (TextView) findViewById(R.id.settingRelativeBack);
		
		qq_view.setOnClickListener(this);
		sina_view.setOnClickListener(this);
		weixin_view.setOnClickListener(this);
		settingRelativeBack.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.qq_logo:
			qq_duihao.setImageResource(R.drawable.setting_relevance_choice_true);
			sina_duihao.setImageResource(R.drawable.setting_relevance_choice_false);
			weixin_duihao.setImageResource(R.drawable.setting_relevance_choice_false);
			break;
		case R.id.sina_logo:
			qq_duihao.setImageResource(R.drawable.setting_relevance_choice_false);
			sina_duihao.setImageResource(R.drawable.setting_relevance_choice_true);
			weixin_duihao.setImageResource(R.drawable.setting_relevance_choice_false);
		    break;
		case R.id.weixin_logo:
			qq_duihao.setImageResource(R.drawable.setting_relevance_choice_false);
			sina_duihao.setImageResource(R.drawable.setting_relevance_choice_false);
			weixin_duihao.setImageResource(R.drawable.setting_relevance_choice_true);
			break;
		case R.id.settingRelativeBack:
			intent = new Intent();
			intent.setClass(SettingRelativeActivity.this, SettingActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			startActivity(intent);
			finish();
			break;
		default:
			break;
		}
		
	}
}
