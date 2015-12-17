package com.yiluote.yiluote_v2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class UserDraftsActivity extends Activity {

	private ListView draftsListView;
	private SimpleAdapter simp_adapter;
	private List<Map<String, Object>> dataList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_drafts);
		
		draftsListView = (ListView) findViewById(R.id.draftsListView);
		dataList = new ArrayList<Map<String, Object>>();
		simp_adapter = new SimpleAdapter(this, getData(), R.layout.item_user_drafts, 
				new String[] { "draftsImage", "draftsText" }, 
				new int[] {R.id.draftsImage, R.id.draftsText});
		draftsListView.setAdapter(simp_adapter);
	}

	private List<Map<String, Object>> getData() {
		for (int i = 0; i < 20; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("draftsImage", R.drawable.ic_launcher);
			map.put("draftsText", "²Ý¸åÄÚÈÝ" + i);
			dataList.add(map);
		}
		return dataList;
	}
}