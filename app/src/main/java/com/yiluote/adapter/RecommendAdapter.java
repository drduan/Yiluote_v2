package com.yiluote.adapter;

import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.yiluote.bean.BeanInfo;
import com.yiluote.bean.BeanRecommend;
import com.yiluote.yiluote_v2.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RecommendAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<BeanRecommend> mDatas;

	private ImageLoader mImageLoader = ImageLoader.getInstance();
	private DisplayImageOptions options = new DisplayImageOptions.Builder()
			.showImageOnLoading(R.drawable.icon_stub)
			.showImageForEmptyUri(R.drawable.icon_stub)
			.showImageOnFail(R.drawable.icon_stub)
			.cacheInMemory(true)
			.cacheOnDisc(true)
			.bitmapConfig(Bitmap.Config.RGB_565)
			.imageScaleType(ImageScaleType.EXACTLY)
			.build();

	public RecommendAdapter(Context context, List<BeanRecommend> datas) {
		mInflater = LayoutInflater.from(context);
		mDatas = datas;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mDatas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public int getItemViewType(int position) {
		if (position % 2 == 0) {
			return 0;
		} else {
			return 1;
		}
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		int type = getItemViewType(position);
		ViewHolder holder = null;
		if (convertView == null) {
			switch (type) {
				case 0:
					convertView = mInflater.inflate(R.layout.item_recommend, parent, false);
					holder = new ViewHolder();
					holder.mLable1 = (TextView) convertView.findViewById(R.id.recommendLable1);
					holder.mLable2 = (TextView) convertView.findViewById(R.id.recommendLable2);
					holder.mText = (TextView) convertView.findViewById(R.id.recommendText);
					holder.mImage = (ImageView) convertView.findViewById(R.id.recommendImage);
					convertView.setTag(holder);
					break;

				case 1:
					convertView = mInflater.inflate(R.layout.item_recommend2, parent, false);
					holder = new ViewHolder();
					holder.mLable1 = (TextView) convertView.findViewById(R.id.recommendLable1);
					holder.mLable2 = (TextView) convertView.findViewById(R.id.recommendLable2);
					holder.mText = (TextView) convertView.findViewById(R.id.recommendText);
					holder.mImage = (ImageView) convertView.findViewById(R.id.recommendImage);
					convertView.setTag(holder);
					break;

				default:
					break;
			}
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}
		BeanRecommend beanRecommend = mDatas.get(position);
		holder.mLable1.setText(beanRecommend.getLable1());
		holder.mLable1.setBackgroundResource(beanRecommend.getLableColor1());
		holder.mLable2.setText(beanRecommend.getLable2());
		holder.mLable2.setBackgroundResource(beanRecommend.getLableColor2());
		holder.mText.setText(beanRecommend.getText());
		mImageLoader.displayImage(beanRecommend.getImageUrl(), holder.mImage, options);
		return convertView;
	}

	/**
	 * 各种控件的引用
	 * @author lyp
	 */
	private class ViewHolder {
		TextView mLable1;
		TextView mLable2;
		TextView mText;
		ImageView mImage;
	}
}
