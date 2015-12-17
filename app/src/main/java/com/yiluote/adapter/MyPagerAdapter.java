package com.yiluote.adapter;

import java.util.List;

import com.yiluote.bean.BeanAD;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

public class MyPagerAdapter extends PagerAdapter {

	private Context mContext;
	private List<BeanAD> mDatas;
	private List<ImageView> mImageViews;

	public MyPagerAdapter(Context mContext, List<BeanAD> mDatas,
						  List<ImageView> mImageViews) {
		super();
		this.mContext = mContext;
		this.mDatas = mDatas;
		this.mImageViews = mImageViews;
	}

	@Override
	public int getCount() {
		return mDatas.size();
	}

	@Override
	public Object instantiateItem(ViewGroup container, final int position) {
		ImageView iv = mImageViews.get(position);
		((ViewPager) container).addView(iv);
		//设置广告图片的点击事件
		iv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mDatas.get(position).getTargetUrl() == "") {
					Toast.makeText(mContext, "无法找到目标网址", Toast.LENGTH_SHORT).show();
				}
				else {
					Uri uri = Uri.parse(mDatas.get(position).getTargetUrl());
					Intent intent = new Intent(Intent.ACTION_VIEW, uri);
					mContext.startActivity(intent);
				}
			}
		});
		return iv;
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		((ViewPager) container).removeView((View) object);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

}
