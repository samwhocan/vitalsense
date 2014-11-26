package com.omniapps.vitalsense5.views;

import com.omniapps.vitalsense5.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GraphFragment extends Fragment {
	public static final String ARG_MENU_ITEM_NUMBER = "menu_item_number";
	
	public GraphFragment() {
		// Empty constructor required for fragment subclasses
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		int i = getArguments().getInt(ARG_MENU_ITEM_NUMBER);
		View view = inflater.inflate(R.layout.graph_view, container, false);		
		String menuItem = getResources().getStringArray(R.array.menu_array)[i];
		getActivity().setTitle(menuItem);
		
		return view;		
	}
}