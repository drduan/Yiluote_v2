package com.yiluote.fragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.yiluote.adapter.RecommendAdapter;
import com.yiluote.bean.BeanInfo;
import com.yiluote.bean.BeanRecommend;
import com.yiluote.yiluote_v2.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class RecommendFragment extends Fragment {

	public static String IMAGE_CACHE_PATH = "Yiluote/imageCache";//图片缓存路径
	private ImageLoader mImageLoader;
	private DisplayImageOptions options;

	private List<BeanRecommend> recommendDatas;
	private ListView recommendListView;
	private RecommendAdapter recommendAdapter;

	int resLableColor1[] = {
			R.drawable.lable_1, R.drawable.lable_2,
			R.drawable.lable_3, R.drawable.lable_4,
			R.drawable.lable_5};
	int resLableColor2[] = {
			R.drawable.lable_r1, R.drawable.lable_r2,
			R.drawable.lable_r3, R.drawable.lable_r4,
			R.drawable.lable_r5};
	private String[] recommendImageUrls = {
			"https://img.alicdn.com/bao/uploaded/i2/TB1vSZ8JXXXXXaTXFXXXXXXXXXX_!!0-item_pic.jpg_430x430q90.jpg",
			"http://d02.res.meilishuo.net/pic/_o/7f/63/c35d60c00d23229d44fbf7061847_640_832.cf.jpg_df14853a_s7_450_680.jpg",
			"http://d04.res.meilishuo.net/pic/_o/5b/88/0b0bf6914f1304a21cea4b26fb6c_700_700.c8.jpeg",
			"http://d06.res.meilishuo.net/pic/_o/cb/1b/b29b55c0fde6e25f928ba9942404_640_960.cg.jpg_6391fdef_s7_450_680.jpg"
	};
	private String[] recommendTargetUrl = {
			"http://www.baidu.com/"
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_recommend, container, false);

		recommendDatas = getRecommendDatas();
		recommendListView = (ListView) view.findViewById(R.id.recommendListView);
		recommendAdapter = new RecommendAdapter(getActivity(), recommendDatas);
		recommendListView.setAdapter(recommendAdapter);

		return view;
	}

	private List<BeanRecommend> getRecommendDatas() {
		List<BeanRecommend> recommendDatas = new ArrayList<BeanRecommend>();
		BeanRecommend beanRecommend;

		beanRecommend = new BeanRecommend(recommendImageUrls[(int) (Math.random()*recommendImageUrls.length)], "衣洛特大学生衣搭软件 Android/iOS", "标签1", resLableColor1[(int) (Math.random()*5)], "标签2", resLableColor1[(int) (Math.random()*5)], recommendTargetUrl[0]);
		recommendDatas.add(beanRecommend);

		beanRecommend = new BeanRecommend(recommendImageUrls[(int) (Math.random()*recommendImageUrls.length)], "衣洛特iOS", "标签1", resLableColor2[(int) (Math.random()*5)], "标签2", resLableColor2[(int) (Math.random()*5)], recommendTargetUrl[0]);
		recommendDatas.add(beanRecommend);

		beanRecommend = new BeanRecommend(recommendImageUrls[(int) (Math.random()*recommendImageUrls.length)], "衣搭软件 Android/iOS", "标签1", resLableColor1[(int) (Math.random()*5)], "标签2", resLableColor1[(int) (Math.random()*5)], recommendTargetUrl[0]);
		recommendDatas.add(beanRecommend);

		beanRecommend = new BeanRecommend(recommendImageUrls[(int) (Math.random()*recommendImageUrls.length)], "大学生衣搭软件 Android", "标签1", resLableColor2[(int) (Math.random()*5)], "标签2", resLableColor2[(int) (Math.random()*5)], recommendTargetUrl[0]);
		recommendDatas.add(beanRecommend);

		beanRecommend = new BeanRecommend(recommendImageUrls[(int) (Math.random()*recommendImageUrls.length)], "衣洛特大学生衣搭软件Yiluote~", "标签1", resLableColor1[(int) (Math.random()*5)], "标签2", resLableColor1[(int) (Math.random()*5)], recommendTargetUrl[0]);
		recommendDatas.add(beanRecommend);

		beanRecommend = new BeanRecommend(recommendImageUrls[(int) (Math.random()*recommendImageUrls.length)], "大学生衣搭软件 平台 Android/iOS", "标签1", resLableColor2[(int) (Math.random()*5)], "标签2", resLableColor2[(int) (Math.random()*5)], recommendTargetUrl[0]);
		recommendDatas.add(beanRecommend);

		beanRecommend = new BeanRecommend(recommendImageUrls[(int) (Math.random()*recommendImageUrls.length)], "衣洛特衣搭软件Yiluote", "标签1", resLableColor1[(int) (Math.random()*5)], "标签2", resLableColor1[(int) (Math.random()*5)], recommendTargetUrl[0]);
		recommendDatas.add(beanRecommend);

		beanRecommend = new BeanRecommend(recommendImageUrls[(int) (Math.random()*recommendImageUrls.length)], "Yiluote Android", "标签1", resLableColor2[(int) (Math.random()*5)], "标签2", resLableColor2[(int) (Math.random()*5)], recommendTargetUrl[0]);
		recommendDatas.add(beanRecommend);

		beanRecommend = new BeanRecommend(recommendImageUrls[(int) (Math.random()*recommendImageUrls.length)], "Yiluote Yiluote~", "标签1", resLableColor1[(int) (Math.random()*5)], "标签2", resLableColor1[(int) (Math.random()*5)], recommendTargetUrl[0]);
		recommendDatas.add(beanRecommend);

		return recommendDatas;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initImageLoader();	//只需被创建一次，所以放在OnCreate中
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
}
