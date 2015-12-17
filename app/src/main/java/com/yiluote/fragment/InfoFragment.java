package com.yiluote.fragment;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.zip.Adler32;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.yiluote.adapter.InfoAdapter;
import com.yiluote.adapter.InfoAdapterWithCommonViewHolder;
import com.yiluote.adapter.MyPagerAdapter;
import com.yiluote.bean.BeanAD;
import com.yiluote.bean.BeanInfo;
import com.yiluote.view.ListViewForScroll;
import com.yiluote.yiluote_v2.R;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class InfoFragment extends Fragment {

	private ListView infoListView;
	//	private ScrollView infoScrollView;
//	private ListViewForScroll infoListView;
	private List<BeanInfo> infoDatas;
	private List<ImageView> infoImageViews;//咨询图片的集合
	private InfoAdapterWithCommonViewHolder infoAdapterWithCommonViewHolder;

	int resLableColor[] = {
			R.drawable.lable_1, R.drawable.lable_2,
			R.drawable.lable_3, R.drawable.lable_4,
			R.drawable.lable_5};

	public static String IMAGE_CACHE_PATH = "Yiluote/imageCache";//图片缓存路径
	private ViewPager adViewPager;
	private TextView adTitle;
	private View dot0, dot1, dot2, dot3;
	private List<View> dots;
	private List<View> dotList;
	private List<ImageView> imageViews;//广告的图片集合
	private int currentItem = 0;//当前图片的索引号
	private List<BeanAD> adDatas;//轮播广告的数据
	private MyPagerAdapter myPagerAdapter;

	//定时任务
	private ScheduledExecutorService scheduledExecutorService;
	//异步加载图片
	private ImageLoader mImageLoader;
	private DisplayImageOptions options;

	//动态更新当前图片索引号
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			adViewPager.setCurrentItem(currentItem);
		};
	};

	private String[] adTitles = {
			"广告标题1",
			"广告标题2",
			"广告标题3",
			"广告标题4"
	};
	private String[] adImageUrls = {
			"http://g.hiphotos.baidu.com/image/w%3D310/sign=bb99d6add2c8a786be2a4c0f5708c9c7/d50735fae6cd7b8900d74cd40c2442a7d9330e29.jpg",
			"http://d.hiphotos.baidu.com/image/w%3D310/sign=54884c82b78f8c54e3d3c32e0a282dee/a686c9177f3e670932e4cf9338c79f3df9dc55f2.jpg",
			"http://e.hiphotos.baidu.com/image/w%3D310/sign=392ce7f779899e51788e3c1572a6d990/8718367adab44aed22a58aeeb11c8701a08bfbd4.jpg",
			"http://e.hiphotos.baidu.com/image/w%3D310/sign=66270b4fe8c4b7453494b117fffd1e78/0bd162d9f2d3572c7dad11ba8913632762d0c30d.jpg"
	};
	private String[] adTargetUrls = {
			"http://www.baidu.com/",
			"http://www.baidu.com/",
			"http://www.baidu.com/",
			"http://www.baidu.com/"
	};
	private String[] infoTargetUrls = {
			"http://www.baidu.com/"
	};
	private String[] infoImageUrls = {
			"http://d02.res.meilishuo.net/pic/_o/7f/63/c35d60c00d23229d44fbf7061847_640_832.cf.jpg_df14853a_s7_450_680.jpg",
			"http://d04.res.meilishuo.net/pic/_o/5b/88/0b0bf6914f1304a21cea4b26fb6c_700_700.c8.jpeg",
			"http://d06.res.meilishuo.net/pic/_o/cb/1b/b29b55c0fde6e25f928ba9942404_640_960.cg.jpg_6391fdef_s7_450_680.jpg"
	};

	private final String INFO_FRAGMENT_URL = "http://123.56.117.235/yiluote/index.php/Home/News/showNewsx";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_info, container, false);
		initView(view);
		initDatas();
