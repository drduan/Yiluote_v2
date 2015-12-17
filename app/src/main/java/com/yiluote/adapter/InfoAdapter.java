package com.yiluote.adapter;

import java.util.List;
import com.yiluote.bean.BeanInfo;
import com.yiluote.yiluote_v2.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * 传统写法的适配器
 * @author lyp
 */
public class InfoAdapter extends BaseAdapter {

	int resLable[] = {R.drawable.lable_1, R.drawable.lable_2,
			R.drawable.lable_3, R.drawable.lable_4};

	private LayoutInflater mInflater;
	private List<BeanInfo> mDatas;

	public InfoAdapter(Context context, List<BeanInfo> datas) {
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
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			convertView = mInflater.inflate(R.layout.item_info, parent, false);
			holder = new ViewHolder();

			holder.mLable = (TextView) convertView.findViewById(R.id.infoLable);
			holder.mTitle = (TextView) convertView.findViewById(R.id.infoTitle);
			holder.mContent = (TextView) convertView.findViewById(R.id.infoContent);
			holder.mHits = (TextView) convertView.findViewById(R.id.infoHits);
			holder.mComments = (TextView) convertView.findViewById(R.id.infoComments);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		BeanInfo beanInfo = mDatas.get(position);
		holder.mLable.setText(beanInfo.getLable());
		holder.mLable.setBackgroundResource(resLable[(int) (Math.random()*4)]);
		holder.mTitle.setText(beanInfo.getTitle());
		holder.mContent.setText(beanInfo.getContent());
		holder.mHits.setText(beanInfo.getHits());
		holder.mComments.setText(beanInfo.getComments());
		return convertView;
	}

	/**
	 * 各种控件的引用
	 * @author lyp
	 */
	private class ViewHolder{
		TextView mLable;
		TextView mTitle;
		TextView mContent;
		TextView mHits;
		TextView mComments;
	}
}
