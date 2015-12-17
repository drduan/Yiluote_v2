package com.yiluote.fragment;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.zip.Inflater;

import com.yiluote.yiluote_v2.R;
import com.zhy.bean.ImageFloder;
import com.zhy.imageloader.ListImageDirPopupWindow;
import com.zhy.imageloader.ListImageDirPopupWindow.OnImageDirSelected;
import com.zhy.imageloader.MyAdapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.PopupWindow.OnDismissListener;

public class ImageLoaderFragment extends Fragment implements OnImageDirSelected {

	private int mScreenHeight;
	private int mPicsSize;
	int totalCount = 0;
	private View view;
	private GridView mGirdView;
	private TextView mChooseDir;
	private TextView mImageCount;
	private RelativeLayout mBottomLy;
	private ProgressDialog mProgressDialog;
	private File mImgDir;
	private List<String> mImgs;
	private MyAdapter mAdapter;
	private HashSet<String> mDirPaths = new HashSet<String>();
	private List<ImageFloder> mImageFloders = new ArrayList<ImageFloder>();
	private ListImageDirPopupWindow mListImageDirPopupWindow;

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			mProgressDialog.dismiss();
			data2View();
			initListDirPopupWindw();
		}
	};

	private void data2View() {
		if (mImgDir == null) {
			Toast.makeText(this.getActivity().getApplicationContext(),
					"一张图片也没找到", Toast.LENGTH_SHORT).show();
			return;
		}
		mImgs = Arrays.asList(mImgDir.list());
		mAdapter = new MyAdapter(this.getActivity().getApplicationContext(),
				mImgs, R.layout.item_image_loader_grid, mImgDir.getAbsolutePath());
		mGirdView.setAdapter(mAdapter);
		mImageCount.setText(totalCount + "张");
	};

	private void initListDirPopupWindw()
	{
		mListImageDirPopupWindow = new ListImageDirPopupWindow(
				LayoutParams.MATCH_PARENT, (int) (mScreenHeight * 0.7),
				mImageFloders, LayoutInflater.from(getActivity().getApplicationContext())
				.inflate(R.layout.list_dir, null));

		mListImageDirPopupWindow.setOnDismissListener(new OnDismissListener()
		{

			@Override
			public void onDismiss()
			{
				WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
				lp.alpha = 1.0f;
				getActivity().getWindow().setAttributes(lp);
			}
		});
		mListImageDirPopupWindow.setOnImageDirSelected(this);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		DisplayMetrics outMetrics = new DisplayMetrics();
		this.getActivity().getWindowManager().getDefaultDisplay()
				.getMetrics(outMetrics);
		mScreenHeight = outMetrics.heightPixels;


		getImages();

	}

	private void initView() {
		mGirdView = (GridView) view.findViewById(R.id.id_gridView);
		mChooseDir = (TextView) view.findViewById(R.id.id_choose_dir);
		mImageCount = (TextView) view.findViewById(R.id.id_total_count);
		mBottomLy = (RelativeLayout) view.findViewById(R.id.id_bottom_ly);
	}

	private void initEvent() {
		mBottomLy.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				mListImageDirPopupWindow
						.setAnimationStyle(R.style.anim_popup_dir);
				mListImageDirPopupWindow.showAsDropDown(mBottomLy, 0, 0);

				WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
				lp.alpha = .3f;
				getActivity().getWindow().setAttributes(lp);
			}
		});
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_image_loader, container,
				false);
		initView();
		initEvent();
		return view;
	}

	private void getImages() {
		if (!Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			Toast.makeText(getActivity(), "暂无外部存储", Toast.LENGTH_SHORT).show();
			return;
		}
		// 显示进度条
		mProgressDialog = ProgressDialog.show(getActivity(), null, "加载中...");

		new Thread(new Runnable() {
			@Override
			public void run() {

				String firstImage = null;

				Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				ContentResolver mContentResolver = ImageLoaderFragment.this
						.getActivity().getContentResolver();

				// 只查询jpeg和png的图片
				Cursor mCursor = mContentResolver.query(mImageUri, null,
						MediaStore.Images.Media.MIME_TYPE + "=? or "
								+ MediaStore.Images.Media.MIME_TYPE + "=?",
						new String[] { "image/jpeg", "image/png" },
						MediaStore.Images.Media.DATE_MODIFIED);

				Log.e("TAG", mCursor.getCount() + "");
				while (mCursor.moveToNext()) {
					// 获取图片的路径
					String path = mCursor.getString(mCursor
							.getColumnIndex(MediaStore.Images.Media.DATA));

					Log.e("TAG", path);
					// 拿到第一张图片的路径
					if (firstImage == null)
						firstImage = path;
					// 获取该图片的父路径名
					File parentFile = new File(path).getParentFile();
					if (parentFile == null)
						continue;
					String dirPath = parentFile.getAbsolutePath();
					ImageFloder imageFloder = null;
					// 利用一个HashSet防止多次扫描同一个文件夹（不加这个判断，图片多起来还是相当恐怖的~~）
					if (mDirPaths.contains(dirPath)) {
						continue;
					} else {
						mDirPaths.add(dirPath);
						// 初始化imageFloder
						imageFloder = new ImageFloder();
						imageFloder.setDir(dirPath);
						imageFloder.setFirstImagePath(path);
					}

					int picSize = parentFile.list(new FilenameFilter() {
						@Override
						public boolean accept(File dir, String filename) {
							if (filename.endsWith(".jpg")
									|| filename.endsWith(".png")
									|| filename.endsWith(".jpeg"))
								return true;
							return false;
						}
					}).length;
					totalCount += picSize;

					imageFloder.setCount(picSize);
					mImageFloders.add(imageFloder);

					if (picSize > mPicsSize) {
						mPicsSize = picSize;
						mImgDir = parentFile;
					}
				}
				mCursor.close();

				// 扫描完成，辅助的HashSet也就可以释放内存了
				mDirPaths = null;

				// 通知Handler扫描图片完成
				mHandler.sendEmptyMessage(0x110);

			}
		}).start();
	}

	@Override
	public void selected(ImageFloder floder)
	{

		mImgDir = new File(floder.getDir());
		mImgs = Arrays.asList(mImgDir.list(new FilenameFilter()
		{
			@Override
			public boolean accept(File dir, String filename)
			{
				if (filename.endsWith(".jpg") || filename.endsWith(".png")
						|| filename.endsWith(".jpeg"))
					return true;
				return false;
			}
		}));
		mAdapter = new MyAdapter(getActivity().getApplicationContext(), mImgs,
				R.layout.item_image_loader_grid, mImgDir.getAbsolutePath());
		mGirdView.setAdapter(mAdapter);
		// mAdapter.notifyDataSetChanged();
		mImageCount.setText(floder.getCount() + "张");
		mChooseDir.setText(floder.getName());
		mListImageDirPopupWindow.dismiss();

	}

}
