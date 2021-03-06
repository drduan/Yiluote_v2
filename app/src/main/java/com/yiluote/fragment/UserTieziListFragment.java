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
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class UserTieziListFragment extends Fragment {

	private List<Map<String, Object>> dataList;
	private ListView list;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	View view = inflater.inflate(R.layout.fragment_user_tiezi_list, container,false);
	dataList = new ArrayList<Map<String,Object>>();
	list = (ListView)view.findViewById(R.id.list_view);
	for(int i=0;i<10;i++)
	{
		Map<String, Object> item = new HashMap<String, Object>();
		item.put("img_lizi", R.drawable.ad_demo_1);
		item.put("text_jieshao", "��������������!!!!����r��~!!!!!!��ҹ��ҹ���賿2�c��Ů���");
		dataList.add(item);
	}
	SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), dataList, 
			R.layout.item_user_tiezi, new String[]{
		"img_lizi","text_jieshao"
	}, new int[] {
	  R.id.img_lizi,R.id.text_jieshao
	});
	list.setAdapter(simpleAdapter);
	
       return view;
	}
}
