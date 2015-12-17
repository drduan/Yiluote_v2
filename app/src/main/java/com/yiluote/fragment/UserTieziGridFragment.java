package com.yiluote.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yiluote.yiluote_v2.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.SimpleAdapter;

public class UserTieziGridFragment extends Fragment {

	private List<Map<String, Object>> dataList;
	private GridView grid;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	View view = inflater.inflate(R.layout.fragment_user_tiezi_grid, container,false);
	dataList = new ArrayList<Map<String,Object>>();
	grid = (GridView)view.findViewById(R.id.grid_view);
	for(int i=0;i<10;i++)
	{
		Map<String, Object> item = new HashMap<String, Object>();
		item.put("img_lizi", R.drawable.ad_demo_4);
		item.put("text_jieshao", "帥不帥？帥不帥!!!!跪舔時刻~!!!!!!昨夜熬夜到凌晨2點多才畫完");
		dataList.add(item);
	}
	SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), dataList, 
			R.layout.item_user_tiezi, new String[]{
		"img_lizi","text_jieshao"
	}, new int[] {
	  R.id.img_lizi,R.id.text_jieshao
	});
	grid.setAdapter(simpleAdapter);
	
       return view;
	}
	
 	
}
