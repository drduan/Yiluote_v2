package com.zhy.imageloader;

import java.util.LinkedList;
import java.util.List;

import com.yiluote.yiluote_v2.R;
import com.zhy.utils.CommonAdapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MyAdapter extends CommonAdapter<String> {

	public static List<String> mSelectedImage = new LinkedList<String>();

	private String mDirPath;

	public MyAdapter(Context context, List<String> mDatas, int itemLayoutId,
			String dirPath) {
		super(context, mDatas, itemLayoutId);
		this.mDirPath = dirPath;
	}

	@Override
	public void convert(final com.zhy.utils.ViewHolder helper, final String item) {
		helper.setImageResource(R.id.id_item_image, R.drawable.pictures_no);
		helper.setImageResource(R.id.id_item_select,
				R.drawable.picture_unselected);
		helper.setImageByUrl(R.id.id_item_image, mDirPath + "/" + item);

		final ImageView mImageView = helper.getView(R.id.id_item_image);
		final ImageView mSelect = helper.getView(R.id.id_item_select);

		mImageView.setColorFilter(null);
		mImageView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mSelectedImage.contains(mDirPath + "/" + item)) {
					mSelectedImage.remove(mDirPath + "/" + item);
					mSelect.setImageResource(R.drawable.picture_unselected);
					mImageView.setColorFilter(null);
				} else {
					mSelectedImage.add(mDirPath + "/" + item);
					mSelect.setImageResource(R.drawable.pictures_selected);
					mImageView.setColorFilter(Color.parseColor("#77000000"));
				}

			}
		});

		if (mSelectedImage.contains(mDirPath + "/" + item)) {
			mSelect.setImageResource(R.drawable.pictures_selected);
			mImageView.setColorFilter(Color.parseColor("#77000000"));
		}
	}
}