//		infoScrollView = (ScrollView) view.findViewById(R.id.infoScrollView);
//		infoScrollView.smoothScrollTo(0, 0);
		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initImageLoader();	//只需被创建一次，所以放在OnCreate中
		startAd();			//只需被创建一次，所以放在OnCreate中
	}

	private void startAd() {
		scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		//当Activity显示出来后，每两秒切换一次图片显示
		scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 500, 2500, TimeUnit.MILLISECONDS);

	}

	private class ScrollTask implements Runnable {
		@Override
		public void run() {
			currentItem = (currentItem + 1) % imageViews.size();
			handler.obtainMessage().sendToTarget();
		}
	}

	private void initImageLoader() {
		File cacheDir = com.nostra13.universalimageloader.utils.StorageUtils
				.getOwnCacheDirectory(getActivity().getApplicationContext(), IMAGE_CACHE_PATH);

		DisplayImageOptions defaultOptions = new DisplayImageOptions
				.Builder()
				.cacheInMemory(true)
				.cacheOnDisc(true)
				.build();

		ImageLoaderConfiguration config = new ImageLoaderConfiguration
				.Builder(getActivity())
				.defaultDisplayImageOptions(defaultOptions)
				.memoryCache(new LruMemoryCache(12 * 1024 * 1024))
				.memoryCacheSize(12 * 1024 * 1024)
				.discCacheSize(32 * 1024 * 1024)
				.discCacheFileCount(100)
				.discCache(new UnlimitedDiscCache(cacheDir))
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.build();

		ImageLoader.getInstance().init(config);
	}


	private void initDatas() {
		/*
		 * 广告部分数据初始化
		 */
		adDatas = getAdDatas();
		imageViews = new ArrayList<ImageView>();
		dots = new ArrayList<View>();
		dotList = new ArrayList<View>();
		dots.add(dot0);
		dots.add(dot1);
		dots.add(dot2);
		dots.add(dot3);
		myPagerAdapter = new MyPagerAdapter(getActivity(), adDatas, imageViews);
		adViewPager.setAdapter(myPagerAdapter);
		adViewPager.setOnPageChangeListener(new MyPageChangeListener());
		mImageLoader = ImageLoader.getInstance();
		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.icon_stub)
				.showImageForEmptyUri(R.drawable.icon_stub)
				.showImageOnFail(R.drawable.icon_stub)
				.cacheInMemory(true)
				.cacheOnDisc(true)
				.bitmapConfig(Bitmap.Config.RGB_565)
				.imageScaleType(ImageScaleType.EXACTLY)
				.build();
		addDynamicView();
		//----------------------分隔线----------------------
		/*
		 * 咨询部分数据初始化
		 */
		infoDatas = getInfoDatas();
		infoImageViews = new ArrayList<ImageView>();

		infoAdapterWithCommonViewHolder = new InfoAdapterWithCommonViewHolder(getActivity(), infoDatas, R.layout.item_info);
		//设置适配器
		infoListView.setAdapter(infoAdapterWithCommonViewHolder);
		//设置item点击事件
		infoListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				if(infoDatas.get(position).getTargetUrl() == ""){
					Toast.makeText(getActivity(), "无法找到目标网址", Toast.LENGTH_SHORT).show();
				}
				else {
					Uri uri = Uri.parse(infoDatas.get(position).getTargetUrl());
					Intent intent = new Intent(Intent.ACTION_VIEW, uri);
					getActivity().startActivity(intent);
				}
			}
		});
	}

	/**
	 * 动态添加广告部分图片和指示圆点
	 */
	private void addDynamicView() {
		//初始化图片资源
		for (int i = 0; i < adDatas.size(); i++) {
			ImageView imageView = new ImageView(getActivity());
			//异步加载图片
			mImageLoader.displayImage(adDatas.get(i).getImageUrl(), imageView, options);
			imageView.setScaleType(ScaleType.FIT_XY);
			imageViews.add(imageView);
			dots.get(i).setVisibility(View.VISIBLE);
			dotList.add(dots.get(i));
		}
	}

	private List<BeanAD> getAdDatas() {
		List<BeanAD> adDatas = new ArrayList<BeanAD>();
		BeanAD beanAD;
		for (int i = 0; i < adTitles.length; i++) {
			beanAD = new BeanAD((i+1)+"", adTitles[i], adImageUrls[i], adTargetUrls[i]);
			adDatas.add(beanAD);
		}
		return adDatas;
	}

	private List<BeanInfo> getInfoDatas(){
		List<BeanInfo> infoDatas = new ArrayList<BeanInfo>();
		BeanInfo beanInfo;

//		infoMap = new HashMap<String, String>();

		beanInfo = new BeanInfo("游乐园", resLableColor[(int) (Math.random()*5)], "标题A", "内容A：迪士尼游乐园欢乐游", (int)(Math.random()*9999)+"", (int)(Math.random()*9999)+"", infoTargetUrls[0], infoImageUrls[(int) (Math.random()*3)]);
		infoDatas.add(beanInfo);

		beanInfo = new BeanInfo("Yiluote", resLableColor[(int) (Math.random()*5)], "标题B", "内容B：迪士尼游乐园欢乐游", (int)(Math.random()*9999)+"", (int)(Math.random()*9999)+"", infoTargetUrls[0], infoImageUrls[(int) (Math.random()*3)]);
		infoDatas.add(beanInfo);

		beanInfo = new BeanInfo("大学生衣搭", resLableColor[(int) (Math.random()*5)], "标题C", "内容C：衣洛特大学生衣搭软件 for Android/IOS", (int)(Math.random()*9999)+"", (int)(Math.random()*9999)+"", infoTargetUrls[0], infoImageUrls[(int) (Math.random()*3)]);
		infoDatas.add(beanInfo);

		beanInfo = new BeanInfo("游乐园", resLableColor[(int) (Math.random()*5)], "标题D", "内容D：迪士尼游乐园欢乐游", (int)(Math.random()*9999)+"", (int)(Math.random()*9999)+"", infoTargetUrls[0], infoImageUrls[(int) (Math.random()*3)]);
		infoDatas.add(beanInfo);

		beanInfo = new BeanInfo("冰淇淋YoyoBerry", resLableColor[(int) (Math.random()*5)], "标题E", "内容E：冰淇淋冰淇淋冰淇淋冰淇淋冰淇淋", (int)(Math.random()*9999)+"", (int)(Math.random()*9999)+"", infoTargetUrls[0], infoImageUrls[(int) (Math.random()*3)]);
		infoDatas.add(beanInfo);

		beanInfo = new BeanInfo("衣洛特", resLableColor[(int) (Math.random()*5)], "标题F", "内容F：衣洛特大学生衣搭软件 for Android/IOS", (int)(Math.random()*9999)+"", (int)(Math.random()*9999)+"", infoTargetUrls[0], infoImageUrls[(int) (Math.random()*3)]);
		infoDatas.add(beanInfo);

		beanInfo = new BeanInfo("游乐园", resLableColor[(int) (Math.random()*5)], "标题G", "内容G：迪士尼游乐园欢乐游", (int)(Math.random()*9999)+"", (int)(Math.random()*9999)+"", infoTargetUrls[0], infoImageUrls[(int) (Math.random()*3)]);
		infoDatas.add(beanInfo);

		beanInfo = new BeanInfo("游乐园", resLableColor[(int) (Math.random()*5)], "标题H", "内容H：迪士尼游乐园欢乐游", (int)(Math.random()*9999)+"", (int)(Math.random()*9999)+"", infoTargetUrls[0], infoImageUrls[(int) (Math.random()*3)]);
		infoDatas.add(beanInfo);

		beanInfo = new BeanInfo("Yiluote", resLableColor[(int) (Math.random()*5)], "标题I", "内容I：迪士尼游乐园欢乐游", (int)(Math.random()*9999)+"", (int)(Math.random()*9999)+"", infoTargetUrls[0], infoImageUrls[(int) (Math.random()*3)]);
		infoDatas.add(beanInfo);

		beanInfo = new BeanInfo("大学生衣搭", resLableColor[(int) (Math.random()*5)], "标题J", "内容J：衣洛特大学生衣搭软件 for Android/IOS", (int)(Math.random()*9999)+"", (int)(Math.random()*9999)+"", infoTargetUrls[0], infoImageUrls[(int) (Math.random()*3)]);
		infoDatas.add(beanInfo);

		beanInfo = new BeanInfo("冰淇淋YoyoBerry", resLableColor[(int) (Math.random()*5)], "标题K", "内容K：冰淇淋冰淇淋冰淇淋冰淇淋冰淇淋", (int)(Math.random()*9999)+"", (int)(Math.random()*9999)+"", infoTargetUrls[0], infoImageUrls[(int) (Math.random()*3)]);
		infoDatas.add(beanInfo);

		beanInfo = new BeanInfo("衣洛特", resLableColor[(int) (Math.random()*5)], "标题L", "内容L：衣洛特大学生衣搭软件 for Android/IOS", (int)(Math.random()*9999)+"", (int)(Math.random()*9999)+"", infoTargetUrls[0], infoImageUrls[(int) (Math.random()*3)]);
		infoDatas.add(beanInfo);

		beanInfo = new BeanInfo("游乐园", resLableColor[(int) (Math.random()*5)], "标题M", "内容M：迪士尼游乐园欢乐游", (int)(Math.random()*9999)+"", (int)(Math.random()*9999)+"", infoTargetUrls[0], infoImageUrls[(int) (Math.random()*3)]);
		infoDatas.add(beanInfo);

		return infoDatas;
	}

	private class MyPageChangeListener implements OnPageChangeListener{
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
			BeanAD beanAD = adDatas.get(position);
			adTitle.setText(beanAD.getTitle());
			dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
			dots.get(position).setBackgroundResource(R.drawable.dot_focused);
			oldPosition = position;
		}
	}

	private void initView(View view) {
		infoListView = (ListView) view.findViewById(R.id.infoListView);
		adTitle = (TextView) view.findViewById(R.id.adTitle);
		adViewPager = (ViewPager) view.findViewById(R.id.adViewPager);
		dot0 = view.findViewById(R.id.v_dot0);
		dot1 = view.findViewById(R.id.v_dot1);
		dot2 = view.findViewById(R.id.v_dot2);
		dot3 = view.findViewById(R.id.v_dot3);
	}


	//用于调试
	@Override
	public void onResume() {
		super.onResume();
//		Toast.makeText(getActivity(), "onResume", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onStop() {
		super.onStop();
//		Toast.makeText(getActivity(), "onStop", Toast.LENGTH_SHORT).show();
		//当Fragment不可见的时候停止切换
		//scheduledExecutorService.shutdown();
	}

	@Override
	public void onPause() {
		super.onPause();
//		Toast.makeText(getActivity(), "onPause", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onStart() {
		super.onStart();
//		Toast.makeText(getActivity(), "onStart", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
//		Toast.makeText(getActivity(), "onDestroyView", Toast.LENGTH_SHORT).show();
	}
}
