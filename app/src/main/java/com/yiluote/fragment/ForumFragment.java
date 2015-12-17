package com.yiluote.fragment;

import java.util.ArrayList;
import java.util.List;

import com.yiluote.adapter.MyPagerAdapter2;
import com.yiluote.yiluote_v2.R;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

public class ForumFragment extends Fragment implements OnClickListener {

	private ViewPager forumViewPager;
	private View dot0, dot1, dot2, dot3;
	private List<View> dots;
	private List<View> dotList;
	private List<ImageView> imageViews;// 轮播图片集合
	private int currentItem = 0;// 当前图片的索引号

	private int[] forumDatas = { R.drawable.forum_demo1,
			R.drawable.forum_demo2, R.drawable.forum_demo3,
			R.drawable.forum_demo4 };// 轮播的数据

	private MyPagerAdapter2 myPagerAdapter;

	private ImageView forumLike, forumGood, forumHead6;
	private TextView forumGoodNum, forumAttention;
	private Boolean isGood = false;
	private Boolean isLike = false;
	private Boolean isAttention = false;
	private int goodNum;

	// 动态更新当前图片索引号
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			forumViewPager.setCurrentItem(currentItem);
		};
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_forum, container, false);

		initView(view);
		initEvent();
		initDatas();

		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	private void initDatas() {
		forumGoodNum.setText(""+5);
		isGood = false;
		goodNum = Integer.parseInt(forumGoodNum.getText().toString());
		imageViews = new ArrayList<ImageView>();
		dots = new ArrayList<View>();
		dotList = new ArrayList<View>();
		dots.add(dot0);
		dots.add(dot1);
		dots.add(dot2);
		dots.add(dot3);
		myPagerAdapter = new MyPagerAdapter2(getActivity(), forumDatas,
				imageViews);
		forumViewPager.setAdapter(myPagerAdapter);
		forumViewPager.setOnPageChangeListener(new MyPageChangeListener());
		addDynamicView();
	}

	private void addDynamicView() {
		// 初始化图片资源
		for (int i = 0; i < forumDatas.length; i++) {
			ImageView imageView = new ImageView(getActivity());
			imageView.setImageResource(forumDatas[i]);
			imageView.setScaleType(ScaleType.FIT_XY);
			imageViews.add(imageView);
			dots.get(i).setVisibility(View.VISIBLE);
			dotList.add(dots.get(i));
		}
	}

	private class MyPageChangeListener implements OnPageChangeListener {
		private int oldPosition = 0;

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int position) {
			currentItem = position;
			dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
			dots.get(position).setBackgroundResource(R.drawable.dot_focused);
			oldPosition = position;
		}
	}

	private void initView(View view) {
		forumViewPager = (ViewPager) view.findViewById(R.id.forumViewPager);
		dot0 = view.findViewById(R.id.v_dot0);
		dot1 = view.findViewById(R.id.v_dot1);
		dot2 = view.findViewById(R.id.v_dot2);
		dot3 = view.findViewById(R.id.v_dot3);
		forumLike = (ImageView) view.findViewById(R.id.forumLike);
		forumGood = (ImageView) view.findViewById(R.id.forumGood);
		forumGoodNum = (TextView) view.findViewById(R.id.forumGoodNum);
		forumAttention = (TextView) view.findViewById(R.id.forumAttention);
		forumHead6 = (ImageView) view.findViewById(R.id.forumHead6);
	}

	private void initEvent() {
		forumLike.setOnClickListener(this);
		forumGood.setOnClickListener(this);
		forumAttention.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.forumLike:
				if(isLike){
					forumLike.setImageResource(R.drawable.forum_like_gray);
					isLike = false;
				} else {
					forumLike.setImageResource(R.drawable.forum_like_red);
					isLike = true;
				}
				break;

			case R.id.forumGood:
				if(isGood){
					goodNum -= 1;
					forumGoodNum.setText(goodNum+"");
					forumHead6.setVisibility(View.INVISIBLE);
					isGood = false;
				} else {
					goodNum += 1;
					forumGoodNum.setText(goodNum+"");
					forumHead6.setVisibility(View.VISIBLE);
					isGood = true;
				}
				break;

			case R.id.forumAttention:
				if(isAttention){
					forumAttention.setBackgroundResource(R.drawable.rect_corner_forum_attention);
					isAttention = false;
				} else {
					forumAttention.setBackgroundResource(R.drawable.rect_corner_forum_attention_red);
					isAttention = true;
				}
				break;

			default:
				break;
		}
	}
}
