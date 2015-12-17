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

public class MyPagerAdapter2 extends PagerAdapter {

	private Context mContext;
	private int[] mDatas;
	private List<ImageView> mImageViews;

	public MyPagerAdapter2(Context mContext, int[] mDatas,
			List<ImageView> mImageViews) {
		super();
		this.mContext = mContext;
		this.mDatas = mDatas;
		this.mImageViews = mImageViews;
	}

	@Override
	public int getCount() {
		return mDatas.length;
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, final int position) {
		ImageView iv = mImageViews.get(position);
		((ViewPager) container).addView(iv);
		
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
