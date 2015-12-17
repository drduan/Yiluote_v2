package com.yiluote.yiluote_v2;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class UserCollectionActivity extends Activity implements OnClickListener{

	private Intent intent;

	private TextView userCollectionBack;

	private ExpandableListView expandableListView;
	private ScrollView scrollview;

	private List<String> group_list;

	private List<String> item_lt;

	private List<List<String>> item_list;

	private List<List<Integer>> item_list2;

	private List<List<Integer>> gr_list2;

	private MyExpandableListViewAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_collection);

		group_list = new ArrayList<String>();
		group_list.add("资讯");
		group_list.add("话题");

		item_lt = new ArrayList<String>();
		item_lt.add("格子衬衣");
		item_lt.add("新时尚街区");
		item_lt.add("百搭风格");

		item_list = new ArrayList<List<String>>();
		item_list.add(item_lt);
		item_list.add(item_lt);
		item_list.add(item_lt);

		List<Integer> tmp_list = new ArrayList<Integer>();
		tmp_list.add(R.drawable.ic_launcher);
		tmp_list.add(R.drawable.ic_launcher);
		tmp_list.add(R.drawable.ic_launcher);

		item_list2 = new ArrayList<List<Integer>>();
		item_list2.add(tmp_list);
		item_list2.add(tmp_list);
		item_list2.add(tmp_list);

		List<Integer> gr_list = new ArrayList<Integer>();
		gr_list.add(R.drawable.ic_launcher);
		gr_list.add(R.drawable.ic_launcher);
		gr_list.add(R.drawable.ic_launcher);

		gr_list2 = new ArrayList<List<Integer>>();
		gr_list2.add(gr_list);
		gr_list2.add(gr_list);
		gr_list2.add(gr_list);

		expandableListView = (ExpandableListView) findViewById(R.id.user_shoucang);
		expandableListView.setGroupIndicator(null);

		// 监听组点击
		expandableListView.setOnGroupClickListener(new OnGroupClickListener() {
			@SuppressLint("NewApi")
			@Override
			public boolean onGroupClick(ExpandableListView parent, View v,
										int groupPosition, long id) {
				if (item_list.get(groupPosition).isEmpty()) {
					return true;
				}
				return false;
			}
		});

		// 监听每个分组里子控件的点击事件
		expandableListView.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
										int groupPosition, int childPosition, long id) {

				// Toast.makeText(UserParticitapionActivity.this, "group=" +
				// groupPosition + "---child=" + childPosition + "---" +
				// item_list.get(groupPosition).get(childPosition),
				// Toast.LENGTH_SHORT).show();

				return false;
			}
		});

		adapter = new MyExpandableListViewAdapter(this);

		expandableListView.setAdapter(adapter);

		userCollectionBack = (TextView) findViewById(R.id.userCollectionBack);
		userCollectionBack.setOnClickListener(this);
	}

	class MyExpandableListViewAdapter extends BaseExpandableListAdapter {
		private Context context;

		public MyExpandableListViewAdapter(Context context) {
			this.context = context;
		}

		@Override
		public Object getChild(int groupPosition, int childPosition) {

			return item_list.get(groupPosition).get(childPosition);

		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {

			return childPosition;

		}

		@Override
		public int getChildrenCount(int groupPosition) {

			return item_list.get(groupPosition).size();

		}

		@Override
		public View getChildView(int groupPosition, int childPosition,
								 boolean isLastChild, View convertView, ViewGroup parent) {
			ItemHolder itemHolder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(context).inflate(
						R.layout.item_user_collection_child, null);
				itemHolder = new ItemHolder();
				itemHolder.txt = (TextView) convertView.findViewById(R.id.txt);
				itemHolder.img = (ImageView) convertView.findViewById(R.id.img);
				convertView.setTag(itemHolder);
			} else {
				itemHolder = (ItemHolder) convertView.getTag();
			}
			itemHolder.txt.setText(item_list.get(groupPosition).get(
					childPosition));
			itemHolder.img.setBackgroundResource(item_list2.get(groupPosition)
					.get(childPosition));
			return convertView;
		}

		@Override
		public Object getGroup(int groupPosition) {

			return group_list.get(groupPosition);

		}

		@Override
		public int getGroupCount() {

			return group_list.size();
		}

		@Override
		public long getGroupId(int groupPosition) {

			return groupPosition;

		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
								 View convertView, ViewGroup parent) {
			GroupHolder groupHolder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(context).inflate(
						R.layout.item_user_collection_father, null);
				groupHolder = new GroupHolder();
				groupHolder.txt = (TextView) convertView.findViewById(R.id.txt);
				groupHolder.img = (ImageView) convertView
						.findViewById(R.id.img);
				convertView.setTag(groupHolder);
			} else {
				groupHolder = (GroupHolder) convertView.getTag();
			}

			if (!isExpanded) {
				groupHolder.img.setBackgroundResource(R.drawable.fangxiangtou);
			} else {
				groupHolder.img
						.setBackgroundResource(R.drawable.fangxiangtoutwo);
			}

			groupHolder.txt.setText(group_list.get(groupPosition));
			return convertView;
		}

		@Override
		public boolean hasStableIds() {

			return true;

		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {

			return true;

		}

		class GroupHolder {
			public TextView txt;

			public ImageView img;
		}

		class ItemHolder {
			public ImageView img;

			public TextView txt;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.userCollectionBack:
				intent = new Intent();
				intent.setClass(UserCollectionActivity.this, UserActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
				finish();
				break;

			default:
				break;
		}
	}
}