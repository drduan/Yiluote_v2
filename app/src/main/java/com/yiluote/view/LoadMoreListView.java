package com.yiluote.view;

import com.yiluote.yiluote_v2.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

public class LoadMoreListView extends ListView {

	View footerView;

	public LoadMoreListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
		// TODO Auto-generated constructor stub
	}

	public LoadMoreListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
		// TODO Auto-generated constructor stub
	}

	public LoadMoreListView(Context context) {
		super(context);
		initView(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 添加底部加载更多的布局
	 * @param context
	 */
	private void initView(Context context){
		LayoutInflater inflater = LayoutInflater.from(context);
		footerView = inflater.inflate(R.layout.load_more, null);
		this.addFooterView(footerView);
	}
}
