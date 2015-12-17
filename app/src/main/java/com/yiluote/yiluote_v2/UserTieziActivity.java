package com.yiluote.yiluote_v2;

import com.yiluote.fragment.UserTieziGridFragment;
import com.yiluote.fragment.UserTieziListFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class UserTieziActivity extends FragmentActivity implements OnClickListener {
	private Intent intent;

	private LinearLayout back;
	private ImageView gridview;
	private ImageView listview;

	private UserTieziGridFragment gridfragment;
	private UserTieziListFragment listfragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_tiezi);
		this.back = (LinearLayout) super.findViewById(R.id.title_back);
		this.gridview = (ImageView) super.findViewById(R.id.grid_on);
		this.listview = (ImageView) super.findViewById(R.id.list_off);
		this.back = (LinearLayout) findViewById(R.id.title_back);
		this.gridview.setOnClickListener(this);
		this.listview.setOnClickListener(this);
		back.setOnClickListener(this);

		initFragment();

	}

	public void initFragment() {
		if (gridfragment == null) {
			gridfragment = new UserTieziGridFragment();
			addFragment(gridfragment);
			showFragment(gridfragment);
		} else {
			showFragment(gridfragment);
		}
		gridview.setImageResource(R.drawable.user_tiezi_show1);
	}

	public void addFragment(Fragment fragment) {
		FragmentTransaction ft = this.getSupportFragmentManager()
				.beginTransaction();
		ft.add(R.id.fragment, fragment);
		ft.commit();
	}

	/** 删除Fragment **/
	public void removeFragment(Fragment fragment) {
		FragmentTransaction ft = this.getSupportFragmentManager()
				.beginTransaction();
		ft.remove(fragment);
		ft.commit();
	}

	/** 显示Fragment **/
	public void showFragment(Fragment fragment) {
		FragmentTransaction ft = getSupportFragmentManager()
				.beginTransaction();

		// 判断页面是否已经创建，如果已经创建，那么就隐藏掉
		if (gridfragment != null) {
			ft.hide(gridfragment);
		}
		if (listfragment != null) {
			ft.hide(listfragment);
		}

		ft.show(fragment);
		ft.commitAllowingStateLoss();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

			case R.id.grid_on:
				if (gridfragment == null) {
					gridfragment = new UserTieziGridFragment();
					addFragment(gridfragment);
					showFragment(gridfragment);
				} else {
					if (gridfragment.isHidden()) {
						showFragment(gridfragment);
					}
				}
				gridview.setImageResource(R.drawable.user_tiezi_show1);
				listview.setImageResource(R.drawable.user_tiezi_show2_gray);
				break;
			case R.id.list_off:
				if (listfragment == null) {
					listfragment = new UserTieziListFragment();
					addFragment(listfragment);
					showFragment(listfragment);
				} else {
					if (listfragment.isHidden()) {
						showFragment(listfragment);
					}
				}
				gridview.setImageResource(R.drawable.user_tiezi_show1_gray);
				listview.setImageResource(R.drawable.user_tiezi_show2);
				break;

			case R.id.title_back:
				intent = new Intent();
				intent.setClass(UserTieziActivity.this, UserActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
				finish();
		}

	}

}
