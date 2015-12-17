package com.yiluote.adapter;

import java.util.List;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.yiluote.bean.BeanInfo;
import com.yiluote.utils.CommonAdapter;
import com.yiluote.utils.ViewHolder;
import com.yiluote.yiluote_v2.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoAdapterWithCommonViewHolder extends CommonAdapter<BeanInfo> {
	
//	private LayoutInflater mInflater;
//	private List<BeanInfo> mDatas;
//	private Context mContext;
	
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
	
	public InfoAdapterWithCommonViewHolder(Context context, List<BeanInfo> datas, int layoutId) {
		super(context, datas, layoutId);
//		mContext = context;
//		mInflater = LayoutInflater.from(context);
//		mDatas = datas;
	}
	
//	@Override
//	public int getCount() {
//		// TODO Auto-generated method stub
//		return mDatas.size();
//	}
//
//	@Override
//	public Object getItem(int position) {
//		// TODO Auto-generated method stub
//		return mDatas.get(position);
//	}
//
//	@Override
//	public long getItemId(int position) {
//		// TODO Auto-generated method stub
//		return position;
//	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder = ViewHolder.get(mContext, convertView, parent, layoutId, position);
		
		BeanInfo beanInfo = mDatas.get(position);
		
		( (TextView)holder.getView(R.id.infoTitle) ).setText(beanInfo.getTitle());
		( (TextView)holder.getView(R.id.infoContent) ).setText(beanInfo.getContent());
		( (TextView)holder.getView(R.id.infoHits) ).setText(beanInfo.getHits());
		( (TextView)holder.getView(R.id.infoComments) ).setText(beanInfo.getComments());
		( (TextView)holder.getView(R.id.infoLable) ).setText(beanInfo.getLable());
		( (TextView)holder.getView(R.id.infoLable) ).setBackgroundResource(beanInfo.getLableColor());
		mImageLoader.displayImage(beanInfo.getImageUrl(), (ImageView)holder.getView(R.id.infoImage), options);
		
		return holder.getConvertView();
	}
	
}
